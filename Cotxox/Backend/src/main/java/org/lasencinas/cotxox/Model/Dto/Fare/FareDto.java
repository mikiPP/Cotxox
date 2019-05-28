package org.lasencinas.cotxox.Model.Dto.Fare;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object (dto) en este caso,es el objeto que enviamos al frontend.
 */

@Getter
@Setter
@Component
public class FareDto {


    private Long id;
    private Long userId;
    private Long driverId;
    private String creditCard;
    private String origin;
    private String destination;
    private double mileage;
    private double time;
    private double rate;
    private double tip;
    private double cost;

}
