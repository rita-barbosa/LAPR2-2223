package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {

    private final List<Person> people = new ArrayList<>();

    public Optional<Person> add(Person person) {
        Optional<Person> newPerson = Optional.empty();
        boolean success = false;
        if (validatePerson(person)) {
            newPerson = Optional.of(person.clone());
            success = people.add(newPerson.get());
        }
        if (!success) {
            newPerson = Optional.empty();
        }
        return newPerson;
    }

    public Optional<Person> getPersonByEmail(String emailAddress) {
        Optional<Person> returnPerson = Optional.empty();
        for (Person person : people) {
            if (person.hasEmail(emailAddress)) {
                returnPerson = Optional.of(person);
            }
        }
        return returnPerson;
    }

    private boolean validatePerson(Person person) {
        return (!people.contains(person));
    }

}
