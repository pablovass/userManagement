package com.vallejos.service;

import com.vallejos.domain.Person;
import com.vallejos.domain.dto.PersonDto;
import com.vallejos.domain.dto.PersonPostDto;
import com.vallejos.domain.dto.PersonPostDtoResponse;
import com.vallejos.mapper.PersonMapper;
import com.vallejos.repository.PersonRepository;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    DateFormatter dateFormatter;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private ValidationService validationService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateUser() {
        // Given
        PersonDto personDto = new PersonDto();
        Person personPersistence = new Person();
        PersonPostDto personPostDto = new PersonPostDto();
        PersonPostDtoResponse expectedResponse = new PersonPostDtoResponse(personPostDto);

        when(personMapper.mapPersonDtoToPersonPersistence(personDto)).thenReturn(personPersistence);
        when(personMapper.mapPersonToPersonPostDto(personPersistence)).thenReturn(personPostDto);
        when(personRepository.save(personPersistence)).thenReturn(personPersistence);

        // When
        PersonPostDtoResponse response = personService.createUser(personDto);

        // Then
        assertEquals(expectedResponse, response);
        verify(validationService, times(1)).validatePerson(personDto);
        verify(personMapper, times(1)).mapPersonDtoToPersonPersistence(personDto);
        verify(personRepository, times(1)).save(personPersistence);
        verify(personMapper, times(1)).mapPersonToPersonPostDto(personPersistence);
    }

    @Test
    public void testGetPersonByToken() {
        // Given
        String defaultToken = "defaultToken";
        PersonDto expectedPersonDto = new PersonDto();
        Person expectedPerson = new Person();
        expectedPerson.setId(UUID.randomUUID()); // Establecer un UUID válido para evitar el NullPointerException

        // Stubbing the behavior of mock objects
        when(personRepository.findByToken(defaultToken)).thenReturn(expectedPerson);
        // Verificar si expectedPerson es null antes de llamar getId()
        if (expectedPerson != null && expectedPerson.getId() != null) {
            when(jwtTokenProvider.createToken(expectedPerson.getId().toString())).thenReturn(defaultToken);
        }
        when(personRepository.save(expectedPerson)).thenReturn(expectedPerson);
        when(personMapper.mapPersonToPersonDto(expectedPerson)).thenReturn(expectedPersonDto);
        // Stubbing the behavior of dateFormatter if expectedPerson is not null
        if (expectedPerson != null) {
            when(dateFormatter.formatLocalDateTime(any())).thenReturn("formattedDate");
        }

        // When
        PersonDto result = personService.getPersonByToken(defaultToken);

        // Then
        assertEquals(expectedPersonDto, result);
    }



}

