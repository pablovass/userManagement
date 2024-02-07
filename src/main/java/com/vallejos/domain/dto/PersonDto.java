package com.vallejos.domain.dto;
import lombok.Data;;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Data
public class PersonDto {

    private UUID id;
    private String created;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private String modified;
    private String name;
    @Email(message = "El correo electrónico no es válido por aca")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z0-9]{8,12}$")
    private String password;
    private List<PhoneDto> phones;



}
