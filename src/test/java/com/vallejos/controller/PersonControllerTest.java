package com.vallejos.controller;
import com.vallejos.pojo.Person;
import com.vallejos.pojo.mapper.PersonCreatedResponse;
import com.vallejos.service.PersonService;
import com.vallejos.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;

import static org.mockito.Mockito.*;

class PersonControllerTest {
    @InjectMocks
    private PersonController yourController;

    @Mock
    private ValidationService validationService;

    @Mock
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testSignUpValidPerson() {
        // Arrange
        Person validPerson = new Person(); // Initialize a valid Person object
        when(validationService.validatePerson(validPerson)).thenReturn(null); // Mock validationService to return no validation errors
        when(personService.createUser(validPerson)).thenReturn(new PersonCreatedResponse());

        // Act
        ResponseEntity<?> response = yourController.signUp(validPerson);

        // Assert
        verify(validationService).validatePerson(validPerson); // Verify that validationService.validatePerson was called with the validPerson
        verify(personService).createUser(validPerson); // Verify that personService.createUser was called with the validPerson
        // You can add more assertions to check the response status and content
        assert response.getStatusCode() == HttpStatus.CREATED;
        // Add more assertions as needed
    }

}
