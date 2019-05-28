package org.lasencinas.cotxox.Service.User;

import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    Boolean addUser(UserUpesertDto userUpesertDto);

    User findById(long id);

    List<UserDto> getAll();

    boolean login(String username, String password);

}
