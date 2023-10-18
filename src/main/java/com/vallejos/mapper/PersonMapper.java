package com.vallejos.mapper;

import com.vallejos.pojo.Person;
import com.vallejos.pojo.Phone;
import com.vallejos.pojo.dto.PersonDto;
import com.vallejos.pojo.dto.PersonPostDto;
import com.vallejos.pojo.dto.PhoneDto;
import com.vallejos.util.DateFormatter;
import com.vallejos.util.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    private final ModelMapper modelMapper;
    private DateFormatter dateFormatter;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    public PersonMapper(PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider,ModelMapper modelMapper,DateFormatter dateFormatter) {
        this.modelMapper = modelMapper;
        this.dateFormatter=dateFormatter;
        this.jwtTokenProvider=jwtTokenProvider;
        this.passwordEncoder=passwordEncoder;

    }
public Person mapPersonDtoToPersonPersistence(PersonDto personDto){
    Person person = new Person();
    person.setName(personDto.getName());
    person.setEmail(personDto.getEmail());
    person.setPassword(personDto.getPassword());

    // Crear una lista de objetos Phone y asociarlos con el objeto Person
    List<Phone> phones = new ArrayList<>();
    if (personDto.getPhones() != null) {
        for (PhoneDto phoneDto : personDto.getPhones()) {
            Phone phone = new Phone();
            phone.setNumber(phoneDto.getNumber());
            phone.setCitycode(phoneDto.getCitycode());
            phone.setCountrycode(phoneDto.getCountrycode());
            phone.setPerson(person); // Asociar el teléfono con la persona
            phones.add(phone); // Agregar el teléfono a la lista
        }
    }
    person.setPhones(phones); // Asignar la lista de teléfonos al objeto Person

    // Resto de la lógica (encriptación, fecha, token, etc.)
    String encryptedPassword = passwordEncoder.encode(person.getPassword());
    person.setPassword(encryptedPassword);
    person.setCreated(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
    person.setLastLogin(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
    String token = jwtTokenProvider.createToken(person.getToken());
    person.setToken(token);
        return  person;
}
    public PersonPostDto mapPersonToPersonPostDto(Person person) {
        PersonPostDto personPostDto = new PersonPostDto();
        personPostDto.setId(person.getId());
        personPostDto.setCreated(person.getCreated());
        personPostDto.setLastLogin(person.getLastLogin());
        personPostDto.setToken(person.getToken());
        personPostDto.setActive(person.getIsActive());
        return personPostDto;
    }

    public PersonDto mapPersonToPersonDto(Person person) {

        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setCreated(person.getCreated());
        personDto.setLastLogin(person.getLastLogin());
        personDto.setToken(person.getToken());
        personDto.setIsActive(person.getIsActive());
        personDto.setName(person.getName());
        personDto.setEmail(person.getEmail());
        personDto.setPassword(person.getPassword());

        if (person.getPhones() != null) {
            personDto.setPhones(
                    person.getPhones().stream().map(this::mapToPhoneResponse).collect(Collectors.toList())
            );
        }

        return personDto;
    }
    private PhoneDto mapToPhoneResponse(Phone phone) {
        PhoneDto phoneLoginInfo = new PhoneDto();
        phoneLoginInfo.setNumber(phone.getNumber());
        phoneLoginInfo.setCitycode(phone.getCitycode());
        phoneLoginInfo.setCountrycode(phone.getCountrycode());
        return phoneLoginInfo;
    }
}
