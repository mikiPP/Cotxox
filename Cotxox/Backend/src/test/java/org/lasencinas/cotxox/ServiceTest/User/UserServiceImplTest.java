package org.lasencinas.cotxox.ServiceTest.User;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(statements = {
        "delete from user",
        "insert into user values (1,0,'email@test.com','test','12345678','usernameTest')",
        "insert into user values (2,1,'test@test.com','test2','87654321','testUsername')"
})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void addValidUserShouldReturnTrue() {

        /*------------------- Given --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setEmail("pruebaTest@gmail.com");
        user.setName("pruebaTest");
        user.setPassword("12345678");
        user.setUsername("patata");

        /*------------------- When --------------------- */

        boolean test = userService.addUser(user);

        /*------------------- Then  --------------------- */

        assertTrue(test);

        /*------------------- 2nd Test --------------------- */

        /*------------------- Given --------------------- */

        UserUpesertDto user2 = new UserUpesertDto();
        user2.setEmail("realUserTest@algo.com");
        user2.setName("Real");
        user2.setPassword("otrapassword");
        user2.setUsername("realUser");

        /*------------------- When --------------------- */

        boolean test2 = userService.addUser(user2);

        /*------------------- Then --------------------- */

        assertTrue(test2);

    }

    @Test
    public void addInValidUserShouldReturnFalse() {

        /*------------------- Given --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setEmail("email@test.com");
        user.setName("Test");
        user.setPassword("12345678");
        user.setUsername("usernameTest");

        /*------------------- When --------------------- */

        boolean test = userService.addUser(user);


        /*------------------- Then  --------------------- */

        assertFalse(test);

        /*------------------- 2nd Test --------------------- */

        /*------------------- Given --------------------- */

        UserUpesertDto userBadEmail = new UserUpesertDto();
        userBadEmail.setEmail("email@test.com");
        userBadEmail.setName("Test");
        userBadEmail.setPassword("12345678");
        userBadEmail.setUsername("otherUsernameTest");


        /*------------------- When --------------------- */

        boolean test2 = userService.addUser(userBadEmail);

        /*------------------- Then  --------------------- */

        assertFalse(test2);

        /*------------------- 3rd Test --------------------- */

        /*------------------- Given  --------------------- */

        UserUpesertDto userBadUsername = new UserUpesertDto();
        userBadUsername.setEmail("another@test.com");
        userBadUsername.setName("Test");
        userBadUsername.setPassword("12345678");
        userBadUsername.setUsername("usernameTest");

        boolean test3 = userService.addUser(userBadUsername);

        /*------------------- Then  --------------------- */

        assertFalse(test3);
    }


    @Test
    public void validUserInValidUserShouldReturnTrue() {

        /*------------------- Given  --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setEmail("ValidUserTest@gmail.com");
        user.setName("ValidTest");
        user.setPassword("password");
        user.setUsername("ValidTest");

        /*------------------- When  --------------------- */

        boolean test = userService.addUser(user);

        /*------------------- Then  --------------------- */

        assertTrue(test);

        /*------------------- 2nd Test  --------------------- */

        /*------------------- Given  --------------------- */

        UserUpesertDto validUser = new UserUpesertDto();
        validUser.setEmail("ValidUserTest@Test.com");
        validUser.setName("TestValid");
        validUser.setPassword("password");
        validUser.setUsername("ValidTestAgain");

        /*------------------- When --------------------- */

        boolean test2 = userService.addUser(validUser);

        /*------------------- Then  --------------------- */

        assertTrue(test2);
    }


    @Test
    public void InvalidUserInValidUserShouldReturnFalse() {

        /*------------------- Given  --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setPassword("12345678");
        user.setUsername("testUsername");

        /*------------------- When --------------------- */

        boolean test = userService.addUser(user);

        /*------------------- Then  --------------------- */

        assertFalse(test);

        /*------------------- 2nd Test  --------------------- */

        /*------------------- Given  --------------------- */

        UserUpesertDto userBadEmail = new UserUpesertDto();
        userBadEmail.setEmail("test@test.com");
        userBadEmail.setName("Test");
        userBadEmail.setPassword("12345678");
        userBadEmail.setUsername("OtherTestUsername");

        /*------------------- When --------------------- */

        boolean test2 = userService.addUser(userBadEmail);

        /*------------------- Then  --------------------- */

        assertFalse(test2);

        /*------------------- 3rd Test  --------------------- */

        /*------------------- Given  --------------------- */

        UserUpesertDto userBadUsername = new UserUpesertDto();
        userBadUsername.setEmail("anotherTest@test.com");
        userBadUsername.setName("Test");
        userBadUsername.setPassword("12345678");
        userBadUsername.setUsername("testUsername");

        /*------------------- When --------------------- */

        boolean test3 = userService.addUser(userBadUsername);

        /*------------------- Then  --------------------- */

        assertFalse(test3);

    }


    @Test
    public void validLoginShouldReturnTrue() {

        /*------------------- Given  --------------------- */

        UserUpesertDto user = new UserUpesertDto();
        user.setEmail("TestLogin@test.com");
        user.setName("Test");
        user.setPassword("12345678");
        user.setUsername("LoginTest");

        userService.addUser(user);

        /*------------------- When  --------------------- */

        boolean test = userService.login(user.getUsername(), user.getPassword());

        /*------------------- Then  --------------------- */

        assertTrue(test);

        /*------------------- 2nd Case  --------------------- */

        /*------------------- Given  --------------------- */

        UserUpesertDto userTest = new UserUpesertDto();
        userTest.setEmail("LoginTest@test.com");
        userTest.setName("Test");
        userTest.setPassword("87654321");
        userTest.setUsername("TestLogin");

        userService.addUser(userTest);

        /*------------------- When  --------------------- */

        boolean test2 = userService.login(userTest.getUsername(), userTest.getPassword());

        /*------------------- Then  --------------------- */

        assertTrue(test2);
    }


    @Test
    public void invalidLoginShouldReturnFalse() {

        /*------------------- Given  --------------------- */

        String password = "1234";
        String username = "notValidUsername";

        //in this case both will be wrong

        /*------------------- When  --------------------- */

        boolean test = userService.login(username, password);

        /*------------------- Then  --------------------- */

        assertFalse(test);

        /*------------------- 2nd Case  --------------------- */

        /*------------------- Given  --------------------- */

        String password2 = "1234567";
        String username2 = "usernameTest";

        //The password is wrong

        /*------------------- When  --------------------- */

        boolean test2 = userService.login(username2, password2);

        /*------------------- Then  --------------------- */

        assertFalse(test2);

        /*------------------- 3nd Case  --------------------- */

        /*------------------- Given  --------------------- */

        String password3 = "12345678";
        String username3 = "Test";

        //The username is wrong

        /*------------------- When  --------------------- */

        boolean test3 = userService.login(username3, password3);

        /*------------------- Then  --------------------- */

        assertFalse(test3);

    }


}
