package com.example.demo.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payments;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;
@Service
public class PaymentServiceImp implements PaymentService {

	private PaymentRepository paymentRepository;
	
	@Autowired
	public PaymentServiceImp(PaymentRepository paymentRepository) {
		this.paymentRepository= paymentRepository;
	}
	
	@Override
	public ResponseEntity<Void> addPayment(Payments payment) {
		// TODO Auto-generated method stub
		this.paymentRepository.save(payment);
		return ResponseEntity.ok().build();
	}

}
