package com.vallejos.service;
import com.vallejos.exception.ValidationExceptionMessage;
import com.vallejos.pojo.Person;
import com.vallejos.validation.PersonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Mock
    private PersonValidator personValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidatePersonInvalidEmail() {
        // Arrange
        Person invalidEmailPerson = new Person();
        invalidEmailPerson.setEmail("invalid-email");
        invalidEmailPerson.setPassword("ValidPassword");

        when(personValidator.isValidEmail(invalidEmailPerson.getEmail())).thenReturn(false);

        // Act
        ValidationException exception = assertThrows(ValidationException.class,
                () -> validationService.validatePerson(invalidEmailPerson));

        // Assert
        assertEquals(String.valueOf(ValidationExceptionMessage.EMAIL_INVALID), exception.getMessage());
        verify(personValidator).isValidEmail(invalidEmailPerson.getEmail());
    }

}
