package com.api.parkingcontrol.services;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {
  // @Autowired // -> injeção de dependencia
  // ParkingSpotRepository parkingSpotRepository;

  final ParkingSpotRepository parkingSpotRepository;
  public ParkingSpotService(ParkingSpotRepository parkingSpotRepository){
    this.parkingSpotRepository = parkingSpotRepository;
  }

  @Transactional
  public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
    return parkingSpotRepository.save(parkingSpotModel);
  }

  public boolean existsByLicensePlateCar(String licencePlateCar) {
    return parkingSpotRepository.findByLicencePlateCar(licencePlateCar).size() > 0;
  }

  public boolean existsByParkingStopNumber(String parkingSpotNumber) {
    return parkingSpotRepository.findByParkingSpotNumber(parkingSpotNumber).size() > 0;
  }

  public boolean existsByApartmentAndBlock(String apartment, String block) {
    return parkingSpotRepository.findByApartmentAndBlock(apartment, block).size() > 0;
  }

  public List<ParkingSpotModel> findAll() {
    return parkingSpotRepository.findAll();
  }

  public Optional<ParkingSpotModel> findById(UUID id) {
    return parkingSpotRepository.findById(id);
  }
}
