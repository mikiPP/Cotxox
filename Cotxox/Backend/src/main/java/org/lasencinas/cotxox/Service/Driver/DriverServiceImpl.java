package org.lasencinas.cotxox.Service.Driver;

import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;
import org.lasencinas.cotxox.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("driverService")
public class DriverServiceImpl implements DriverService {


    private DriverRepository driverRepository;
    private DriverConverter driverConverter;

    //-------------------------------------- Constructor --------------------------------------------------------//

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, DriverConverter driverConverter) {
        this.driverRepository = driverRepository;
        this.driverConverter = driverConverter;
    }

    //-------------------------------------- Interfaz pública --------------------------------------------------------//


    public List<DriverDto> findAll() {
        List<DriverDto> drivers = new ArrayList<>();

        for (Driver driver : driverRepository.findAll()) {
            drivers.add(this.driverConverter.toApiModel(driver));
        }

        return drivers;
    }


    public Driver findDriverById(Long id) {
        return driverRepository.findById(id).get();
    }


    public DriverDto findById(Long id) {
        return driverConverter.toApiModel(driverRepository.findById(id).get());
    }


    public void releaseDriver(Driver driver) {
        driver.setBussy(false);
        driverRepository.save(driver);

    }


    public void takeDriver(Driver driver) {

        driver.setBussy(true);
        driverRepository.save(driver);
    }


    public List<DriverDto> findAllbyBussy() {

        List<DriverDto> driversActive = new ArrayList<>();

        for (Driver driver : driverRepository.findByBussyIsFalse()) {
            driversActive.add(this.driverConverter.toApiModel(driver));
        }

        return driversActive;
    }


    public void putDriver(Driver driver, double rate) {
        this.releaseDriver(driver);
        driver.setValuations(1);
        driver.setRate(rate);
        this.driverRepository.save(driver);
    }


}
