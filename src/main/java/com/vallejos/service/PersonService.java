package com.vallejos.service;

import com.vallejos.exception.ValidationExceptionMessage;
import com.vallejos.mapper.PersonMapper;
import com.vallejos.domain.Person;
import com.vallejos.domain.dto.*;
import com.vallejos.repository.PersonRepository;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import org.springframework.stereotype.Service;
import javax.validation.ValidationException;
import java.time.LocalDateTime;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private DateFormatter dateFormatter;
    private PersonMapper personMapper;
    private ValidationService validationService;
    private JwtTokenProvider jwtTokenProvider;

    public PersonService(JwtTokenProvider jwtTokenProvider, PersonMapper personMapper, ValidationService validationService, DateFormatter dateFormatter, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.dateFormatter = dateFormatter;
        this.validationService = validationService;
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
        if (token.isEmpty()) {
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