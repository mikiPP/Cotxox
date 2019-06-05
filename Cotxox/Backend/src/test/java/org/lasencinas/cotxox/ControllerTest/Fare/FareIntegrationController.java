package org.lasencinas.cotxox.ControllerTest.Fare;

import org.junit.Test;
import org.lasencinas.cotxox.ControllerTest.CotxoxIntegrationTest;
import org.lasencinas.cotxox.Model.Dto.Fare.FareDto;
import org.lasencinas.cotxox.Model.Dto.Fare.FareUpsertDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Sql(statements = {
        "truncate table fare",
        "insert into fare values (1,90.57,'12342366','Sa Ràpita',44.5,'Palma',0,44,0,5,1)",
        "insert into fare values (2,90.57,'3243254343','Sa Ràpita',50,'Palma',0,44,0,3,1)"
})
public class FareIntegrationController extends CotxoxIntegrationTest {


    @Override
    protected void initializeIntegrationTest() {

    }


    @Test
    public void addValidFareShouldReturnTheSameFareAndStatusOk() throws Exception {

        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(getFareUpsertDtoPost());


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/fare")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        FareDto fare = super.mapFromJson(content, FareDto.class);

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(fare instanceof FareDto);
        assertTrue(fare.getId() == 0);
    }


    @Test
    public void aadFareWithoutArgsShouldReturnBadRequest() throws Exception {

        /*------------------- Given --------------------- */

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/fare")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }


    @Test
    public void getRequestFareShouldReturnMethodNotAllowed() throws Exception {
        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(getFareUpsertDtoPost());


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/fare")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());

    }


    @Test
    public void putFareShouldRetunEdited() throws Exception {

        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(getFareUpsertDtoPut());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/fare")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();


        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(content.contains("edited"));
    }

    @Test
    public void putWhitoutArgsFareShouldRetunBadRequest() throws Exception {

        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/fare")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();



        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

    }


    @Test
    public void findAllFaresShouldReturnAllFares() throws Exception {


        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/findAll")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<FareDto> fares = super.mapFromJson(content, List.class);

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(fares.size() > 0);

    }


    @Test
    public void findAllFaresWithArgsShouldReturnAllFares() throws Exception {


        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/findAll")
                .param("mileage", "51")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<FareDto> fares = super.mapFromJson(content, List.class);

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(fares.size() == 0);

    }

    @Test
    public void findAllFaresWhitPostRequestShouldReturnMethodNotAllowed() throws Exception {

        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/findAll")
                .param("mileage", "51")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        /*------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());
    }


    private FareUpsertDto getFareUpsertDtoPost() {

        FareUpsertDto fare = new FareUpsertDto();

        fare.setId((long) 0);
        fare.setUserId((long) 1);
        fare.setDriverId((long) 2);
        fare.setCreditCard("12354353");
        fare.setOrigin("Palma");
        fare.setDestination("Test");
        fare.setMileage(10.5);
        fare.setTime(15.3);

        return fare;
    }

    private FareUpsertDto getFareUpsertDtoPut() {

        FareUpsertDto fare = new FareUpsertDto();

        fare.setId((long) 1);
        fare.setUserId((long) 1);
        fare.setDriverId((long) 1);
        fare.setCreditCard("12342366");
        fare.setOrigin("Palma");
        fare.setDestination("Sa Ràpita");
        fare.setMileage(44.5);
        fare.setTime(44);
        fare.setRate(4);
        fare.setTip(15.2);

        return fare;
    }

}
