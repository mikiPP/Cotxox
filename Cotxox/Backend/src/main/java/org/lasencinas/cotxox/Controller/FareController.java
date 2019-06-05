package org.lasencinas.cotxox.Controller;

import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareFilterDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Service.Fare.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Controller de carreras de taxi,tiene dos endpoints,uno crea la carrera y la guarda en base de datos
 * y la otra permite editar aspectos de la carrera,la idea es que despues de la carrera se pueda valorar al conductor
 * y dar una tip voluntariamente.
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
        return this.fareService.addFare(fare);
    }


    @PutMapping("/fare")
    public String putFare(@RequestBody FareUpsertDto fare) {
        return this.fareService.putFare(fare);
    }

    @GetMapping("findAll")
    public List<FareDto> findAll(Double mileage, Long driverId, Double cost) {
        FareFilterDto filterDto = new FareFilterDto();
        filterDto.setMileage(mileage);
        filterDto.setDriverId(driverId);
        filterDto.setCost(cost);

        return this.fareService.findAll(filterDto);
    }
}
