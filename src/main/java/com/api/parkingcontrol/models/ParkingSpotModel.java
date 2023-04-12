package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.api.parkingcontrol.dtos.ParkingSpotDto;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable{
  // conversões que serão realizadas no bd de obj para bytes
  // controle de versão da jvm
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true, length = 10)
  private String parkingSpotNumber;

  @Column(nullable = false, unique = true, length = 7)
  private String licencePlateCar;

  @Column(nullable = false, length = 70)
  private String brandCar;

  @Column(nullable = false, length = 70)
  private String colorCar;

  @Column(nullable = false)
  private LocalDateTime registrationDate;

  @Column(nullable = false, length = 130)
  private String responsibleName;

  @Column(nullable = false, length = 30)
  private String apartment;

  @Column(nullable = false, length = 30)
  private String block;

  public UUID getId(){
    return id;
  }

  public void setId(UUID id){
    this.id = id;
  }

  public String getparkingSpotNumber(){
    return parkingSpotNumber;
  }

  public void setparkingSpotNumber(String parkingSpotNumber){
    this.parkingSpotNumber = parkingSpotNumber;
  }

  public String getlicencePlateCar(){
    return licencePlateCar;
  }
  
  public void setlicencePlateCar(String licencePlateCar){
    this.licencePlateCar = licencePlateCar;
  }

  public String getbrandCar(){
    return brandCar;
  }

  public void setbrandCar(String brandCar){
    this.brandCar = brandCar;
  }

  public String getcolorCar(){
    return colorCar;
  }

  public void setcolorCar(String colorCar){
    this.colorCar = colorCar;
  }

  public LocalDateTime getregistrationDate(){
    return registrationDate;
  }

  public void setregistrationDate(LocalDateTime registrationDate){
    this.registrationDate = registrationDate;
  }

  public String getresponsibleName(){
    return responsibleName;
  }

  public void setresponsibleName(String responsibleName){
    this.responsibleName = responsibleName;
  }

  public String getapartment(){
    return apartment;
  }

  public void setapartment(String apartment){
    this.apartment = apartment;
  }

  public String getblock(){
    return block;
  }

  public void setblock(String block){
    this.block = block;
  }

  // public void setToUpdate(ParkingSpotDto parkingSpotDto){
  //   setapartment(parkingSpotDto.getapartment());
  //   setblock(parkingSpotDto.getblock());
  //   setcolorCar(parkingSpotDto.getcolorCar());
  //   setbrandCar(parkingSpotDto.getbrandCar());
  //   setlicencePlateCar(parkingSpotDto.getlicencePlateCar());
  //   setparkingSpotNumber(parkingSpotDto.getparkingSpotNumber());
  //   setresponsibleName(parkingSpotDto.getresponsibleName());
  // }
}
