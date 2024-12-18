package com.example.demo.model;

public class ParkingLotRequest {
	
	private String address;
	
	public ParkingLotRequest() {};
	
	public ParkingLotRequest(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	};
}
