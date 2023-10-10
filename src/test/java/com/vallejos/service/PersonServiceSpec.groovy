package com.vallejos.service

import spock.lang.Specification
import com.vallejos.pojo.Person
import com.vallejos.pojo.mapper.PersonCreatedResponse
import com.vallejos.repository.PersonRepository
import com.vallejos.util.DateFormatter
import com.vallejos.util.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder


class PersonServiceSpec extends Specification {

    def "test createUser method"() {
        given:
        def personRepository = Mock(PersonRepository)
        def jwtTokenProvider = Mock(JwtTokenProvider)
        def passwordEncoder = Mock(PasswordEncoder)
        def dateFormatter = Mock(DateFormatter)

        def personService = new PersonService(personRepository,
                jwtTokenProvider,
                dateFormatter,
                null,
                passwordEncoder,
                null,
                null)

        def person = new Person(username: "testuser",
                password: "testpassword",
                // Set other properties as needed
        )

        personRepository.save(_) >> person

        when:
        def createdPersonResponse = personService.createUser(person)

        then:
        createdPersonResponse instanceof PersonCreatedResponse
        createdPersonResponse.personInfo.username == person.username

    }


}
