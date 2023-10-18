package com.vallejos.pojo;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSON") //user es una palabra reserva de h2
@ToString(exclude = "phones")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 16)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
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
