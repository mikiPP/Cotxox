package org.lasencinas.cotxox.Service.Driver;

import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;

import java.util.List;


public interface DriverService {


    List<DriverDto> findAll();

    Driver findDriverById(Long id);

    DriverDto findById(Long id);

    void releaseDriver(Driver driver);

    void takeDriver(Driver driver);

    List<DriverDto> findAllbyBussy();

    void putDriver(Driver driver, double rate);
}
