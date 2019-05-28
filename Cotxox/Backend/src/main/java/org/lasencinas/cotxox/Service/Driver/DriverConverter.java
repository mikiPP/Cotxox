package org.lasencinas.cotxox.Service.Driver;

import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;
import org.springframework.stereotype.Component;

/**
 * Clase que sirve para pasar de la entidad driver al dto
 */

@Component
public class DriverConverter {

    public DriverDto toApiModel(Driver driver) {
        DriverDto apiModel = new DriverDto();
        apiModel.setId(driver.getId());
        apiModel.setName(driver.getName());
        apiModel.setModel(driver.getModel());
        apiModel.setCarPlate(driver.getCarPlate());
        apiModel.setRate(driver.getRate());
        apiModel.setBussy(driver.isBussy());

        return apiModel;
    }

}
