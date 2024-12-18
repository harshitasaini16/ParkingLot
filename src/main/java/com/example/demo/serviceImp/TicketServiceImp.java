package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentCalculator;
import com.example.demo.dao.SimpleCalculator;
import com.example.demo.entity.Slot;
import com.example.demo.entity.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.SlotService;
import com.example.demo.service.TicketService;
@Service
public class TicketServiceImp implements TicketService{
	
	private TicketRepository ticketRepository;
	private SlotService slotService;
	private PaymentCalculator paymentCalculator;
	
	@Autowired
	public TicketServiceImp(TicketRepository ticketRepository, SlotService slotService) {
		this.ticketRepository = ticketRepository;
		this.slotService = slotService;
		this.paymentCalculator = new SimpleCalculator();
	}
	
	@Override
	public ResponseEntity<List<Ticket>> getTicket() {
		List<Ticket> ticket = this.ticketRepository.findAll();
        return ResponseEntity.ok(ticket);
	}
	
	@Override
	public ResponseEntity<Ticket> getTicket(int ticketId) {
		Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
	}
	
	@Override
	public ResponseEntity<Ticket> generateTicket(int slotId) {

		ResponseEntity<Slot> slotOptional = this.slotService.getSlot(slotId);


	    if (slotOptional.getStatusCode() != HttpStatus.OK || slotOptional.getBody() == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(null);
	    }

	    Slot slot = slotOptional.getBody();

	    Ticket ticket = new Ticket();
	    ticket.setSlot(slot);
	    ticket.setParkingSpace(slot.getParkingSpace());
	    ticket.setParkingLot(slot.getParkingSpace().getParkingLot());
	    ticket.setPaymentDone(false);
	    Ticket savedTicket = ticketRepository.save(ticket);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(savedTicket);
	}
	
	@Override
    public ResponseEntity<Ticket> updateTicket(int ticketId, Ticket updatedTicket) {
        Optional<Ticket> existingTicketOpt = ticketRepository.findById(ticketId);
        
        if (!existingTicketOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Ticket existingTicket = existingTicketOpt.get();
        
        if (updatedTicket.getSlot() != null) {
            existingTicket.setSlot(updatedTicket.getSlot());
        }
        if (updatedTicket.getParkingSpace() != null) {
            existingTicket.setParkingSpace(updatedTicket.getParkingSpace());
        }
        if (updatedTicket.getParkingLot() != null) {
            existingTicket.setParkingLot(updatedTicket.getParkingLot());
        }
        if (updatedTicket.isPaymentDone() != false) {
            existingTicket.setPaymentDone(updatedTicket.isPaymentDone());
        }
        Ticket savedTicket = ticketRepository.save(existingTicket);
        return ResponseEntity.ok(savedTicket);
    }

	@Override
	public ResponseEntity<Integer> findAmount(Ticket ticket) {
		// TODO Auto-generated method stub
		int amount = this.paymentCalculator.calculateAmount(ticket);
		return ResponseEntity.ok(amount);
	}
}
