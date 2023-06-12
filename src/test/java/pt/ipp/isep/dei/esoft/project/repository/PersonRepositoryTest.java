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
        Person person = new Person("name", "C12345678", "234-34-4567", "client1@this.app", "345-345-5678", "client", "street", "city", "district", "ST", "12345");

        Boolean returnPerson = personRepository.add(person);

        assertTrue(returnPerson);
    }

    @Test
    void ensureAddFailsforExistingPerson() {
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("name", "C12345678", "234-34-4567", "client1@this.app", "345-345-5678", "client", "street", "city", "district", "ST", "12345");
        personRepository.add(person);
        Person person1 = new Person("name", "C12345678", "234-34-4567", "client1@this.app", "345-345-5678", "client", "street", "city", "district", "ST", "12345");

        assertFalse(personRepository.add(person1));
    }

    @Test
    void ensureGetPersonByEmailWorks() {
        PersonRepository personRepository = new PersonRepository();
        Person person = new Person("name", "C12345678", "234-34-4567", "client1@this.app", "345-345-5678", "client", "street", "city", "district", "ST", "12345");

        personRepository.add(person);

        Optional<Person> returnPerson = personRepository.getPersonByEmail("client1@this.app");

        assertEquals(person, returnPerson.get());
    }

    @Test
    void ensureGetPersonByEmailFailsForNonExistingPerson() {
        PersonRepository personRepository = new PersonRepository();
        String personEmail = "client1@this.app";
        Person person = new Person("name", "C12345678", "234-34-4567", "client1@this.app", "345-345-5678", "client", "street", "city", "district", "ST", "12345");

        personRepository.add(person);
        String personEmail1 = "client2@this.app";

        Optional<Person> result = personRepository.getPersonByEmail(personEmail1);
        assertTrue(result.isEmpty());
    }

}