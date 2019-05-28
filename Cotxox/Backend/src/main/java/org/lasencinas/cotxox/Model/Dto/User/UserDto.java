package org.lasencinas.cotxox.Model.Dto.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Data Transfer Object (dto) es el objeto que enviaremos al frontend
 */

@Getter
@Setter
@Component
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean active = false;

}
