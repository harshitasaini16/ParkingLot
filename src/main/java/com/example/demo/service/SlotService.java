package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Slot;
import com.example.demo.enums.VehicleType;
import com.example.demo.model.SlotRequest;

public interface SlotService {
	
	public ResponseEntity<Void> addSlot(SlotRequest slotRequest);
	
	public ResponseEntity<List<Slot>> getSlot();
	
	public ResponseEntity<Slot> getSlot(int id);
	
	public ResponseEntity<Void> deleteSlot(int id);
	
	public ResponseEntity<Slot> editSlot(int id, SlotRequest slotRequest);

	public ResponseEntity<Slot> getVacantSlot(int parkingSpaceId, VehicleType vehicleType);

	public ResponseEntity<Void> parkVehichle(int id, int vehicleId);
	
	public ResponseEntity<Void> updateSlot(int id, Slot slot);
}
