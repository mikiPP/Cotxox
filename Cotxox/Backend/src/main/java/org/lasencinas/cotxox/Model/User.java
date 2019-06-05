package org.lasencinas.cotxox.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Entity para users, usuarios en español
 */

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @NotNull
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "username")
    @NotNull
    private String username;


    @Column(name = "email")
    private String email;


    @Column(name = "password")
    @Length(min = 8)
    @NotNull
    private String password;


    @Column(name = "active")
    private boolean active = false;

    @OneToMany(mappedBy = "user")
    private List<Fare> fares;

    public User() {
    }

}
