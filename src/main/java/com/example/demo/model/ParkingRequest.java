package com.example.demo.model;

import com.example.demo.enums.VehicleType;

public class ParkingRequest {
	
	private int vehicleId;
	
	private VehicleType vehicleType;
	
	private int parkingSpaceId;

	public int getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getParkingSpaceId() {
		return this.parkingSpaceId;
	}

	public void setParkingSpaceId(int parkingSpaceId) {
		this.parkingSpaceId = parkingSpaceId;
	};
}
