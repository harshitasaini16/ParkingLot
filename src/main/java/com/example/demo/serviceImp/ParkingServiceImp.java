package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payments;
import com.example.demo.entity.Slot;
import com.example.demo.entity.Ticket;
import com.example.demo.model.ParkingRequest;
import com.example.demo.service.ParkingService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.SlotService;
import com.example.demo.service.TicketService;

@Service
public class ParkingServiceImp implements ParkingService{

	private SlotService slotService;
	private TicketService ticketService;
	private PaymentService paymentService;
	
	@Autowired
	public ParkingServiceImp(SlotService slotService, TicketService ticketService, PaymentService paymentService) {
		this.slotService = slotService;
		this.ticketService = ticketService;
		this.paymentService = paymentService;
	}
	
	public ResponseEntity<Ticket> parkVehicle(ParkingRequest parkingRequest) {
		ResponseEntity<Slot> slotResponse = this.slotService.getVacantSlot(parkingRequest.getParkingSpaceId(), parkingRequest.getVehicleType());
		if(slotResponse.getStatusCode() != HttpStatus.OK || slotResponse.getBody() == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<Void> parkingResponse = this.slotService.parkVehichle(slotResponse.getBody().getId(), parkingRequest.getVehicleId());
		if(parkingResponse.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<Ticket> ticketResponse = this.ticketService.generateTicket(slotResponse.getBody().getId());
        if (ticketResponse.getStatusCode() != HttpStatus.OK || slotResponse.getBody() == null) {
            return ResponseEntity.ok(ticketResponse.getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	public ResponseEntity<Payments> unParkVehicle(int ticketId) {
		ResponseEntity<Ticket> ticketResponse = this.ticketService.getTicket(ticketId);
		System.out.println(ticketResponse.getBody().toString());
		if(ticketResponse.getStatusCode() != HttpStatus.OK || ticketResponse.getBody() == null) {
			return ResponseEntity.notFound().build();
		}
		if(ticketResponse.getBody().isPaymentDone()) {
			return ResponseEntity.badRequest().build();
		}
		Ticket ticket = ticketResponse.getBody();
		ticket.setPaymentDone(true);
		this.ticketService.updateTicket(ticketId, ticket);
		
		Slot slot = ticket.getSlot();
		System.out.println(slot.toString());
		if(slot == null) {
			return ResponseEntity.notFound().build();
		}
		slot.setParkedVehicleId(0);
		ResponseEntity<Void> slotResponse = this.slotService.updateSlot(slot.getId(),slot);
		System.out.println(slotResponse.toString());
		if(slotResponse.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<Integer> amountResponse = this.ticketService.findAmount(ticket);
		System.out.println(amountResponse.getBody().toString());
		if(amountResponse.getStatusCode() != HttpStatus.OK) {
			return ResponseEntity.badRequest().build();
		}
		Payments payment = new Payments(ticket, amountResponse.getBody());
		this.paymentService.addPayment(payment);
		return ResponseEntity.ok(payment);
	}
}
