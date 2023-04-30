package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Location;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    @Test
    void testAddPerson() {
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("client1@this.app");

        Optional<Person> returnPerson = personRepository.add(person);

        assertEquals(person, returnPerson.get());
    }

    @Test
    void ensureAddFailsforExistingPerson(){
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("client1@this.app");
        personRepository.add(person);
        Person person1 = new Person("client1@this.app");

        assertTrue(personRepository.add(person1).isEmpty());
    }

    @Test
    void ensureGetPersonByEmailWorks() {
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("client1@this.app");
        personRepository.add(person);

        Optional<Person> returnPerson = personRepository.getPersonByEmail("client1@this.app");

        assertEquals(person, returnPerson.get());
    }

    @Test
    void ensureGetPersonByEmailFailsForNonExistingPerson() {
        PersonRepository personRepository = new PersonRepository();
        String personEmail = "client1@this.app";
        Person person = new Person(personEmail);
        personRepository.add(person);
        String personEmail1 = "client2@this.app";

        Optional<Person> result = personRepository.getPersonByEmail(personEmail1);
        assertTrue(result.isEmpty());
    }

}