package com.vallejos.validation;

import com.vallejos.domain.dto.PersonDto;
import com.vallejos.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class PersonValidator {

    private PersonRepository personRepository;

    @Autowired
    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonValidator() {

    }

    public boolean isValidEmail(String email) {
        // Valida el formato del correo electrónico utilizando la anotación @Email
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PersonDto>> violations = validator.validateValue(PersonDto.class, "email", email);
        return violations.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // Obtiene las anotaciones de la clase PersonDTO
        Set<ConstraintViolation<PersonDto>> violations = validator.validateValue(PersonDto.class, "password", password);
        // Si no hay violaciones, la contraseña es válida según la expresión regular definida en @Pattern
        return violations.isEmpty();
    }

    public boolean existsByEmail(String email) {
        return personRepository.findByEmail(email) != null;
    }

    public boolean existByToken(String token) {
        boolean tokenExists = personRepository.existsByToken(token);
        return tokenExists;
    }

}