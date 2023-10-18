package com.vallejos.pojo.dto;
import lombok.Data;
import java.util.UUID;

@Data
public class PersonPostDto {
    private UUID id;
    private String created;
    private String lastLogin;
    private String token;
    private boolean isActive;

}
