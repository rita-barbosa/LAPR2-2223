package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Person;

import javax.swing.text.html.Option;
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
    public Boolean add(Person person) {
        Optional<Person> existentPerson = findPerson(person);
        Optional<Person> newPerson;
        boolean addedPerson;
        if (existentPerson.isPresent()) {
            Person updatedRoles = existentPerson.get().clone();
            updatedRoles.setRole(person.getRoles());
            newPerson = Optional.of(updatedRoles);
            people.remove(existentPerson.get());
            addedPerson = false;
        } else {
            newPerson = Optional.of(person.clone());
            addedPerson = true;
        }
        people.add(newPerson.get());
        return addedPerson;
    }

    /**
     * This method checks if there is already a person in the list with the specified tax number.
     *
     * @param person - the person to look for.
     * @return an Optional of a Person instance.
     */
    private Optional<Person> findPerson(Person person) {
        Optional<Person> returnPerson = Optional.empty();
        for (Person existentPerson : people) {
            if (existentPerson.getTaxNumber().equals(person.getTaxNumber())) {
                returnPerson = Optional.of(person);
            }
        }
        return returnPerson;
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
