package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Ticket;

public interface TicketService {
	
	public ResponseEntity<Ticket> generateTicket(int slotId);
	
	public ResponseEntity<List<Ticket>> getTicket();
	
	public ResponseEntity<Ticket> getTicket(int ticketId);
	
	public ResponseEntity<Ticket> updateTicket(int ticketId, Ticket updatedTicket);
	
	public ResponseEntity<Integer> findAmount(Ticket ticket);
}
