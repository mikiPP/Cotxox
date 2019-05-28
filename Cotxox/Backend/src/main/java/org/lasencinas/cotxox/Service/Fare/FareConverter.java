package org.lasencinas.cotxox.Service.Fare;

import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Fare;
import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Model.User;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.lasencinas.cotxox.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FareConverter {

    private final UserService userService;
    private final DriverService driverService;

    @Autowired
    public FareConverter(UserService userService, DriverService driverService) {
        this.userService = userService;
        this.driverService = driverService;
    }

    public FareDto toApiModel(Fare fare) {
        FareDto apiModel = new FareDto();
        apiModel.setCreditCard(fare.getCreditCard());
        apiModel.setOrigin(fare.getOrigin());
        apiModel.setDestination(fare.getDestination());
        apiModel.setId(fare.getId());
        apiModel.setUserId(fare.getUser().getId());
        apiModel.setDriverId(fare.getDriver().getId());
        apiModel.setCost(fare.getCost());
        apiModel.setMileage(fare.getMileage());
        apiModel.setTime(fare.getTime());

        return apiModel;
    }

    public Fare toDomainModel(FareUpsertDto fareUpsertDto) {

        Fare domainModel = new Fare();

        User user = userService.findById(fareUpsertDto.getUserId());
        Driver driver = driverService.findDriverById(fareUpsertDto.getDriverId());

        domainModel.setUser(user);
        domainModel.setDriver(driver);
        domainModel.setCreditCard(fareUpsertDto.getCreditCard());
        domainModel.setOrigin(fareUpsertDto.getOrigin());
        domainModel.setDestination(fareUpsertDto.getDestination());
        domainModel.setTip(fareUpsertDto.getTip());
        domainModel.setRate(fareUpsertDto.getRate());
        domainModel.setId(fareUpsertDto.getId());
        domainModel.setMileage(fareUpsertDto.getMileage());
        domainModel.setTime(fareUpsertDto.getTime());


        return domainModel;
    }
}
