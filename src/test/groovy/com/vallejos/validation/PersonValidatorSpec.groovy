package com.vallejos.validation
import com.vallejos.pojo.Person
import com.vallejos.repository.PersonRepository
import spock.lang.Specification

class PersonValidatorSpec extends Specification {

    def personRepository = Mock(PersonRepository)
    def personValidator = new PersonValidator(personRepository)

    def "isValidEmail should return true for a valid email"() {
        when:
        def isValid = personValidator.isValidEmail("valid@email.com")

        then:
        isValid == true
    }

    def "isValidEmail should return false for an invalid email"() {
        when:
        def isValid = personValidator.isValidEmail("invalid_email")

        then:
        isValid == false
    }

    def "isValidPassword should return false for a valid password"() {
        when:
        def isValid = PersonValidator.isValidPassword("ValidP@ss1")

        then:
        isValid == false
    }
    def "isValidPassword should return true for a valid password"() {
        when:
        def isValid = PersonValidator.isValidPassword("a2asfGfdfdf4")

        then:
        isValid
    }

    def "isValidPassword should return false for an invalid password"() {
        when:
        def isValid = PersonValidator.isValidPassword("invalidpassword")

        then:
        isValid == false
    }

    def "existsByEmail should return true when email exists in the repository"() {
        given:
        personRepository.findByEmail("existing@email.com") >> new Person()

        when:
        def exists = personValidator.existsByEmail("existing@email.com")

        then:
        exists
    }


    def "existsByEmail should return false when email does not exist in the repository"() {
        given:
        personRepository.findByEmail("nonexistent@email.com") >> null

        when:
        def exists = personValidator.existsByEmail("nonexistent@email.com")

        then:
        exists == false
    }

    def "existByToken should return true when token exists in the repository"() {
        given:
        personRepository.existsByToken("validToken") >> true

        when:
        def exists = personValidator.existByToken("validToken")

        then:
        exists == true
    }

    def "existByToken should return false when token does not exist in the repository"() {
        given:
        personRepository.existsByToken("nonexistentToken") >> false

        when:
        def exists = personValidator.existByToken("nonexistentToken")

        then:
        exists == false
    }
}
