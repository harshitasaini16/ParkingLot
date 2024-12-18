package com.example.demo.dao;

import com.example.demo.entity.Ticket;

public class SimpleCalculator implements PaymentCalculator{

	private static final int BASE_RATE = 10;
    
    @Override
    public int calculateAmount(Ticket ticket) {
        long parkedTimeInHours = calculateParkedTime(ticket);
        
        int ratePerHour = switch (ticket.getSlot().getVehicleType()) {
            case TwoWheeler -> 20; 
            case FourWheeler -> 10; 
            case Heavy -> 50;
            default -> BASE_RATE;
        };
        
        return (int) (parkedTimeInHours * ratePerHour);
    }

    private long calculateParkedTime(Ticket ticket) {
        long duration = java.time.Duration.between(ticket.getCreateTime(), java.time.LocalDateTime.now()).toHours();
        return Math.max(duration, 1);
    }

}
