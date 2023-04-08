package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")// pode ser a nível de classe ou de método
public class ParkingSpotController {

  final ParkingSpotService parkingSpotService;

  public ParkingSpotController(ParkingSpotService parkingSpotService){
    this.parkingSpotService = parkingSpotService;
  }

  @PostMapping
  public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
    //@RequestBody -> receber via json no body | @Valid -> executa as contrainst do dto
    if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getlicencePlateCar())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License plate car is alredy in use!");
    }
    if (parkingSpotService.existsByParkingStopNumber(parkingSpotDto.getparkingSpotNumber())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot is alredy in use!");
    }
    if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getapartment(), parkingSpotDto.getblock())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot is alredy resistered for this adress!");
    }
    var parkingSpotModel = new ParkingSpotModel();
    BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
    // converter dto em model (o que, para o que)
    parkingSpotModel.setregistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
  }
  
}
