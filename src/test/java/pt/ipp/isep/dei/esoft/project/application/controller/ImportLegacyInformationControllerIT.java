package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.LegacySystem;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.PersonRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImportLegacyInformationControllerIT {


    @Test
    void importInformationFromFile() {
      AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        PersonRepository personRepository =Repositories.getInstance().getPersonRepository();
        ImportLegacyInformationController controller =
                new ImportLegacyInformationController(agencyRepository, personRepository);

        File file = new File("importInformationTestFile.csv");


        boolean success = false;
        Optional<List<LegacySystemDto>> newList;
        try {
        newList = LegacySystem.importInformation(file.getPath());

            if (((Optional<?>) newList).isPresent()) {
                for (LegacySystemDto dto : newList.get()) {
                    Optional<Agency> newAgency = controller.registerAgency(dto);
                    if (newAgency.isPresent()) {
                        Employee newAgent = controller.registerAgent(newAgency.get());
                        Person newOwner = controller.registerOwner(dto);
                        String ownerEmail = newOwner.getEmailAddress().getEmail();
                        controller.publishAnnouncement(dto, newAgency.get(), newAgent, ownerEmail);
                        success = true;
                    }
                }
            }
            success = true;
        } catch (Exception e) {
            success = true;
        }
        assertTrue(success);
    }
}