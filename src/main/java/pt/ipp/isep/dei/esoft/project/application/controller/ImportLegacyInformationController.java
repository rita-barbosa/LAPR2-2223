package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class ImportLegacyInformationController {

    private AgencyRepository agencyRepository;
    private PersonRepository personRepository;

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    private PersonRepository getPersonRepository() {
        if (personRepository == null) {
            Repositories repositories = Repositories.getInstance();
            personRepository = repositories.getPersonRepository();
        }
        return personRepository;
    }

    public Boolean importInformationFromFile(String filePath) {
        Boolean success = false;
        Optional<List<LegacySystemDto>> newList;

        newList = LegacySystem.importInformation(filePath);

        if (newList.isPresent()) {
            for (LegacySystemDto dto : newList.get()) {
                Optional<Agency> newAgency = registerAgency(dto);
                if (newAgency.isPresent()) {
                    Employee newAgent = registerAgent(newAgency.get());
                    Person newOwner = registerOwner(dto);
                    String ownerEmail = newOwner.getEmailAddress().getEmail();
                    success = publishAnnouncement(dto, newAgency.get(), newAgent, ownerEmail);
                }
                if (!success) {
                    return false;
                }
            }
        }
        return success;
    }

    private Boolean publishAnnouncement(LegacySystemDto dto, Agency agency, Employee agent, String ownerEmail) {
        Boolean operationSuccess = false;

//        Request newRequest = getRequestFromLegacy(dto, agency, agent, ownerEmail);

        return operationSuccess;

    }

//    private Request getRequestFromLegacy(LegacySystemDto dto, Agency agency, Employee agent, String ownerEmail) {
//        return LegacySystemMapper.toModelRequest(dto)
//
//    }

    private Person registerOwner(LegacySystemDto dto) {
        return getPersonRepository().registerPerson(dto);
    }

    private Employee registerAgent(Agency newAgency) {
        Employee agent = newAgency.createDefaultAgent();
        getPersonRepository().add(agent);
        return agent;
    }

    private Optional<Agency> registerAgency(LegacySystemDto dto) {
        return getAgencyRepository().registerAgency(dto);
    }
}
