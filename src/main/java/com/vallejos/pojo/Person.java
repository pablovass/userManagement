package com.vallejos.pojo;

import com.vallejos.pojo.Phone;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import java.util.List;
import java.util.UUID;
@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON")
@ToString(exclude = "phones") //user es una palabra reserva da h2
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 16)
    private UUID id;


    @Column(name = "name")
    private String name;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d{2})(?=.*[a-z]).{8,12}$")
    private String password;
    @Column(name = "created")
    private String created; // Valor predeterminado: fecha y hora actual
    @Column(name = "last_login")
    private String lastLogin;// Valor predeterminado: fecha y hora actual
    @Column(name = "is_active")
    private Boolean isActive = true; // Valor predeterminado: true

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Phone> phones;
    @Column(name = "token", length = 1000) // Ajusta la longitud según tus necesidades
    private String token;

}
