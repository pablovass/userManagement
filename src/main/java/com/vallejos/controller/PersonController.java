package com.vallejos.controller;
import com.vallejos.domain.dto.PersonDto;
import com.vallejos.domain.dto.PersonPostDtoResponse;
import com.vallejos.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = "Usuario")
public class PersonController {

    private PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/sign-up")
    @ApiOperation(value = "Crear un nuevo usuario", notes = "Crea un nuevo usuario en el sistema.")
    public ResponseEntity<PersonPostDtoResponse> signUp(@RequestBody PersonDto person) {
        PersonPostDtoResponse createdPerson = personService.createUser(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);

    }

   @GetMapping("/login")
   @ApiOperation(value = "Consulta usuarios registrados en la base de datos por el token",
           notes = "Consulta usuarios registrados en la base de datos por el token. Realizada la consulta, se actualiza el token, deshabilitando el token consultado")
   public ResponseEntity<PersonDto> loginByToken(String token) {
       PersonDto personLoginInfo = personService.getPersonByToken(token);
       return new ResponseEntity<>(personLoginInfo, HttpStatus.CREATED);

   }

}
