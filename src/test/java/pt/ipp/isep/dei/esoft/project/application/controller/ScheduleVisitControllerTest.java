package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVisitControllerTest {

    @Test
    void ensureGetPropertyTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        List<PropertyType> expected = propertyTypeRepository.getPropertyTypeList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<PropertyType> propertyTypeList = controller.getPropertyTypeList();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
    }

    @Test
    void ensureGetBusinessTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        List<BusinessType> expected = businessTypeRepository.getBusinessTypeList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<BusinessType> businessTypeList = controller.getBusinessTypeList();

        assertArrayEquals(expected.toArray(), businessTypeList.toArray());
    }


    @Test
    void ensureGetCriteriaListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();


        List<String> expected = criteriaRepository.getCriteriaList();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<String> criteria = controller.getCriteriaList();

        assertArrayEquals(expected.toArray(), criteria.toArray());
    }


    @Test
    void ensureGetAgenciesListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        List<Agency> expected = agencyRepository.getAgenciesList();

        CreateRequestController controller =
                new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        List<Agency> agencies = controller.getAgenciesList();

        assertArrayEquals(expected.toArray(), agencies.toArray());
    }

    @Disabled
    @Test
    void getAnnouncementListDto() {
    }

    @Disabled
    @Test
    void getAllAnnouncementsList() {
    }

    @Disabled
    @Test
    void toModel() {
    }

    @Disabled
    @Test
    void toDto() {
    }

    @Disabled
    @Test
    void scheduleVisit() {
    }


    @Test
    void getAnnouncementsByBusinessType() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByBusinessType("Sale");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();
            for (Announcement announce : announcements) {
                if (announce.getRequest().getBusiness().getBusinessType().getDesignation().equalsIgnoreCase("Sale")) {
                    actual.add(announce);
                }
            }
        }

        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByPropertyType() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPropertyType("Land");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();
            for (Announcement announce : announcements) {
                if (announce.getRequest().getProperty().getPropertyType().getDesignation().equalsIgnoreCase("Land")) {
                    actual.add(announce);
                }
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    void getAnnouncementsByNumberBedrooms() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByNumberBedrooms(2);

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            List<Announcement> announcements = agency.getAnnouncements().getList();


            for (Announcement announce : announcements) {
                if (announce.getRequest().getProperty() instanceof Residence) {
                    Residence residence = (Residence) announce.getRequest().getProperty();
                    if ((residence.getNumberBedroom().equals(2))) {
                        actual.add(announce);
                    }
                }
            }
        }
        assertEquals(expected, actual);
    }

   @Disabled
   @Test
    void getAnnouncementsByPrice() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        PersonRepository personRepository = new PersonRepository();

        Comparator<Announcement> sortPricesByAscendingOrder = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                Double value1 = a1.getRequest().getBusiness().getPrice();
                Double value2 = a2.getRequest().getBusiness().getPrice();

                return value1.compareTo(value2);
            }
        };

        ScheduleVisitController controller =
                new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<Announcement> expected = controller.getAnnouncementsByPrice("Ascending");

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency : agencies) {
            actual.addAll(agency.sortAnnouncementsByAscendingPrice(agency.getAnnouncementsList()));
        }

        assertEquals(expected, actual);
    }

    @Disabled
    @Test
    void getAnnouncementsByCity() {
    }

    @Disabled
    @Test
    void getAnnouncementsByState() {
    }


}