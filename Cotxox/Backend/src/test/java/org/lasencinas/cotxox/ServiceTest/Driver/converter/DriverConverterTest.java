package org.lasencinas.cotxox.ServiceTest.Driver.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;
import org.lasencinas.cotxox.Service.Driver.DriverConverter;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DriverConverterTest {

    @Autowired
    private DriverConverter driverConverter;

    @Autowired
    private DriverService driverService;


    @Test
    public void driverShouldReturnDriverApi() {

        /*------------------- Given --------------------- */

        Driver driver = new Driver();

        driver.setRate(4.1);
        driver.setValuations(1);
        driver.setCarPlate("1234BCD");
        driver.setModel("Ferrari v1");
        driver.setName("Test");

        /*------------------- When --------------------- */

        DriverDto driverDto = driverConverter.toApiModel(driver);

        /*------------------- Then  --------------------- */

        assertTrue(driverDto instanceof DriverDto);
        assertEquals(driver.getRate(), driverDto.getRate(), 0);
        assertEquals(driver.getCarPlate(), driverDto.getCarPlate());
        assertEquals(driver.getModel(), driverDto.getModel());
        assertEquals(driver.getName(), driverDto.getName());
        assertEquals(driver.isBussy(), driverDto.isBussy());

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        Driver driverTest = driverService.findDriverById((long) 1);

        /*------------------- When --------------------- */

        DriverDto driverDtoTest = driverConverter.toApiModel(driverTest);

        /*------------------- Then --------------------- */

        assertTrue(driverDtoTest instanceof DriverDto);
        assertEquals(driverTest.getRate(), driverDtoTest.getRate(), 0);
        assertEquals(driverTest.getCarPlate(), driverDtoTest.getCarPlate());
        assertEquals(driverTest.getModel(), driverDtoTest.getModel());
        assertEquals(driverTest.getName(), driverDtoTest.getName());
        assertEquals(driverTest.isBussy(), driverDtoTest.isBussy());


    }

}
