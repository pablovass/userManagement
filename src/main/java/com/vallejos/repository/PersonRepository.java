package com.vallejos.repository;

import com.vallejos.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Person findByEmail(String email);
    boolean existsByToken(String token);
    Person findByToken(String token);

}
