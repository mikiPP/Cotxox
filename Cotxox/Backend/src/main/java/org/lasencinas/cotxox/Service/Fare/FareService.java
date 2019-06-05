package org.lasencinas.cotxox.Service.Fare;

import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareFilterDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Model.Fare;

import java.util.List;


public interface FareService {

    FareDto addFare(FareUpsertDto fareUpsertDto);

    String putFare(FareUpsertDto fareUpsertDto);

    double calculateCost(double time, double mileage);

    List<FareDto> findAll(FareFilterDto filter);

    Fare findById(long id);

}