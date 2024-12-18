package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ParkingLot;
import com.example.demo.model.ParkingLotRequest;
import com.example.demo.service.ParkingLotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lot")
public class ParkingLotController {

    private ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping
    public ResponseEntity<Void> addParkingLot(@RequestBody ParkingLotRequest parkingLotRequest) {
        return parkingLotService.addParkingLot(parkingLotRequest);
    }

    @GetMapping
    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
        return parkingLotService.getParkingLot();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable int id) {
        return parkingLotService.getParkingLot(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParkingLot(@PathVariable int id) {
        return parkingLotService.deleteParkingLot(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> editParkingLot(
            @PathVariable int id,
            @RequestBody ParkingLotRequest parkingLotRequest) {
        return parkingLotService.editParkingLot(id, parkingLotRequest);
    }
}
