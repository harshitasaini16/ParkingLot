package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ParkingLot;
import com.example.demo.model.ParkingLotRequest;
import com.example.demo.repository.ParkingLotRepository;
import com.example.demo.service.ParkingLotService;

@Service
public class ParkingLotServiceImp implements ParkingLotService{

	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	public ParkingLotServiceImp(ParkingLotRepository parkingLotRepository) {
		this.parkingLotRepository = parkingLotRepository;
	}
	
	@Override
	public ResponseEntity<Void> addParkingLot(ParkingLotRequest parkingLotRequest) {
	    try {
	        ParkingLot parkingLot = new ParkingLot(parkingLotRequest);
	        this.parkingLotRepository.save(parkingLot);
	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	@Override
	public ResponseEntity<List<ParkingLot>> getParkingLot() {
	    List<ParkingLot> parkingLotList = this.parkingLotRepository.findAll();
	    return ResponseEntity.ok(parkingLotList);
	}
	
	@Override
	public ResponseEntity<ParkingLot> getParkingLot(int id) {
	    Optional<ParkingLot> parkingLot = this.parkingLotRepository.findById(id);
	    if(parkingLot.isPresent()) {
	    	return ResponseEntity.ok(parkingLot.get());
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Void> deleteParkingLot(int id) {
	    Optional<ParkingLot> parkingLot = this.parkingLotRepository.findById(id);
	    if(parkingLot.isPresent()) {
	    	this.parkingLotRepository.deleteById(id);
	    	return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<ParkingLot> editParkingLot(int id, ParkingLotRequest parkingLotRequest) {
	    Optional<ParkingLot> parkingLot = this.parkingLotRepository.findById(id);
	    if(parkingLot.isPresent()) {
	    	parkingLot.get().setAddress(parkingLotRequest.getAddress());
	    	this.parkingLotRepository.save(parkingLot.get());
	    	return ResponseEntity.ok(parkingLot.get());
	    }
	    return ResponseEntity.notFound().build();
	}

}