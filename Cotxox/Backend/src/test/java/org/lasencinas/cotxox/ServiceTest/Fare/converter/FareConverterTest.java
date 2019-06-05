package org.lasencinas.cotxox.ServiceTest.Fare.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Model.Fare;
import org.lasencinas.cotxox.Model.User;
import org.lasencinas.cotxox.Service.Fare.FareConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(statements = {
        "delete from user",
        "insert into user values (1,0,'email@test.com','test','12345678','usernameTest')",
        "insert into user values (2,1,'test@test.com','test2','87654321','testUsername')"
})
public class FareConverterTest {


    @Autowired
    private FareConverter fareConverter;


    @Test
    public void convertUpsertDtoShouldReturnModel() {

        /*------------------- Given --------------------- */

        FareUpsertDto fareUpsertDto = new FareUpsertDto();
        fareUpsertDto.setUserId((long) 1);
        fareUpsertDto.setDriverId((long) 2);
        fareUpsertDto.setCreditCard("12314324345");
        fareUpsertDto.setOrigin("Palma");
        fareUpsertDto.setDestination("Andratx");
        fareUpsertDto.setMileage(10);
        fareUpsertDto.setTime(20);
        fareUpsertDto.setRate(4.2);
        fareUpsertDto.setTip(3.5);

        /*------------------- When --------------------- */

        Fare fare = fareConverter.toDomainModel(fareUpsertDto);

        /*------------------- Then  --------------------- */

        assertTrue(fare instanceof Fare);
        assertTrue(fare.getUser() instanceof User);
        assertTrue(fare.getDriver() instanceof Driver);
        assertEquals(fare.getCreditCard(), fareUpsertDto.getCreditCard());
        assertEquals(fare.getOrigin(), fareUpsertDto.getOrigin());
        assertEquals(fare.getDestination(), fareUpsertDto.getDestination());
        assertEquals(fare.getMileage(), fareUpsertDto.getMileage(), 0);
        assertEquals(fare.getTime(), fareUpsertDto.getTime(), 0);
        assertEquals(fare.getRate(), fareUpsertDto.getRate(), 0);
        assertEquals(fare.getTip(), fareUpsertDto.getTip(), 0);

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        FareUpsertDto fareUpsertDtoTest = new FareUpsertDto();

        fareUpsertDtoTest.setUserId((long) 1);
        fareUpsertDtoTest.setDriverId((long) 1);
        fareUpsertDtoTest.setCreditCard("324326534534564564564564564564");
        fareUpsertDtoTest.setOrigin("Palma");
        fareUpsertDtoTest.setDestination("Pollença");
        fareUpsertDtoTest.setMileage(15);
        fareUpsertDtoTest.setTime(25);
        fareUpsertDtoTest.setRate(3.21);
        fareUpsertDtoTest.setTip(4.99);

        /*------------------- When --------------------- */

        Fare fareTest = fareConverter.toDomainModel(fareUpsertDtoTest);

        /*------------------- Then --------------------- */

        assertTrue(fareTest instanceof Fare);
        assertTrue(fareTest.getUser() instanceof User);
        assertTrue(fareTest.getDriver() instanceof Driver);
        assertEquals(fareTest.getCreditCard(), fareUpsertDtoTest.getCreditCard());
        assertEquals(fareTest.getOrigin(), fareUpsertDtoTest.getOrigin());
        assertEquals(fareTest.getDestination(), fareUpsertDtoTest.getDestination());
        assertEquals(fareTest.getMileage(), fareUpsertDtoTest.getMileage(), 0);
        assertEquals(fareTest.getTime(), fareUpsertDtoTest.getTime(), 0);
        assertEquals(fareTest.getRate(), fareUpsertDtoTest.getRate(), 0);
        assertEquals(fareTest.getTip(), fareUpsertDtoTest.getTip(), 0);


    }


    @Test
    public void convertFareShouldReturnApiModel() {

        /*------------------- Given --------------------- */

        User user = new User();
        user.setEmail("pruebaTest@gmail.com");
        user.setName("pruebaTest");
        user.setPassword("12345678");
        user.setUsername("patata");

        Driver driver = new Driver();
        driver.setRate(4.1);
        driver.setValuations(1);
        driver.setCarPlate("1234BCD");
        driver.setModel("Ferrari v1");
        driver.setName("Test");

        Fare fare = new Fare();
        fare.setUser(user);
        fare.setDriver(driver);
        fare.setCreditCard("12314324345");
        fare.setOrigin("Palma");
        fare.setDestination("Andratx");
        fare.setMileage(10);
        fare.setTime(20);
        fare.setRate(4.2);
        fare.setTip(3.5);

        /*------------------- When --------------------- */

        FareDto fareDto = fareConverter.toApiModel(fare);

        /*------------------- Then  --------------------- */

        assertTrue(fareDto instanceof FareDto);
        assertEquals(fare.getUser().getId(), fareDto.getUserId());
        assertTrue(fareDto.getDriverId().compareTo(fare.getDriver().getId()) == 0);
        assertEquals(fare.getCreditCard(), fareDto.getCreditCard());
        assertEquals(fare.getOrigin(), fareDto.getOrigin());
        assertEquals(fare.getDestination(), fareDto.getDestination());
        assertEquals(fare.getMileage(), fareDto.getMileage(), 0);
        assertEquals(fare.getTime(), fareDto.getTime(), 0);


        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        User userTest = new User();
        user.setEmail("Test@gmail.com");
        user.setName("Test");
        user.setPassword("12345678");
        user.setUsername("patataTest");

        Driver driverTest = new Driver();
        driver.setRate(4.5);
        driver.setValuations(3);
        driver.setCarPlate("5678EFG");
        driver.setModel("Ferrari v2");
        driver.setName("TestDriver");


        Fare fareTest = new Fare();

        fareTest.setUser(userTest);
        fareTest.setDriver(driverTest);
        fareTest.setCreditCard("324326534534564564564564564564");
        fareTest.setOrigin("Palma");
        fareTest.setDestination("Pollença");
        fareTest.setMileage(15);
        fareTest.setTime(25);
        fareTest.setRate(3.21);
        fareTest.setTip(4.99);

        /*------------------- When --------------------- */

        FareDto fareDtoTest = fareConverter.toApiModel(fareTest);

        /*------------------- Then --------------------- */

        assertTrue(fareDtoTest instanceof FareDto);
        assertEquals(fareTest.getUser().getId(), fareDtoTest.getUserId());
        assertTrue(fareDtoTest.getDriverId().compareTo(fareTest.getDriver().getId()) == 0);
        assertEquals(fareTest.getCreditCard(), fareDtoTest.getCreditCard());
        assertEquals(fareTest.getOrigin(), fareDtoTest.getOrigin());
        assertEquals(fareTest.getDestination(), fareDtoTest.getDestination());
        assertEquals(fareTest.getMileage(), fareDtoTest.getMileage(), 0);
        assertEquals(fareTest.getTime(), fareDtoTest.getTime(), 0);

    }
}
