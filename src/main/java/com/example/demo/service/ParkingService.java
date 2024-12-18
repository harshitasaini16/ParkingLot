package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Payments;
import com.example.demo.entity.Ticket;
import com.example.demo.model.ParkingRequest;

public interface ParkingService {
	
	public ResponseEntity<Ticket> parkVehicle(ParkingRequest parkingRequest);

	public ResponseEntity<Payments> unParkVehicle(int ticketId);
}
