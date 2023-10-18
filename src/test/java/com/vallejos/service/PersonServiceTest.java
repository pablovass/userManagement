package com.vallejos.service;

import com.vallejos.mapper.PersonMapper;
import com.vallejos.pojo.Person;
import com.vallejos.pojo.dto.*;
import com.vallejos.repository.PersonRepository;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import com.vallejos.validation.PersonValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private PersonService personService;

    private PersonRepository personRepository;

    private JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder passwordEncoder;

    private DateFormatter dateFormatter;

    private PersonMapper personMapper;

    private PersonValidator personValidator;

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        passwordEncoder = mock(PasswordEncoder.class);
        dateFormatter = mock(DateFormatter.class);
        personMapper = mock(PersonMapper.class);
        personValidator = mock(PersonValidator.class);
        validationService = mock(ValidationService.class);
        personService = new PersonService(
                personMapper,
                personValidator,
                validationService,
                dateFormatter,
                passwordEncoder,
                personRepository,
                jwtTokenProvider
        );
    }

    @Test
    void createUser_ShouldCreateNewPersonAndReturnResponse() {

        PersonDto personDto = new PersonDto();
        Person personPersistence = new Person();
        PersonPostDto personPostDto = new PersonPostDto();
        when(personMapper.mapPersonDtoToPersonPersistence(personDto)).thenReturn(personPersistence);
        when(personRepository.save(personPersistence)).thenReturn(personPersistence);
        when(personMapper.mapPersonToPersonPostDto(personPersistence)).thenReturn(personPostDto);


        PersonPostDtoResponse response = personService.createUser(personDto);


        verify(validationService, times(1)).validatePerson(personDto);
        verify(personRepository, times(1)).save(personPersistence);
        verify(personMapper, times(1)).mapPersonDtoToPersonPersistence(personDto);
        verify(personMapper, times(1)).mapPersonToPersonPostDto(personPersistence);
        assertEquals(personPostDto, response.getUser_created());
    }

    @Test
    void getPersonByToken_ShouldReturnPersonDtoWithUpdatedToken() {

        String token = "iqGBami0iL-ovT3PCAti0U4UCm50rYqVN87HM-t1pmDsIHUBqrZJu3LtjKbgroV6d6YWzfGpeliw";
        Person person = new Person();
        person.setId(UUID.randomUUID()); // Set a valid ID for the person object
        PersonDto personDto = new PersonDto();

        // Mockear la creación de token por JwtTokenProvider
        when(jwtTokenProvider.createToken(anyString())).thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NDk1NmVkYy1kODAzLTRlN2UtYmQ3Ny1jMWFhOWM1YTkwZDkiLCJpYXQiOjE2OTc1ODY5NDgsImV4cCI6MTY5NzU4Njk1M30.5nBbVLC5cr");
        when(dateFormatter.formatLocalDateTime(any())).thenReturn("Oct 17, 2023 08:55:34 PM");
        when(personRepository.findByToken(token)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.mapPersonToPersonDto(person)).thenReturn(personDto);


        PersonDto response = personService.getPersonByToken(token);


        verify(validationService, times(1)).exitsData(token);
        verify(jwtTokenProvider, times(1)).createToken(person.getId().toString());
        verify(dateFormatter, times(1)).formatLocalDateTime(any());
        verify(personRepository, times(1)).findByToken(token);
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).mapPersonToPersonDto(person);

        assertEquals(personDto, response);
    }


}

