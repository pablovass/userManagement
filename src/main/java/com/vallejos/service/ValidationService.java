package com.vallejos.service;

import com.vallejos.exception.ValidationExceptionMessage;
import com.vallejos.domain.dto.PersonDto;
import com.vallejos.validation.PersonValidator;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class ValidationService {

    private PersonValidator personValidator;


    public ValidationService(PersonValidator personValidator) {
        this.personValidator = personValidator;
    }

    public ValidationException exitsData(String token) {
        if (!personValidator.existByToken(token)) {
            throw new ValidationException(String.valueOf(ValidationExceptionMessage.TOKEN_INVALID));
        }
        return null;
    }

    public ValidationException validatePerson(PersonDto person) throws ValidationException {
        if (!personValidator.isValidEmail(person.getEmail())) {
            throw new ValidationException(String.valueOf(ValidationExceptionMessage.EMAIL_INVALID));
        }

        if (!personValidator.isValidPassword(person.getPassword())) {
            throw new ValidationException(String.valueOf(ValidationExceptionMessage.PASSWORD_INVALID));
        }

        if (personValidator.existsByEmail(person.getEmail())) {
            throw new ValidationException(String.valueOf(ValidationExceptionMessage.EMAIL_ALREADY_EXISTS));
        }
        return null;
    }
}

