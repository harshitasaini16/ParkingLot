package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ParkingSpace;
import com.example.demo.model.ParkingSpaceRequest;
import com.example.demo.service.ParkingSpaceService;

@RestController
@RequestMapping("/api/parking-space")
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    @Autowired
    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @PostMapping
    public ResponseEntity<Void> addParkingSpace(@RequestBody ParkingSpaceRequest parkingSpaceRequest) {
        return parkingSpaceService.addParkingSpace(parkingSpaceRequest);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getParkingSpaces() {
        return parkingSpaceService.getParkingSpace();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getParkingSpace(@PathVariable int id) {
        return parkingSpaceService.getParkingSpace(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable int id) {
        return parkingSpaceService.deleteParkingSpace(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpace> editParkingSpace(
            @PathVariable int id, 
            @RequestBody ParkingSpaceRequest parkingSpaceRequest) {
        return parkingSpaceService.editParkingSpace(id, parkingSpaceRequest);
    }
}
