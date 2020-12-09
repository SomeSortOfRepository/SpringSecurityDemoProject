package ru.dolinini.springsecuritydemoproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "Password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(value=EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value=EnumType.STRING)
    private Status status;

}
