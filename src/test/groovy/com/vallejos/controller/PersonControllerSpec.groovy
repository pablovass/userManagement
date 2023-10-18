package com.vallejos.controller

import com.vallejos.pojo.dto.PersonDto
import com.vallejos.service.PersonService
import com.vallejos.pojo.dto.PersonPostDtoResponse
import org.springframework.http.HttpStatus
import spock.lang.Specification

class PersonControllerSpec extends Specification {

    def personService = Mock(PersonService)
    def personController = new PersonController(personService)

    def "signUp should create a new user and return a 201 Created response"() {
        given:
        def personDto = new PersonDto()
        def createdPersonResponse = new PersonPostDtoResponse()

        personService.createUser(personDto) >> createdPersonResponse

        when:
        def response = personController.signUp(personDto)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == createdPersonResponse

        1 * personService.createUser(personDto)
    }

    def "loginByToken should retrieve user information by token and return a 201 Created response"() {
        given:
        def token = "valid-token"
        def personDto = new PersonDto()

        personService.getPersonByToken(token) >> personDto

        when:
        def response = personController.loginByToken(token)

        then:
        response.statusCode == HttpStatus.CREATED
        response.body == personDto

        1 * personService.getPersonByToken(token)
    }
}
