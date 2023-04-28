package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Optional;

public class RegisterUserController {
    private AuthenticationRepository authenticationRepository = null;
    private PersonRepository personRepository = null;

    public RegisterUserController() {
        getAuthenticationRepository();
        getPersonRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public RegisterUserController(AuthenticationRepository authenticationRepository, PersonRepository personRepository) {
        this.authenticationRepository = authenticationRepository;
        this.personRepository = personRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private PersonRepository getPersonRepository() {
        if (personRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the PersonRepository
            personRepository = repositories.getPersonRepository();
        }
        return personRepository;
    }

    public Optional<Person> createPerson(String name, String passportCardNumber, String taxNumber, String emailAddress,
                                         String phoneNumber, String streetName, String city, String district, String state,
                                         String zipcode, String clientRole) {

        return personRepository.add(new Person(name, passportCardNumber, taxNumber, emailAddress,
                phoneNumber, clientRole, streetName, city, district, state, zipcode));
    }

    public boolean addUserWithRole(String name, String emailAddress, String password, String clientRole) {
        return authenticationRepository.addUserWithRole(name, emailAddress, password, clientRole);
    }

}
