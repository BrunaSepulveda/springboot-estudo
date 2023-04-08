package com.api.parkingcontrol.dtos;
//spring validator
// estudar contraints personalizadas
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParkingSpotDto {

  @NotBlank
  private String parkingSpotNumber;

  @NotBlank
  @Size(max = 7, min = 7, message = "A placa deve ter 7 caracteres")
  private String licencePlateCar;

  @NotBlank
  private String brandCar;

  @NotBlank
  private String colorCar;

  @NotBlank
  private String responsibleName;

  @NotBlank
  private String apartment;

  @NotBlank
  private String block;


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
}
