package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.LegacySystemMapper;
import pt.ipp.isep.dei.esoft.project.domain.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Person repository.
 */
public class PersonRepository implements Serializable {

    /**
     * The People.
     */
    private List<Person> people = new ArrayList<>();

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

    public Person registerPerson(LegacySystemDto dto) {
        Person newPerson = LegacySystemMapper.toModelPerson(dto);
        add(newPerson);
        return newPerson;
    }

    public void savePeople() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("binaryFile/personBin.data"))) {
            outputStream.writeObject((List<Person>) people);
            System.out.println("Saved person.");
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't save person.");
        }
    }

    public void loadPeople() {
        File file = new File("binaryFile/personBin.data");
        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file.getPath()))) {
                people = (ArrayList<Person>) inputStream.readObject();
                System.out.println("Loaded people.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("ERROR: Couldn't load people.");
            }
        }
    }
}
