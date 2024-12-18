package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Ticket;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    private TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
    	this.ticketService = ticketService;
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable int ticketId) {
        return ticketService.getTicket(ticketId);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Ticket>> getTicket() {
        return ticketService.getTicket();
    }
}