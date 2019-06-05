package org.lasencinas.cotxox.ControllerTest.User;

import org.junit.Test;
import org.lasencinas.cotxox.ControllerTest.CotxoxIntegrationTest;
import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Sql(statements = {
        "truncate user",
        "insert into user values (1,0,'email@test.com','test','12345678','usernameTest')"
})
public class UserIntegrationTest extends CotxoxIntegrationTest {

    @Autowired
    private UserService userService;

    @Override
    protected void initializeIntegrationTest() {
    }


    @Test
    public void postSignUpShouldReturnOK() throws Exception {

        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(createNewUser());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(content.contains("Added"));

    }


    @Test
    public void getSignUpShouldReturnNotAllowed() throws Exception {

        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(createNewUser());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/signup")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();



        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());


    }


    @Test
    public void ifUserExixstsShouldReturnMessageTellingIt() throws Exception {

        /*------------------- Given --------------------- */

        String inputJson = super.mapToJson(getUserThatAlreadyExist());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(content.contains("The user already exists"));
        assertFalse(content.contains("Added"));
    }


    @Test
    public void ifRequestHasAnyParamShouldReturnBadRequest() throws Exception {

        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

    }


    @Test
    public void ifUserHasValidCredentialsShouldReturnLogged() throws Exception {

        /*------------------- Given --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setPassword("12345678");
        user.setUsername("TestUsername");

        String inputJson = super.mapToJson(user);
        userService.addUser(createNewUser());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(content.contains("Logged"));
        assertFalse(content.contains("User or password is wrong"));

    }

    @Test
    public void ifUserHasInValidCredentialsShouldReturnUserOrPasswordIsWrong() throws Exception {

        /*------------------- Given --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setPassword("12345678");
        user.setUsername("Username");

        String inputJson = super.mapToJson(user);
        userService.addUser(createNewUser());

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        String content = mvcResult.getResponse().getContentAsString();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(content.contains("User or password is wrong"));
        assertFalse(content.contains("Logged"));
    }


    @Test
    public void noArgsInThePostMethodShouldReturnBadRequest() throws Exception {

        /*------------------- Given --------------------- */


        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    public void getRequestInLoginShouldReturnMethodNotAllowed() throws Exception {

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/login")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();


        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());
    }


    @Test
    public void getAllUsersShouldReturnAllUsers() throws Exception {

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/all")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<UserDto> users = super.mapFromJson(content, List.class);

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertTrue(users.size() > 0);

    }

    @Test
    public void postAllUsersShouldReturnMethodNotAllowed() throws Exception {

        /*------------------- When --------------------- */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/all")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        /*-------------------- Then --------------------- */

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mvcResult.getResponse().getStatus());
    }


    public UserUpesertDto createNewUser() {
        UserUpesertDto user = new UserUpesertDto();
        user.setName("Test");
        user.setUsername("TestUsername");
        user.setPassword("12345678");
        user.setEmail("Test@Test.com");
        return user;
    }


    public UserUpesertDto getUserThatAlreadyExist() {
        UserUpesertDto user = new UserUpesertDto();
        user.setName("Test");
        user.setUsername("usernameTest");
        user.setPassword("12345678");
        user.setEmail("email@test.com");
        return user;
    }

}
