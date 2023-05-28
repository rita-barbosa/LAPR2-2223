package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserControllerIT {

    @Test
    void ensureCreatePersonWorksForNonExistentPerson() {
        PersonRepository personRepository = new PersonRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        RegisterUserController ctrl = new RegisterUserController(authenticationRepository, personRepository);

        boolean result = ctrl.createPerson("Joseph", "C12345678", "123-23-4566", "joseph@email.com", "945-835-9008", "JOSep45"
                , "St. Mark's Place", "Manhattan", "East Village", "NY", "45678");
    }

    @Test
    void ensureCreatePersonWorksForExistentPerson() {
        PersonRepository personRepository = new PersonRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Employee employee = new Employee(1234, "Joseph", "C12345678", "123-23-4566", "joseph@email.com",
                "agent", "945-835-9008", "East Village", "Manhattan", "NY", "45678", "St. Mark's Place");
        personRepository.add(employee);
        RegisterUserController ctrl = new RegisterUserController(authenticationRepository, personRepository);

        boolean result = ctrl.createPerson("Joseph", "C12345678", "123-23-4566", "joseph@email.com", "945-835-9008", "JOSep45"
                , "St. Mark's Place", "Manhattan", "East Village", "NY", "45678");
    }
}