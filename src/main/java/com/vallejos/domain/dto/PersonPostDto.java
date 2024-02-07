package com.vallejos.domain.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.UUID;

@Data
public class PersonPostDto {
    private UUID id;
    private String created;
    @JsonProperty("last_login")
    private String lastLogin;
    private String token;
    private Boolean  isActive;
    private String modified;

}
