package org.lasencinas.cotxox.Controller;

import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para usuarios, tiene 3 endpoints.El primero sirve para registrar un nuevo usuario.
 * El segundo para que se pueda logear y el último devuelve todos los usuarios.
 *
 */


@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserUpesertDto user) {
        System.out.println("llega");
        return (userService.addUser(user)) ? "Added" : "The user already exists";

    }

    @PostMapping("/login")
    public String login(@RequestBody UserUpesertDto user) {

        return (userService.login(user.getUsername(), user.getPassword())) ? "Logged" : "User or password is wrong";
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

}
