package com.vallejos.validation;

import static org.junit.jupiter.api.Assertions.*;

import com.vallejos.pojo.Person;
import com.vallejos.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class PersonValidatorTest {

    @Mock
    private PersonRepository personRepository;

    private PersonValidator personValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personValidator = new PersonValidator();
        personValidator.personRepository = personRepository;
    }

    @Test
    public void testIsValidEmailValid() {
        // Arrange
        String validEmail = "test@example.com";

        // Act
        boolean result = personValidator.isValidEmail(validEmail);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidEmailInvalid() {
        // Arrange
        String invalidEmail = "invalid-email";

        // Act
        boolean result = personValidator.isValidEmail(invalidEmail);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValidPasswordValid() {
        // Arrange
        String validPassword = "Abc12345";

        // Act
        boolean result = personValidator.isValidPassword(validPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValidPasswordInvalid() {
        // Arrange
        String invalidPassword = "short";

        // Act
        boolean result = personValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testExistsByEmailTrue() {
        // Arrange
        String existingEmail = "existing@example.com";
        when(personRepository.findByEmail(existingEmail)).thenReturn(new Person());

        // Act
        boolean result = personValidator.existsByEmail(existingEmail);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testExistsByEmailFalse() {
        // Arrange
        String nonExistingEmail = "nonexisting@example.com";
        when(personRepository.findByEmail(nonExistingEmail)).thenReturn(null);

        // Act
        boolean result = personValidator.existsByEmail(nonExistingEmail);

        // Assert
        assertFalse(result);
    }

}
