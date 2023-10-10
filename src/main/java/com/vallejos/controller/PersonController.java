package com.vallejos.controller;

import com.vallejos.pojo.Person;
import com.vallejos.pojo.mapper.PersonCreatedResponse;
import com.vallejos.pojo.mapper.PersonLoginInfo;
import com.vallejos.service.PersonService;
import com.vallejos.service.ValidationService;
import com.vallejos.validation.PersonValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "Usuario")
public class PersonController {

    private PersonService personService;
    private PersonValidator personValidator;
    private ValidationService validationService;

    @Autowired
    public PersonController(ValidationService validationService, PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.validationService = validationService;
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Crear un nuevo usuario", notes = "Crea un nuevo usuario en el sistema.")
    public ResponseEntity<?> signUp(@RequestBody Person person) {
        ValidationException validationException = validationService.validatePerson(person);
        if (validationException != null) {
            Map<String, Object> errorResponse = personService.createErrorResponse(validationException.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        PersonCreatedResponse createdPerson = personService.createUser(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);

    }

    @GetMapping("/login")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> consultarUsuario(String token) {
        ValidationException validationException = validationService.exitsData(token);
        if (validationException != null) {
            Map<String, Object> errorResponse = personService.createErrorResponse(validationException.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        PersonLoginInfo createdPerson = personService.getPersonByToken(token);

        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);

    }

}
