package org.lasencinas.cotxox.Service.User;

import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    public UserDto toApiModel(User user) {

        UserDto apiModel = new UserDto();

        apiModel.setId(user.getId());
        apiModel.setUsername(user.getUsername());
        apiModel.setEmail(user.getEmail());
        apiModel.setPassword(user.getPassword());
        apiModel.setActive(user.isActive());

        return apiModel;
    }


    public User toDomainModel(UserUpesertDto userUpesertDto) {

        User domainModel = new User();

        domainModel.setName(userUpesertDto.getName());
        domainModel.setUsername(userUpesertDto.getUsername());
        domainModel.setEmail(userUpesertDto.getEmail());
        domainModel.setPassword(userUpesertDto.getPassword());

        return domainModel;
    }
}
