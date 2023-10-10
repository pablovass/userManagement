package com.vallejos.pojo.mapper;
import com.vallejos.pojo.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    // Método personalizado para mapear de Person a UserInfo
    PersonLoginInfo mapToUserInfo(Person person);

}
