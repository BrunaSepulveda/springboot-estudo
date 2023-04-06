package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
}
