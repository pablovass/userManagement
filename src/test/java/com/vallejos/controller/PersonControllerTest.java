package com.vallejos.controller;

import com.vallejos.controller.PersonController;
import com.vallejos.domain.dto.PersonDto;
import com.vallejos.domain.dto.PersonPostDto;
import com.vallejos.domain.dto.PersonPostDtoResponse;
import com.vallejos.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSignUp() {
        // Given
        PersonDto personDto = new PersonDto();
        PersonPostDto personPostDto = new PersonPostDto(); // Crear un objeto PersonPostDto necesario para crear PersonPostDtoResponse
        PersonPostDtoResponse createdPerson = new PersonPostDtoResponse(personPostDto); // Pasar PersonPostDto como argumento al constructor
        when(personService.createUser(personDto)).thenReturn(createdPerson);

        // When
        ResponseEntity<PersonPostDtoResponse> responseEntity = personController.signUp(personDto);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdPerson, responseEntity.getBody());
        verify(personService, times(1)).createUser(personDto);
    }


    @Test
    public void testLoginByToken() {
        // Given
        String token = "testToken";
        PersonDto personDto = new PersonDto();
        when(personService.getPersonByToken(token)).thenReturn(personDto);

        // When
        ResponseEntity<PersonDto> responseEntity = personController.loginByToken(token);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(personDto, responseEntity.getBody());
        verify(personService, times(1)).getPersonByToken(token);
    }
}
