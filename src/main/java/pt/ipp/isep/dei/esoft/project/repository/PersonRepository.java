package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Person repository.
 */
public class PersonRepository {

    /**
     * The People.
     */
    private final List<Person> people = new ArrayList<>();

    /**
     * Add optional.
     *
     * @param person the person
     * @return the optional
     */
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

    /**
     * Gets person by email.
     *
     * @param emailAddress the email address
     * @return the person by email
     */
    public Optional<Person> getPersonByEmail(String emailAddress) {
        Optional<Person> returnPerson = Optional.empty();
        for (Person person : people) {
            if (person.hasEmail(emailAddress)) {
                returnPerson = Optional.of(person);
            }
        }
        return returnPerson;
    }

    /**
     * Validate person boolean.
     *
     * @param person the person
     * @return the boolean
     */
    private boolean validatePerson(Person person) {
        return (!people.contains(person));
    }

}
