package org.lasencinas.cotxox.Controller;

import org.lasencinas.cotxox.Model.Fee;
import org.lasencinas.cotxox.Service.Fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para tarifa, tiene un único endpoint que devuelve todas las tarifas.
 */

@RestController()
@RequestMapping(path = "/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @GetMapping("/fee")
    public List<Fee> getAll() {
        return feeService.findAll();
    }

}
