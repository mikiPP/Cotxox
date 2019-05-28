package org.lasencinas.cotxox.Controller;

import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Service.Fare.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller de carreras de taxi,tiene dos endpoints,uno crea la carrera y la guarda en base de datos
 * y la otra permite editar aspectos de la carrera,la idea es que despues de la carrera se pueda valorar al conductor
 * y dar una tip voluntariamente.
 *
 */


@RestController
@RequestMapping("/api")
public class FareController {

    private FareService fareService;

    @Autowired
    public FareController(FareService fareService) {

        this.fareService = fareService;
    }

    @PostMapping("/fare")
    public FareDto addFare(@RequestBody FareUpsertDto fare) {
        System.out.println("llego!");
        return this.fareService.addFare(fare);
    }


    @PutMapping("/fare")
    public String putFare(@RequestBody FareUpsertDto fare) {
        return this.fareService.putFare(fare);
    }
}
