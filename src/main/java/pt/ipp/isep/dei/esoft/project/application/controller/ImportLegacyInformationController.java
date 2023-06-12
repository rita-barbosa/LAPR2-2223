package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.LegacySystemMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Optional;

public class ImportLegacyInformationController {

    private AgencyRepository agencyRepository;
    private PersonRepository personRepository;

    public ImportLegacyInformationController() {
        getAgencyRepository();
        getPersonRepository();
//        agencyRepository.loadAgencies();
//        personRepository.loadPeople();
    }

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
        boolean success;
        Optional<List<LegacySystemDto>> newList;
        newList = LegacySystem.importInformation(filePath);
        try {
            if (newList.isPresent()) {
                for (LegacySystemDto dto : newList.get()) {
                    Optional<Agency> newAgency = registerAgency(dto);
                    if (newAgency.isPresent()) {
                        Employee newAgent = registerAgent(newAgency.get());
                        Person newOwner = registerOwner(dto);
                        String ownerEmail = newOwner.getEmailAddress().getEmail();
                        publishAnnouncement(dto, newAgency.get(), newAgent, ownerEmail);
                    }
                }
            }
            success = true;
        } catch (Exception e) {
            success = false;
        }
//        saveBinaryFile();
        return success;
    }

//    private void saveBinaryFile() {
//        Repositories rep = Repositories.getInstance();
//        PersonRepository people = rep.getPersonRepository();
//        people.savePeople();
//        agencyRepository.saveAgencies();
//    }

    private Boolean publishAnnouncement(LegacySystemDto dto, Agency agency, Employee agent, String ownerEmail) throws
            NumberFormatException {
        Request newRequest = getRequestFromLegacy(dto, agency, agent, ownerEmail);
        Announcement newAnnouncement = new Announcement(agent, dto.getCommission(), newRequest, dto.getPropertyDateSale(), dto.getPropertyPrice());
        return agency.addAnnouncement(newAnnouncement);
    }

    private Request getRequestFromLegacy(LegacySystemDto dto, Agency agency, Employee agent, String ownerEmail) throws
            RuntimeException {
        Request newRequest = LegacySystemMapper.toModelRequest(dto);
        newRequest.setResponsibleAgent(agent);
        newRequest.setOwnerEmail(ownerEmail);
        agency.addRequest(newRequest);
        return newRequest;
    }

    private Person registerOwner(LegacySystemDto dto) {
        return getPersonRepository().registerPerson(dto);
    }

    private Employee registerAgent(Agency newAgency) {
        Employee agent = newAgency.createDefaultAgent();
        getPersonRepository().add(agent);
        return agent;
    }

    private Optional<Agency> registerAgency(LegacySystemDto dto) throws IllegalArgumentException {
        return getAgencyRepository().registerAgency(dto);
    }
}
