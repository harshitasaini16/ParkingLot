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

@Entity
@Table(name="Slot")
public class Slot {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="parkingSpaceId", referencedColumnName = "id")
	private ParkingSpace parkingSpace;
	
	@Column(name="parkedVehicleId")
	private int parkedVehicleId;
	
	@Column(name="vehicleType")
	private VehicleType vehicleType;
	
	@Column(name="distance")
	private int distance;
	
	public Slot(){}

	public Slot(ParkingSpace parkingSpace, VehicleType vehicleType,
			int distance) {
		super();
		this.parkingSpace = parkingSpace;
		this.vehicleType = vehicleType;
		this.distance = distance;
		this.parkedVehicleId = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public int getParkedVehicleId() {
		return parkedVehicleId;
	}

	public void setParkedVehicleId(int parkedVehicleId) {
		this.parkedVehicleId = parkedVehicleId;
	}

	public VehicleType getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", parkingSpace=" + parkingSpace + ", parkedVehicleId="
				+ parkedVehicleId + ", vehicleType=" + vehicleType + ", distance=" + distance + "]";
	};
	
	
}
