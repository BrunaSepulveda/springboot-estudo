package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
// import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping
  public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
    //PageableDefault todos os valores são queryparams, ou seja ?page=&size=&sot=element,ASC|DESC
    return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
    Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
    if (!parkingSpotModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não achada");
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
    Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
    if (!parkingSpotModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não achada");
    } else {
      parkingSpotService.delete(parkingSpotModelOptional.get());
      return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada com sucesso");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto){
    Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
    if (!parkingSpotModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não achada");
    } else {
      // var parkingSpotModel = parkingSpotModelOptional.get();
      // parkingSpotModel.setToUpdate(parkingSpotDto);
      var parkingSpotModel = new ParkingSpotModel();
      BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
      parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
      parkingSpotModel.setregistrationDate(parkingSpotModelOptional.get().getregistrationDate());
      return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }
  }
}
