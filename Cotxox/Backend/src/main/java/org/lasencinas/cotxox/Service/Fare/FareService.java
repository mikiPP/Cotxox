package org.lasencinas.cotxox.Service.Fare;

import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;


public interface FareService {

    FareDto addFare(FareUpsertDto fareUpsertDto);

    String putFare(FareUpsertDto fareUpsertDto);

    double calculateCost(double time, double mileage);
}