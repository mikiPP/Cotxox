package org.lasencinas.cotxox.ServiceTest.Fare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.lasencinas.cotxox.Model.Fare;
import org.lasencinas.cotxox.Service.Fare.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(statements = {
        "truncate table fare",
        "insert into fare values (1,90.57,'12342366','Sa Ràpita',44.5,'Palma',0,44,0,5,1)",
        "insert into fare values (2,90.57,'3243254343','Sa Ràpita',44.5,'Palma',0,44,0,3,2)"
})
public class FareServiceImplTest {


    @Autowired
    private FareService fareService;


    @Test
    public void CostLessThanMinimumShouldReturnMinimum() {

        /*------------------- Given --------------------- */

        double time = 0;
        double mileage = 0;

        /*------------------- When --------------------- */

        double cost = fareService.calculateCost(time, mileage);

        /*------------------- Then  --------------------- */

        assertEquals(5, cost, 0.0);

        /*------------------- 2nd Test --------------------- */

        /*------------------- Given --------------------- */

        double timeTest = 1;
        double mileageTest = 1;

        /*------------------- When --------------------- */

        double costTest = fareService.calculateCost(timeTest, mileageTest);

        /*------------------- Then  --------------------- */

        assertEquals(5, costTest, 0.0);

    }

    @Test
    public void costBiggerThanMinimumShouldReturnCostCalculated() {

        /*------------------- Given --------------------- */

        double time = 44;
        double mileage = 44.5;

        /*------------------- When --------------------- */

        double cost = fareService.calculateCost(time, mileage);

        /*------------------- Then  --------------------- */

        assertEquals(90.57, cost, 0.0);

        /*------------------- 2nd Test --------------------- */

        /*------------------- Given --------------------- */

        double timeTest = 20;
        double mileageTest = 10;

        /*------------------- When --------------------- */

        double costTest = fareService.calculateCost(timeTest, mileageTest);

        /*------------------- Then  --------------------- */

        assertEquals(24.6, costTest, 0.0);

    }

//
//    @Test
//    public void putFareTest() {
//
//        /*------------------- Given --------------------- */
//
//        FareUpsertDto fareUpsertDto = new FareUpsertDto();
//
//        fareUpsertDto.setId((long) 1);
//        fareUpsertDto.setRate(4.1);
//        fareUpsertDto.setTip(1);
//
//        Fare fare = fareService.findById(fareUpsertDto.getId());
//
//        /*------------------- When --------------------- */
//
//        assertEquals(0, fare.getTip(), 0);
//        assertEquals(0, fare.getRate(), 0);
//
//        fareService.putFare(fareUpsertDto);
//
//        /*------------------- Then  --------------------- */
//
//
//        assertEquals(4.1, fareService.findById((long) 2).getRate(), 0);
//        assertEquals(1, fareService.findById((long) 2).getRate(), 0);
//
//
//    }

}
