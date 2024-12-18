package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Slot;
import com.example.demo.model.SlotRequest;
import com.example.demo.service.SlotService;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;

    @Autowired
    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<Void> addSlot(@RequestBody SlotRequest slotRequest) {
        return slotService.addSlot(slotRequest);
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        return slotService.getSlot();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlotById(@PathVariable int id) {
        return slotService.getSlot(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable int id) {
        return slotService.deleteSlot(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slot> editSlot(@PathVariable int id, @RequestBody SlotRequest slotRequest) {
        return slotService.editSlot(id, slotRequest);
    }
}
