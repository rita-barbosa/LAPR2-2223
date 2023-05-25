package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class AgencyController {
    private AgencyRepository agencyRepository = null;

    public AgencyController() {
        getAgencyRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public AgencyController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }


    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            //Get the PersonRepository
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public boolean createAgency(String name, String passportCardNumber, String taxNumber, String emailAddress,
                                String phoneNumber, String password, String streetName, String city, String district, String state,
                                String zipcode) {

        AgencyRepository agencyRepository = getAgencyRepository();

        Person person = new Person(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, AuthenticationController.ROLE_CLIENT,
                streetName, city, district, state, zipcode);

        agencyRepository.add(new Agency());
        return true;
    }

    public List<Agency> getAgencies(){
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgenciesList();
    }
}
