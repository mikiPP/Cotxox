package org.lasencinas.cotxox.ServiceTest.User.converter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Model.User;
import org.lasencinas.cotxox.Service.User.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserConverterTest {


    @Autowired
    private UserConverter userConverter;


    @Test
    public void modelShouldReturnApiModel() {

        /*------------------- Given --------------------- */

        User user = new User();

        user.setId((long) 3);
        user.setUsername("ConverterTest");
        user.setPassword("12345678");
        user.setEmail("Test@Converter.com");

        /*------------------- When --------------------- */

        UserDto userDto = userConverter.toApiModel(user);

        /*------------------- Then --------------------- */

        assertTrue(userDto instanceof UserDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getEmail(), userDto.getEmail());

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        User userTest = new User();

        user.setId((long) 4);
        user.setUsername("ConverterTest2");
        user.setPassword("12345678");
        user.setEmail("Test2@Converter.com");

        /*------------------- When --------------------- */

        UserDto userDtoTest = userConverter.toApiModel(userTest);

        /*------------------- Then --------------------- */

        assertTrue(userDtoTest instanceof UserDto);
        assertEquals(userTest.getId(), userDtoTest.getId());
        assertEquals(userTest.getUsername(), userDtoTest.getUsername());
        assertEquals(userTest.getPassword(), userDtoTest.getPassword());
        assertEquals(userTest.getEmail(), userDtoTest.getEmail());
    }


    @Test
    public void UpsertDtoShouldReturnModel() {

        /*------------------- Given --------------------- */

        UserUpesertDto userUpsertDto = new UserUpesertDto();

        userUpsertDto.setName("Test");
        userUpsertDto.setUsername("ConverterTest");
        userUpsertDto.setPassword("12345678");
        userUpsertDto.setEmail("Test@Converter.com");

        /*------------------- When --------------------- */

        User user = userConverter.toDomainModel(userUpsertDto);

        /*------------------- Then --------------------- */

        assertTrue(user instanceof User);
        assertEquals(user.getName(), userUpsertDto.getName());
        assertEquals(user.getUsername(), userUpsertDto.getUsername());
        assertEquals(user.getPassword(), userUpsertDto.getPassword());
        assertEquals(user.getEmail(), userUpsertDto.getEmail());

        /*------------------- 2ndTest --------------------- */

        /*------------------- Given --------------------- */

        UserUpesertDto userUpsertDtoTest = new UserUpesertDto();

        userUpsertDtoTest.setName("Test2");
        userUpsertDtoTest.setUsername("ConverterTest2");
        userUpsertDtoTest.setPassword("12345678");
        userUpsertDtoTest.setEmail("Test2@Converter.com");

        /*------------------- When --------------------- */

        User userTest = userConverter.toDomainModel(userUpsertDtoTest);

        /*------------------- Then --------------------- */

        assertTrue(userTest instanceof User);
        assertEquals(userTest.getName(), userUpsertDtoTest.getName());
        assertEquals(userTest.getUsername(), userUpsertDtoTest.getUsername());
        assertEquals(userTest.getPassword(), userUpsertDtoTest.getPassword());
        assertEquals(userTest.getEmail(), userUpsertDtoTest.getEmail());
    }
}
