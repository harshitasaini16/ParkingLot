package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Slot;
import com.example.demo.enums.VehicleType;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

	@Query(value = "SELECT * FROM Slot s WHERE s.parking_space_id = :parkingSpaceId AND s.vehicle_type = :vehicleType AND s.parked_vehicle_id = 0 ORDER BY s.distance ASC LIMIT 1", nativeQuery = true)
	Optional<Slot> findNearestVacantSlot(
	        @Param("parkingSpaceId") int parkingSpaceId,
	        @Param("vehicleType") VehicleType vehicleType);
}
