package org.lasencinas.cotxox.ControllerTest.Driver;

import org.junit.Assert;
import org.junit.Test;
import org.lasencinas.cotxox.ControllerTest.CotxoxIntegrationTest;
import org.lasencinas.cotxox.Model.Dto.Driver.DriverDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Sql(statements = {
        "delete from driver",
        "insert into driver values (1, 0, '8255 HVT', '500 Abarth', 'Érik Vila Diaz', 4.1, 1)",
        "insert into driver values (2, 1, '8221 LJS', 'Alfa Romeo Giulietta', 'Elena Perez Crespo', 4.35, 1);"
})
public class DriverIntegrationController extends CotxoxIntegrationTest {


    @Override
    protected void initializeIntegrationTest() {

    }


    @Test
    public void getAllShouldReturnAllDrivers() throws Exception {


        /*------------------- Given --------------------- */



        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/driver")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<DriverDto> drivers = super.mapFromJson(content, List.class);

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(drivers.size() > 0);

    }

    @Test
    public void postAllShouldReturnMethodNotAllowed() throws Exception {


        /*------------------- Given --------------------- */



        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/driver")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());

    }


    @Test
    public void getAllActiveDriversShouldReturnAllActiveDrivers() throws Exception {


        /*------------------- Given --------------------- */



        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/driver/active")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<DriverDto> drivers = super.mapFromJson(content, List.class);

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(drivers.size() > 0);
    }


    @Test
    public void postAllActiveDriversShouldReturnMethodNotAllowed() throws Exception {

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/driver/active")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    public void getDriverByIdShouldReturnDriver() throws Exception {
        /*------------------- When --------------------- */

        long ID = 1;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/driver/" + ID)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        DriverDto driver = super.mapFromJson(content, DriverDto.class);


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(driver instanceof DriverDto);
        assertEquals(ID, driver.getId());

    }

    @Test(expected = Exception.class)
    public void badIdShouldReturnNoSuchElementExeption() throws Exception {

        long ID = -1;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/driver/" + ID)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();



        /*-------------------- Then --------------------- */

        Assert.fail("User doesn't Exist");

    }
}
