package com.vallejos.pojo.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneLoginInfo {
    private Long number;
    private Integer citycode;
    private String contrycode;
}
