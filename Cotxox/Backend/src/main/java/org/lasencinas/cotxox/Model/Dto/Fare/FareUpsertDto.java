package org.lasencinas.cotxox.Model.Dto.Fare;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object (dto) en este caso al ser un upsert, quiere decir que es un objeto que enviamos desde el front
 * hacia el backend para poder guardarlo en base de datos (insert) o actualizarlo (update)
 */

@Getter
@Setter
@Component
public class FareUpsertDto {

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


}
