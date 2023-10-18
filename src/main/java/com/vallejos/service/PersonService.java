package com.vallejos.service;

import com.vallejos.exception.ValidationExceptionMessage;
import com.vallejos.mapper.PersonMapper;
import com.vallejos.pojo.Person;
import com.vallejos.pojo.dto.*;
import com.vallejos.repository.PersonRepository;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import com.vallejos.validation.PersonValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private DateFormatter dateFormatter;
    private PersonMapper personMapper;
    private PersonValidator personValidator;
    private ValidationService validationService;


    public PersonService(PersonMapper personMapper, PersonValidator personValidator, ValidationService validationService, DateFormatter dateFormatter, PasswordEncoder passwordEncoder, PersonRepository personRepository, JwtTokenProvider jwtTokenProvider) {
        this.personRepository = personRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.dateFormatter = dateFormatter;
        this.validationService = validationService;
        this.personValidator = personValidator;
        this.personMapper = personMapper;
    }

    public PersonPostDtoResponse createUser(PersonDto personDto) {
        validationService.validatePerson(personDto);
        Person personPersistence = personMapper.mapPersonDtoToPersonPersistence(personDto);
        personRepository.save(personPersistence);
        PersonPostDto personPostDto = personMapper.mapPersonToPersonPostDto(personPersistence);
        return new PersonPostDtoResponse(personPostDto);
    }

   public PersonDto getPersonByToken(String token) {
       if(token.isEmpty()){
           throw new ValidationException(String.valueOf(ValidationExceptionMessage.EMPTY_OBJECT));
       }
       validationService.exitsData(token);
       Person person = personRepository.findByToken(token);
       String newToken = jwtTokenProvider.createToken(person.getId().toString());
       person.setToken(newToken);
       person.getCreated();
       person.setLastLogin(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
       Person savedPerson = personRepository.save(person);
       PersonDto personLoginInfo = personMapper.mapPersonToPersonDto(savedPerson);

       return personLoginInfo;
   }


}


