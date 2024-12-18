package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Payments;

public interface PaymentService {
	
	public ResponseEntity<Void> addPayment(Payments payment);
}
