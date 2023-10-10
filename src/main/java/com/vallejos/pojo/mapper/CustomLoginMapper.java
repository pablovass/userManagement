package com.vallejos.pojo.mapper;

import com.vallejos.pojo.Person;
import com.vallejos.pojo.Phone;
import com.vallejos.util.DateFormatter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class CustomLoginMapper implements LoginMapper {
    @Autowired
    private DateFormatter dateFormatter;

    @Override
    public PersonLoginInfo mapToUserInfo(Person person) {
        PersonLoginInfo personLoginInfo = new PersonLoginInfo();
        personLoginInfo.setId(person.getId().toString());
        personLoginInfo.setCreated(person.getCreated());
        personLoginInfo.setLastLogin(dateFormatter.formatLocalDateTime(LocalDateTime.now()));
        personLoginInfo.setToken(person.getToken());
        personLoginInfo.setIsActive(person.getIsActive());
        personLoginInfo.setName(person.getName());
        personLoginInfo.setEmail(person.getEmail());
        personLoginInfo.setPassword(person.getPassword());

        if (person.getPhones() != null) {
            personLoginInfo.setPhones(
                    person.getPhones().stream().map(this::mapToPhoneResponse).collect(Collectors.toList())
            );
        }

        return personLoginInfo;
    }

    private PhoneLoginInfo mapToPhoneResponse(Phone phone) {
        PhoneLoginInfo phoneLoginInfo = new PhoneLoginInfo();
        phoneLoginInfo.setNumber(phone.getNumber());
        phoneLoginInfo.setCitycode(phone.getCitycode());
        phoneLoginInfo.setContrycode(phone.getCountrycode());
        return phoneLoginInfo;
    }
}