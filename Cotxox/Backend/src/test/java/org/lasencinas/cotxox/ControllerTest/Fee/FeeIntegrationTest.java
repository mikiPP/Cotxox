package org.lasencinas.cotxox.ControllerTest.Fee;

import org.junit.Test;
import org.lasencinas.cotxox.ControllerTest.CotxoxIntegrationTest;
import org.lasencinas.cotxox.Model.Fee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class FeeIntegrationTest extends CotxoxIntegrationTest {


    @Override
    protected void initializeIntegrationTest() {

    }

    @Test
    public void GetgetAllShouldReturnOk() throws Exception {

        /*------------------- Given --------------------- */



        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/fee")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Fee> fees = super.mapFromJson(content, List.class);


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(fees.size() > 0);

    }

    @Test
    public void PostAllShouldReturnNotAllowed() throws Exception {

        /*------------------- Given --------------------- */



        /*------------------- When --------------------- */


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/fee")
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());
    }

}
