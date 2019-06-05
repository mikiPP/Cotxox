package org.lasencinas.cotxox.ServiceTest.Driver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Driver;
import org.lasencinas.cotxox.Service.Driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(statements = {
        "delete from driver",
        "insert into driver values (1, 0, '8255 HVT', '500 Abarth', 'Érik Vila Diaz', 4.1, 1)",
        "insert into driver values (2, 1, '8221 LJS', 'Alfa Romeo Giulietta', 'Elena Perez Crespo', 4.35, 1);"
})
public class DriverServiceImplTest {


    @Autowired
    public DriverService driverService;

    @Test
    public void releaseDriverShouldSetDriverBussyToFalse() {

        /*------------------- Given --------------------- */
        Driver driver = driverService.findDriverById((long) 1);

        driver.setBussy(true);


        /*------------------- When --------------------- */

        driverService.releaseDriver(driver);


        /*------------------- Then --------------------- */

        assertFalse(driver.isBussy());


        /*------------------- Given --------------------- */
        Driver driverTest = driverService.findDriverById((long) 2);

        driver.setBussy(true);


        /*------------------- When --------------------- */

        driverService.releaseDriver(driverTest);


        /*------------------- Then --------------------- */

        assertFalse(driverTest.isBussy());
    }


    @Test
    public void TakeDriverShouldSetDriverBussyToTrue() {

        /*------------------- Given --------------------- */
        Driver driver = driverService.findDriverById((long) 1);

        driver.setBussy(false);


        /*------------------- When --------------------- */

        driverService.takeDriver(driver);


        /*------------------- Then --------------------- */

        assertTrue(driver.isBussy());

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        Driver driverTest = driverService.findDriverById((long) 2);

        driver.setBussy(false);

        /*------------------- When --------------------- */

        driverService.takeDriver(driverTest);

        /*------------------- Then --------------------- */

        assertTrue(driverTest.isBussy());
    }

    @Test
    public void putDriverTest() {

        /*------------------- Given --------------------- */

        Driver driver = driverService.findDriverById((long) 1);

        /*------------------- When --------------------- */

        assertEquals(1, driver.getValuations());
        assertEquals(4.1, driver.getRate(), 0);
        driverService.putDriver(driver, 4.9);

        /*------------------- Then --------------------- */

        assertEquals(2, driver.getValuations());
        assertEquals(4.5, driver.getRate(), 0);


        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        Driver driverTest = driverService.findDriverById((long) 2);

        /*------------------- When --------------------- */

        assertEquals(1, driverTest.getValuations());
        assertEquals(4.35, driverTest.getRate(), 0);
        driverService.putDriver(driverTest, 3.20);



        /*------------------- Then --------------------- */

        assertEquals(2, driverTest.getValuations());
        assertEquals(3.775, driverTest.getRate(), 0);

    }

    @Test
    public void findAllByBussyShouldReturnsDriversIsBussyEqualsFalse() {

        /*------------------- Given --------------------- */

        Driver driver = driverService.findDriverById((long) 1);
        Driver driverTest = driverService.findDriverById((long) 2);

        /*------------------- When --------------------- */

        driverService.takeDriver(driver);
        driverService.releaseDriver(driverTest);

        /*------------------- Then --------------------- */

        assertEquals(1, driverService.findAllbyBussy().size());
        assertTrue(driverService.findAllbyBussy().get(0).getId() == driverTest.getId());
        assertFalse(driverService.findAllbyBussy().get(0).getId() == driver.getId());

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        //the same objects than in the first test but with other values

        /*------------------- When --------------------- */


        driverService.takeDriver(driverTest);
        driverService.releaseDriver(driver);

        /*------------------- Then --------------------- */

        assertEquals(1, driverService.findAllbyBussy().size());
        assertTrue(driverService.findAllbyBussy().get(0).getId() == driver.getId());
        assertFalse(driverService.findAllbyBussy().get(0).getId() == driverTest.getId());
    }

}
