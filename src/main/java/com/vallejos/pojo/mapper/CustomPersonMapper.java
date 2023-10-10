package com.vallejos.pojo.mapper;


import com.vallejos.pojo.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class CustomPersonMapper implements PersonMapper {


    @Override
    public PersonInfo mapToUserInfo(Person person) {
        if (person == null) {
            return null;
        }
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(person.getId());
        personInfo.setCreated(person.getCreated());
        personInfo.setLastLogin(person.getLastLogin());
        personInfo.setToken(person.getToken());
        personInfo.setActive(person.getIsActive());


        return personInfo;
    }
}
