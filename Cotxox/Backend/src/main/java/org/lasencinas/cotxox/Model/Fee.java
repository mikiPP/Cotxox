package org.lasencinas.cotxox.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Entity para fee que son las tarifas.
 */

@Getter
@Setter
@Entity
@Table(name = "Fee")
public class Fee {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fee_id")
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "cost")
    private double cost;

    public Fee() {
    }


}
