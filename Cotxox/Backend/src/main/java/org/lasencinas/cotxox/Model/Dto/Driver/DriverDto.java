package org.lasencinas.cotxox.Model.Dto.Driver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object (dto) en este caso,es el objeto que enviamos al frontend.
 */

@Getter
@Setter
@Component
public class DriverDto {

    private long id;
    private String name;
    private String model;
    private String carPlate;
    private double rate;
    private boolean bussy;


}
