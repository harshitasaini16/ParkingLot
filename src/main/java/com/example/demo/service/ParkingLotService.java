package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.ParkingLot;
import com.example.demo.model.ParkingLotRequest;

public interface ParkingLotService {
	
	public ResponseEntity<Void> addParkingLot(ParkingLotRequest parkingLotRequest);
	
	public ResponseEntity<List<ParkingLot>> getParkingLot();
	
	public ResponseEntity<ParkingLot> getParkingLot(int id);
	
	public ResponseEntity<Void> deleteParkingLot(int id);
	
	public ResponseEntity<ParkingLot> editParkingLot(int id, ParkingLotRequest parkingLotRequest);
}
