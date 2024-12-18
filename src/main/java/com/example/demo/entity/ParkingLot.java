package com.example.demo.entity;


import com.example.demo.model.ParkingLotRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ParkingLot")
public class ParkingLot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="address")
	private String address;
	
	public ParkingLot() {}

	public ParkingLot(ParkingLotRequest parkingLotRequest) {
		super();
		this.address = parkingLotRequest.getAddress();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", address=" + address + "]";
	};
	
}
