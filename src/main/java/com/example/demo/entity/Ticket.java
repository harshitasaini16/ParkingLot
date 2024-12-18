package com.example.demo.entity;

import com.example.demo.enums.VehicleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", referencedColumnName = "id")
    private ParkingLot parkingLot;

    @ManyToOne
    @JoinColumn(name = "parkingSpaceId", referencedColumnName = "id")
    private ParkingSpace parkingSpace;

    @ManyToOne
    @JoinColumn(name = "slotId", referencedColumnName = "id")
    private Slot slot;

    @Column(name = "isPaymentDone")
    private boolean isPaymentDone;

    @CreationTimestamp
    @Column(name = "createTime", nullable = false, updatable = false)
    private LocalDateTime createTime;

    public Ticket() {
    }

    public Ticket(ParkingLot parkingLot, ParkingSpace parkingSpace, Slot slot,
                  boolean isPaymentDone) {
        super();
        this.parkingLot = parkingLot;
        this.parkingSpace = parkingSpace;
        this.slot = slot;
        this.isPaymentDone = isPaymentDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public boolean isPaymentDone() {
        return isPaymentDone;
    }

    public void setPaymentDone(boolean isPaymentDone) {
        this.isPaymentDone = isPaymentDone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "Ticket [id=" + id  + ", parkingLot=" + parkingLot + ", parkingSpace="
                + parkingSpace + ", slot=" + slot + ", isPaymentDone=" + isPaymentDone + ", createTime=" + createTime
                + "]";
    }
}
