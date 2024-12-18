package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.ParkingSpace;
import com.example.demo.model.ParkingSpaceRequest;

public interface ParkingSpaceService {
	
	public ResponseEntity<Void> addParkingSpace(ParkingSpaceRequest parkingSpaceRequest);
	
	public ResponseEntity<List<ParkingSpace>> getParkingSpace();
	
	public ResponseEntity<ParkingSpace> getParkingSpace(int id);
	
	public ResponseEntity<Void> deleteParkingSpace(int id);
	
	public ResponseEntity<ParkingSpace> editParkingSpace(int id, ParkingSpaceRequest parkingSpaceRequest);
}
