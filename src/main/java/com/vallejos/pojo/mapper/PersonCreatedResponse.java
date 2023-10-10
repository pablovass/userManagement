package com.vallejos.pojo.mapper;

import com.vallejos.pojo.mapper.PersonInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreatedResponse {
    private PersonInfo user_created;


}
