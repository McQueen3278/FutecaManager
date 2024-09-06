package com.harolrodriguez.FutecaManagger.model;

import java.sql.Date;

import com.harolrodriguez.FutecaManagger.utils.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Reservation {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @FutureOrPresent
    private Date start;// Hora universl -> GMT
    @NotBlank
    @FutureOrPresent
    private Date end;
    private String payment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotBlank
    @ManyToOne //por defecto tiene poblacion de datos 
    private User user;
    @NotBlank
    @ManyToOne
    private SoccerField soccerField;
}
