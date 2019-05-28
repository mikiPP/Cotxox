package org.lasencinas.cotxox.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidad para Fare que equivale a carrera en español
 */

@Getter
@Setter
@Entity
@Table(name = "Fare")
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fare_id")
    private Long id;

    @NotNull
    @Column(name = "creditCard")
    private String creditCard;

    @NotNull
    @Column(name = "origin")
    private String origin;

    @NotNull
    @Column(name = "destination")
    private String destination;

    @Column(name = "mileage ") // in kilometers
    private double mileage;

    @NotNull
    @Column(name = "time") // in minutes
    private double time;

    @Column(name = "rate")
    private double rate;

    @NotNull
    @Column(name = "cost")
    private double cost;

    @Column(name = "tip")
    private double tip;

    @ManyToOne
    private User user;

    @ManyToOne
    private Driver driver;

    public Fare() {
    }

}
