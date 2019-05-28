package org.lasencinas.cotxox.Service.Fare;


import org.lasencinas.cotxox.Model.Fare;
import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Repository.FareRepository;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.lasencinas.cotxox.Service.Fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fareService")
public class FareServiceImpl implements FareService {


    private FareRepository fareRepository;
    private FeeService feeService;
    private DriverService DriverService;
    private FareConverter fareConverter;

    @Autowired
    public FareServiceImpl(FareRepository fareRepository, FeeService feeService,
                           DriverService DriverService, FareConverter fareConverter) {

        this.fareRepository = fareRepository;
        this.feeService = feeService;
        this.DriverService = DriverService;
        this.fareConverter = fareConverter;
    }



    public FareDto addFare(FareUpsertDto fareUpsertDto) {

        Fare fare = fareConverter.toDomainModel(fareUpsertDto);

        fare.setCost(calculateCost(fare.getTime(), fare.getMileage()));
        DriverService.takeDriver(fare.getDriver());
        fareRepository.save(fare);
        return fareConverter.toApiModel(fare);
    }


    public String putFare(FareUpsertDto fareUpsertDto) {

        Fare fare = fareRepository.getOne(fareUpsertDto.getId());
        fare.setRate(fareUpsertDto.getRate());
        fare.setTip(fareUpsertDto.getTip());
        this.DriverService.putDriver(fare.getDriver(), fare.getRate());
        fareRepository.save(fare);
        return "edited";
    }


    public double calculateCost(double time, double mileage) {

        double cost = ( this.feeService.getCost(mileage, "Mille Cost")
                + this.feeService.getCost(time, "Minute Cost") )
                * this.feeService.getCost(time, "Commission Percentage");

        return (cost > this.feeService.getCost(1, "Minimum Cost"))
                ? cost : this.feeService.getCost(1, "Minimum Cost");
    }
}
