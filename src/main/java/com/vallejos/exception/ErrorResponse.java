package com.vallejos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Timestamp timestamp;
    private Integer codigo;
    private String detail;
}
