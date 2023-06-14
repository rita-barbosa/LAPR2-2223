package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ImportLegacyInformationControllerIT {

    @Disabled
    @Test
    void importInformationFromFile() {
//        ImportLegacyInformationController controller =
//                new ImportLegacyInformationController(/*agencyRepository, personRepository*/);
//
//        File file = new File("importInformationTestFile.csv");
//
//
//        boolean success = false;
//        Optional<List<LegacySystemDto>> newList;
//
//        newList = LegacySystem.importInformation(file.getPath());
//        try {
//            if (newList.isPresent()) {
//                for (LegacySystemDto dto : newList.get()) {
//                    Optional<Agency> newAgency = controller.registerAgency(dto);
//                    if (newAgency.isPresent()) {
//                        Employee newAgent = controller.registerAgent(newAgency.get());
//                        Person newOwner = controller.registerOwner(dto);
//                        String ownerEmail = newOwner.getEmailAddress().getEmail();
//                        controller.publishAnnouncement(dto, newAgency.get(), newAgent, ownerEmail);
//                        success = true;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            success = false;
//        }
//        assertTrue(success);
    }
}