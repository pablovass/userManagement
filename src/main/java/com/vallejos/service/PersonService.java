package com.vallejos.service;

import com.vallejos.pojo.Person;
import com.vallejos.pojo.Phone;
import com.vallejos.pojo.mapper.*;
import com.vallejos.repository.PersonRepository;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private DateFormatter dateFormatter;
    private LoginMapper loginMapper;
    private LoginMapper loginCustomMapper;
    private PersonMapper customMapper;



    public PersonService(PersonMapper customMapper, LoginMapper loginCustomMapper, LoginMapper loginMapper, DateFormatter dateFormatter, PasswordEncoder passwordEncoder, PersonRepository personRepository, JwtTokenProvider jwtTokenProvider) {
        this.personRepository = personRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.dateFormatter = dateFormatter;
        this.loginMapper = loginMapper;
        this.loginCustomMapper = loginCustomMapper;
        this.customMapper = customMapper;

    }

    public PersonCreatedResponse createUser(Person person) {
        String encryptedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);
        person.setCreated(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
        person.setLastLogin(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
        String token = jwtTokenProvider.createToken(person.getToken());
        person.setToken(token);

        if (person.getPhones() != null) {
            for (Phone phone : person.getPhones()) {
                phone.setPerson(person);
            }
        }
        Person savedPerson = personRepository.save(person);
        PersonInfo personInfo = customMapper.mapToUserInfo(savedPerson);
        PersonCreatedResponse personCreatedResponse = new PersonCreatedResponse(personInfo);

        return personCreatedResponse;

    }

    public PersonLoginInfo getPersonByToken(String token) {
        Person person = personRepository.findByToken(token);
        String newToken = jwtTokenProvider.createToken(person.getId().toString());
        person.setToken(newToken);
        person.getCreated();
        person.setLastLogin(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
        Person savedPerson = personRepository.save(person);
        PersonLoginInfo personLoginInfo = loginCustomMapper.mapToUserInfo(savedPerson);

        return personLoginInfo;
    }


    public Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", new Date());
        errorDetails.put("codigo", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("detail", message);

        errorResponse.put("error", Collections.singletonList(errorDetails));

        return errorResponse;
    }
}


