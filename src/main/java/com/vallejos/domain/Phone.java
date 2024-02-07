package com.vallejos.domain;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" ,length = 16)
    private UUID id;

    @Column(name = "number")
    private Long number;
    @Column(name = "city_code")
    private Integer citycode;
    @Column(name = "country_code")
    private String countrycode;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;


}