package org.lasencinas.cotxox.Model.Dto.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 *  Data Transfer Object (dto) en este caso al ser un upsert, quiere decir que es un objeto que enviamos desde el front
 *  hacia el backend para poder guardarlo en base de datos (insert) o actualizarlo (update)
 */

@Getter
@Setter
@Component
public class UserUpesertDto {

    private String name;
    private String username;
    private String email;
    private String password;
}
