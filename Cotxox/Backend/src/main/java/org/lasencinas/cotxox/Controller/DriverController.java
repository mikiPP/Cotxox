package org.lasencinas.cotxox.Controller;

import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para el conductor,tiene 3 endpoints, el primero devuelve todos los conductores,el segundo un conductor
 * por su id y el tercero,devuelve una lista de todos los conductores disponibles.
 *
 */

@RequestMapping(path = "/api")
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/driver")
    public List<DriverDto> getAll() {
        return driverService.findAll();
    }

    @GetMapping("/driver/{id}")
    public DriverDto getDriverById(@PathVariable Long id) {
        return driverService.findById(id);
    }

    @GetMapping("driver/active")
    public List<DriverDto> getAllActive() {
        return driverService.findAllbyBussy();
    }
}
