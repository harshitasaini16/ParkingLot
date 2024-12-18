package com.example.demo.model;

public class ParkingSpaceRequest {
	
	private int parkingLotId;
	
	public ParkingSpaceRequest() {}

	public ParkingSpaceRequest(int parkingLotId) {
		super();
		this.parkingLotId = parkingLotId;
	}

	public int getParkingLotId() {
		return parkingLotId;
	}

	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}
}
