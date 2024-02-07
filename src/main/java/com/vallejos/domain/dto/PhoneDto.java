package com.vallejos.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private Long number;
    private Integer citycode;
    private String countrycode;
    @JsonIgnore
    private PersonDto personDto;
}