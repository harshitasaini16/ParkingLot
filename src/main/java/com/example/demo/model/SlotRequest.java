package com.example.demo.model;

import com.example.demo.enums.VehicleType;

public class SlotRequest {

	private int parkingSpaceId;
	
	private VehicleType vehicleType;
	
	private int distance;

	public int getParkingSpaceId() {
		return this.parkingSpaceId;
	}

	public void setParkingSpaceId(int parkingSpaceId) {
		this.parkingSpaceId = parkingSpaceId;
	}

	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
