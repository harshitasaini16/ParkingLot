package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ParkingSpace")
public class ParkingSpace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "parkingLotId", referencedColumnName = "id")
	private ParkingLot parkingLot;
	
	public ParkingSpace( ) {}

	public ParkingSpace(ParkingLot parkingLot) {
		super();
		this.parkingLot = parkingLot;
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

	@Override
	public String toString() {
		return "ParkingSpace [id=" + id + ", parkingLot=" + parkingLot + "]";
	}
	
}
