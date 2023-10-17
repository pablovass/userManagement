package com.vallejos.pojo;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@Getter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PHONE")
@ToString(exclude = "person")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Puedes usar GenerationType.IDENTITY si estás utilizando una base de datos que admite autoincremento
    @Column(name = "id" ,length = 16)
    private UUID id;

    @Column(name = "number")
    private Long number;
    @Column(name = "city_code")
    private Integer citycode;
    @Column(name = "country_code")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "person_id") // Debe ser "person_id" para coincidir con la columna de clave foránea en la tabla PHONE
    private Person person;


}
