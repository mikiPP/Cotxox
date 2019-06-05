package org.lasencinas.cotxox.ServiceTest.Fee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Service.Fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(statements = {
        "delete from fee",
        "insert into fee values (1, 1.35, 'Mille Cost')",
        "insert into fee values (2, 0.35, 'Minute Cost')",
        "insert into fee values (3, 5, 'Minimum Cost')",
        "insert into fee values (4, 0.2, 'Commission Percentage')"
})
public class FeeServiceImplTest {


    @Autowired
    private FeeService feeService;

    @Test
    public void feeNameShouldReturnCost() {

        /*------------------- Given --------------------- */
        String feeName = "Mille Cost";
        double multipiler = 2;

        /*------------------- When --------------------- */

        double result = feeService.getCost(multipiler, feeName);

        /*------------------- Then  --------------------- */

        assertEquals(2.7, result, 0);

        /*------------------- 2nd Test --------------------- */

        /*------------------- Given --------------------- */

        String feeName2 = "Minute Cost";
        double multipiler2 = 5;



        /*------------------- When --------------------- */

        double result2 = feeService.getCost(multipiler2, feeName2);

        /*------------------- Then  --------------------- */

        assertEquals(1.75, result2, 0);


    }
}
