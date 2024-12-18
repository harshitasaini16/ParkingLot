package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payments;
import com.example.demo.entity.Ticket;
import com.example.demo.model.ParkingRequest;
import com.example.demo.service.ParkingService;

@RestController
@RequestMapping("/park")
public class ParkingController {
	
	private ParkingService parkingService;
	
	@Autowired
	public ParkingController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}
	
	@PostMapping("")
	public ResponseEntity<Ticket> parkVehicle(@RequestBody ParkingRequest parkingRequest) {
		return this.parkingService.parkVehicle(parkingRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payments> unParkVehicle(@PathVariable("id") int id) {
		return this.parkingService.unParkVehicle(id);
	}
}
