package org.lasencinas.cotxox.Service.Fare;


import com.querydsl.core.BooleanBuilder;
import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareFilterDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Model.Fare;
import org.lasencinas.cotxox.Predicates.FarePredicates;
import org.lasencinas.cotxox.Repository.FareRepository;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.lasencinas.cotxox.Service.Fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

        double cost = (this.feeService.getCost(mileage, "Mille Cost")
                + this.feeService.getCost(time, "Minute Cost"));

        cost += this.feeService.getCost(cost, "Commission Percentage");


        return (cost > this.feeService.getCost(1, "Minimum Cost"))
                ? cost : this.feeService.getCost(1, "Minimum Cost");
    }

    public List<FareDto> findAll(FareFilterDto filter) {

        BooleanBuilder booleanBuilder = createBooleanBuilder(filter);

        Iterable<Fare> fares = fareRepository.findAll(booleanBuilder);

        return StreamSupport.stream(fares.spliterator(), false)
                .map(fare -> fareConverter.toApiModel(fare))
                .collect(Collectors.toList());
    }

    public Fare findById(long id){

        return this.fareRepository.findById(id).get();
    }


    private BooleanBuilder createBooleanBuilder(FareFilterDto filter) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (filter.getDriverId() != null) {
            booleanBuilder.and(FarePredicates.idDriverIs(filter.getDriverId()));
        }

        if (filter.getCost() != null) {
            booleanBuilder.and(FarePredicates.CostIsGreaterThan(filter.getCost()));
        }

        if (filter.getMileage() != null) {
            booleanBuilder.and(FarePredicates.mileageIsGreaterThan(filter.getMileage()));
        }

        return booleanBuilder;
    }
}
