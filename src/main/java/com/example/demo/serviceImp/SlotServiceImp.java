package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ParkingLot;
import com.example.demo.entity.ParkingSpace;
import com.example.demo.entity.Slot;
import com.example.demo.enums.VehicleType;
import com.example.demo.model.SlotRequest;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.ParkingLotService;
import com.example.demo.service.ParkingSpaceService;
import com.example.demo.service.SlotService;

@Service
public class SlotServiceImp implements SlotService {

    private SlotRepository slotRepository;
    private ParkingLotService parkingLotService;
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    public SlotServiceImp(SlotRepository slotRepository, ParkingLotService parkingLotService, ParkingSpaceService parkingSpaceService) {
        this.slotRepository = slotRepository;
        this.parkingLotService = parkingLotService;
        this.parkingSpaceService = parkingSpaceService;
    }

    @Override
    public ResponseEntity<Void> addSlot(SlotRequest slotRequest) {
        Slot slot = new Slot();

        ResponseEntity<ParkingSpace> parkingSpaceResponse = this.parkingSpaceService.getParkingSpace(slotRequest.getParkingSpaceId());
        if (parkingSpaceResponse.getStatusCode() != HttpStatus.OK || parkingSpaceResponse.getBody() == null) {
            return ResponseEntity.notFound().build();
        }
        slot.setParkingSpace(parkingSpaceResponse.getBody());

        if (slotRequest.getVehicleType() == null) {
            return ResponseEntity.badRequest().build();
        }
        slot.setVehicleType(slotRequest.getVehicleType());

        if (slotRequest.getDistance() == 0) {
            return ResponseEntity.badRequest().build();
        }
        slot.setDistance(slotRequest.getDistance());

        this.slotRepository.save(slot);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<Slot>> getSlot() {
        List<Slot> slots = this.slotRepository.findAll();
        if (slots.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(slots);
    }

    @Override
    public ResponseEntity<Slot> getSlot(int id) {
        Slot slot = this.slotRepository.findById(id).orElse(null);
        if (slot == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(slot);
    }

    @Override
    public ResponseEntity<Void> deleteSlot(int id) {
        if (!this.slotRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.slotRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Slot> editSlot(int id, SlotRequest slotRequest) {
        Slot slot = this.slotRepository.findById(id).orElse(null);
        if (slot == null) {
            return ResponseEntity.notFound().build();
        }

        ResponseEntity<ParkingSpace> parkingSpaceResponse = this.parkingSpaceService.getParkingSpace(slotRequest.getParkingSpaceId());
        if (parkingSpaceResponse.getStatusCode() == HttpStatus.OK && parkingSpaceResponse.getBody() != null) {
            slot.setParkingSpace(parkingSpaceResponse.getBody());
        }

        if (slotRequest.getVehicleType() != null) {
            slot.setVehicleType(slotRequest.getVehicleType());
        }

        if (slotRequest.getDistance() != 0) {
            slot.setDistance(slotRequest.getDistance());
        }

        this.slotRepository.save(slot);
        return ResponseEntity.ok(slot);
    }

	@Override
	public ResponseEntity<Slot> getVacantSlot(int parkingSpaceId, VehicleType vehicleType) {
        Optional<Slot> slot = this.slotRepository.findNearestVacantSlot(parkingSpaceId, vehicleType);

        if (slot.isPresent()) {
            return ResponseEntity.ok(slot.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@Override
	public ResponseEntity<Void> parkVehichle(int id, int vehicleId) {
		// TODO Auto-generated method stub
		Slot slot = this.slotRepository.findById(id).orElse(null);
        if (slot == null) {
            return ResponseEntity.notFound().build();
        }
        if(slot.getParkedVehicleId() != 0 ) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        slot.setParkedVehicleId(vehicleId);
        this.slotRepository.save(slot);
        System.out.println(slot.toString());
        System.out.println(vehicleId);
        return ResponseEntity.ok().build(); 
	}

	@Override
	public ResponseEntity<Void> updateSlot(int id, Slot updatedSlot) {
	    Optional<Slot> existingSlotOpt = this.slotRepository.findById(id);
	    if (!existingSlotOpt.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }
	    Slot existingSlot = existingSlotOpt.get();
	    existingSlot.setParkingSpace(updatedSlot.getParkingSpace());
	    existingSlot.setVehicleType(updatedSlot.getVehicleType()); 
	    existingSlot.setParkedVehicleId(updatedSlot.getParkedVehicleId());
	    existingSlot.setDistance(updatedSlot.getDistance());
	    slotRepository.save(existingSlot);
	    
	    return ResponseEntity.ok().build();
	}

}
