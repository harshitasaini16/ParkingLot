package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ParkingLot;
import com.example.demo.entity.ParkingSpace;
import com.example.demo.model.ParkingSpaceRequest;
import com.example.demo.repository.ParkingSpaceRepository;
import com.example.demo.service.ParkingLotService;
import com.example.demo.service.ParkingSpaceService;

@Service
public class ParkingSpaceServiceImp implements ParkingSpaceService{
	
	private ParkingSpaceRepository parkingSpaceRepository;
	private ParkingLotService parkingLotService;
	
	@Autowired
	public ParkingSpaceServiceImp(ParkingSpaceRepository parkingSpaceRepository, ParkingLotService parkingLotService) {
		this.parkingSpaceRepository = parkingSpaceRepository;
		this.parkingLotService = parkingLotService;
	}
	
	@Override
	public ResponseEntity<Void> addParkingSpace(ParkingSpaceRequest parkingSpaceRequest) {
	    try {
	        ResponseEntity<ParkingLot> parkingLotResponse = this.parkingLotService.getParkingLot(parkingSpaceRequest.getParkingLotId());

	        if (parkingLotResponse.getStatusCode() == HttpStatus.OK && parkingLotResponse.getBody() != null) {
	            ParkingLot parkingLot = parkingLotResponse.getBody();
	            ParkingSpace parkingSpace = new ParkingSpace(parkingLot);
	            this.parkingSpaceRepository.save(parkingSpace);
	            return ResponseEntity.status(HttpStatus.CREATED).build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	
	@Override
	public ResponseEntity<List<ParkingSpace>> getParkingSpace() {
	    List<ParkingSpace> parkingSpaceList = this.parkingSpaceRepository.findAll();
	    return ResponseEntity.ok(parkingSpaceList);
	}
	
	@Override
	public ResponseEntity<ParkingSpace> getParkingSpace(int id) {
	    Optional<ParkingSpace> parkingSpace = this.parkingSpaceRepository.findById(id);
	    if(parkingSpace.isPresent()) {
	    	return ResponseEntity.ok(parkingSpace.get());
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Void> deleteParkingSpace(int id) {
	    Optional<ParkingSpace> parkingSpace = this.parkingSpaceRepository.findById(id);
	    if(parkingSpace.isPresent()) {
	    	this.parkingSpaceRepository.deleteById(id);
	    	return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<ParkingSpace> editParkingSpace(int id, ParkingSpaceRequest parkingSpaceRequest) {
	    Optional<ParkingSpace> parkingSpaceOptional = this.parkingSpaceRepository.findById(id);

	    if (parkingSpaceOptional.isPresent()) {
	        ParkingSpace parkingSpace = parkingSpaceOptional.get();

	        if (parkingSpaceRequest.getParkingLotId() != 0) {
	            ResponseEntity<ParkingLot> parkingLotResponse = this.parkingLotService.getParkingLot(parkingSpaceRequest.getParkingLotId());
	            if (parkingLotResponse.getStatusCode() == HttpStatus.OK && parkingLotResponse.getBody() != null) {
	                ParkingLot parkingLot = parkingLotResponse.getBody();
	                parkingSpace.setParkingLot(parkingLot);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }
	        }

	        this.parkingSpaceRepository.save(parkingSpace);

	        return ResponseEntity.ok(parkingSpace);
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
