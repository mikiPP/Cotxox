package org.lasencinas.cotxox.Service.User;

import org.lasencinas.cotxox.Model.Dto.User.UserDto;
import org.lasencinas.cotxox.Model.Dto.User.UserUpesertDto;
import org.lasencinas.cotxox.Model.User;
import org.lasencinas.cotxox.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserConverter userConverter;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //-------------------------------------- Constructor --------------------------------------------------------//


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
    }


    //-------------------------------------- Interfaz pública --------------------------------------------------------//


    public Boolean addUser(UserUpesertDto userUpesertDto) {

        User user = userConverter.toDomainModel(userUpesertDto);

        if (this.validUser(user)) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;

        } else {
            return false;
        }
    }


    public User findById(long id) {
        return this.userRepository.findById(id).get();
    }


    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(userConverter.toApiModel(user));
        });
        return users;

    }


    public boolean login(String username, String password) {



        if (userRepository.findByUsername(username).isPresent()) {

            User user = userRepository.findByUsername(username).get();

            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {

                user.setActive(true);
                return true;

            } else {

                return false;
            }

        } else {
            return false;
        }
    }


    //-------------------------------------- Interfaz privada --------------------------------------------------------//


    private Boolean findByEmail(String userEmail) {

        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(userEmail))
                .collect(Collectors.toList());

        return !users.isEmpty();
    }


    private boolean validUser(User user) {


            return !userRepository.findByUsername(user.getUsername()).isPresent()  && !this.findByEmail(user.getEmail());



    }


}

