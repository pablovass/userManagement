package com.vallejos.pojo.mapper;

import com.vallejos.pojo.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    // Método personalizado para mapear de Person a UserInfo
    PersonInfo mapToUserInfo(Person person);

}