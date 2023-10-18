package com.vallejos.controller;

import com.vallejos.pojo.dto.PersonDto;
import com.vallejos.pojo.dto.PersonPostDtoResponse;
import com.vallejos.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Test
    void signUpShouldCreateANewUserAndReturnA201CreatedResponse() {

        PersonDto personDto = new PersonDto();
        PersonPostDtoResponse createdPersonResponse = new PersonPostDtoResponse();


        when(personService.createUser(personDto)).thenReturn(createdPersonResponse);

        PersonController personController = new PersonController(personService);
        ResponseEntity<PersonPostDtoResponse> response = personController.signUp(personDto);


        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(createdPersonResponse, response.getBody());


        verify(personService).createUser(personDto);
    }

    @Test
    void loginByTokenShouldRetrieveUserInformationByTokenAndReturnA201CreatedResponse() {

        String token = "valid-token";
        PersonDto personDto = new PersonDto();


        when(personService.getPersonByToken(token)).thenReturn(personDto);

        PersonController personController = new PersonController(personService);
        ResponseEntity<PersonDto> response = personController.loginByToken(token);


        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(personDto, response.getBody());


        verify(personService).getPersonByToken(token);
    }
}
