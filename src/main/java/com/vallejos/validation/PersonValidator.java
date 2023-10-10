package com.vallejos.validation;

import com.vallejos.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PersonValidator {
    @Autowired
    PersonRepository personRepository;

    public boolean isValidEmail(String email) {
        // La expresión regular para validar una dirección de correo electrónico
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}$";

        // Verifica si la dirección de correo electrónico coincide con la expresión regular
        return Pattern.matches(regex, email);
    }

    public static boolean isValidPassword(String password) {
        // La expresión regular para validar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z0-9]{8,12}$";
        // Verifica si la contraseña coincide con la expresión regular
        return Pattern.matches(regex, password);
    }

    public boolean existsByEmail(String email) {
        return personRepository.findByEmail(email) != null;
    }

    public boolean existByToken(String token) {
        boolean tokenExists = personRepository.existsByToken(token);
        return tokenExists;
    }

}
