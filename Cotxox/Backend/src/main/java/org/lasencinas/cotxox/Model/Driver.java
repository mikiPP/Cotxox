package org.lasencinas.cotxox.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entidad de Driver / Driver
 */

@Getter
@Setter
@Entity
@Table(name = "Driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "carPlate")
    private String carPlate;

    @Column(name = "rate")
    private double rate;

    @Column(name = "bussy")
    private boolean bussy;

    @Column(name = "valuations")
    private int valuations;

    @OneToMany(mappedBy = "driver")
    private List<Fare> fares;


    public Driver() {
    }


    public void setRate(double rate) {
        this.rate = (this.rate + getRate()) / getValuations();
    }

    public void setValuations(int valuations) {
        this.valuations += valuations;
    }
}
