package com.example.demo.dao;

import com.example.demo.entity.Ticket;

public interface PaymentCalculator {
	int calculateAmount(Ticket ticket);
}
