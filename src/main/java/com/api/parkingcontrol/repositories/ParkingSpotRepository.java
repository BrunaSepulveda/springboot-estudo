package com.api.parkingcontrol.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.models.ParkingSpotModel;
//SERÁ UM BEAN DO SPRING NÃO É OBRIGATÓRIO DO REPOSITORY

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
  
  List<ParkingSpotModel> findByLicencePlateCar(String licencePlateCar);
  List<ParkingSpotModel> findByParkingSpotNumber(String parkingSpotNumber);
  List<ParkingSpotModel> findByApartmentAndBlock(String apartment, String block);
}
