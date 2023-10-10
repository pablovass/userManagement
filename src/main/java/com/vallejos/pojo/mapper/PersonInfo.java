package com.vallejos.pojo.mapper;
import lombok.Data;
import java.util.UUID;

@Data
public class PersonInfo {
    private UUID id;
    private String created;
    private String lastLogin;
    private String token;
    private boolean isActive;

    public static void setId() {
    }
}
