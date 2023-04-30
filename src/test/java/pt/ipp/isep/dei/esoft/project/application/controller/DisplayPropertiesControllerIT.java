package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisplayPropertiesControllerIT {

    @Test
    void ensureGetAgenciesListWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        Agency agency = new Agency(1705);
        Agency agency1 = new Agency(2345);

        agencyRepository.add(agency);
        agencyRepository.add(agency1);

        ArrayList<Agency> expected = new ArrayList<>();
        expected.add(agency);
        expected.add(agency1);

        CreateRequestController controller = new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        List<Agency> agencies = controller.getAgenciesList();

        assertArrayEquals(expected.toArray(), agencies.toArray());
    }

    @Test
    void ensureGetAnnouncementsListWorks() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 5678.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);

        List<Announcement> resultAnnouncements = new ArrayList<>();

        for (Agency agency2 : agencies) {
            resultAnnouncements.addAll(agency2.getAnnouncementsList());
        }
        assertEquals(expected, resultAnnouncements);
    }

    @Test
    void ensureSortAnnouncementsByMostRecentAdded() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.of(2023,5,15), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 300.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements.add(announcement);
        resultAnnouncements.add(announcement1);

        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2) {
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };

        resultAnnouncements.sort(Collections.reverseOrder(acceptanceDate));

        assertEquals(expected, resultAnnouncements);
    }

    @Test
    void ensureGetAnnouncementsByBusinessTypeWorks() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 300.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();

        for (Agency agency2 : agencies) {
            resultAnnouncements.addAll(agency2.announcementHasBusinessType(agency2.getAnnouncementsList(),"Sale"));
        }
        assertEquals(expected, agency.announcementHasBusinessType(resultAnnouncements, "Sale"));
    }

    @Test
    void ensureGetAnnouncementsByPropertyType() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property1 = new House(new PropertyType("house"), av, 32.4,
                "street1", "city1", "district1", "st1", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();

        for (Agency agency2 : agencies) {
            resultAnnouncements.addAll(agency2.announcementHasPropertyType(agency2.getAnnouncementsList(),"House"));
        }

        assertEquals(expected, agency.announcementHasPropertyType(resultAnnouncements, "House"));
    }

    @Test
    void ensureGetAnnouncementsByNumberBedrooms() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();

        for (Agency agency2 : agencies) {
            resultAnnouncements.addAll(agency2.announcementHasNumberBedrooms(agency2.getAnnouncementsList(),1));
        }

        assertEquals(expected, agency.announcementHasNumberBedrooms(resultAnnouncements, 1));
    }

    @Test
    void ensureGetAnnouncementsByPrice() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","city1","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request1);
        agency.publishAnnouncement(employee, commissionType, 234.0, request);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements.add(announcement);
        resultAnnouncements.add(announcement1);

        assertEquals(expected, agency.sortAnnouncementsByDescendingPrice(resultAnnouncements));
    }

    @Test
    void ensureGetAnnouncementsByCity() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","city1","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street1", "city1", "district1", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city4", "district4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request1);
        agency.publishAnnouncement(employee, commissionType, 234.0, request);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements.add(announcement);
        resultAnnouncements.add(announcement1);

        assertEquals(expected, agency.sortAnnouncementsByDescendingCity(resultAnnouncements));
    }

    @Test
    void ensureGetAnnouncementsByState() {
        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","city1","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street1", "city1", "district1", "st5", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city4", "district4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request1);
        agency.publishAnnouncement(employee, commissionType, 234.0, request);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements.add(announcement);
        resultAnnouncements.add(announcement1);

        assertEquals(expected, agency.sortAnnouncementsByAscendingState(resultAnnouncements));
    }

    @Test
    void ensureGetBusinessTypeListWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);

        BusinessType businessType1 = new BusinessType("Lease");
        businessTypeRepository.add(businessType1);

        ArrayList<BusinessType> expected = new ArrayList<>();
        expected.add(businessType);
        expected.add(businessType1);

        CreateRequestController controller = new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        List<BusinessType> businessTypeList = controller.getBusinessTypes();

        assertArrayEquals(expected.toArray(), businessTypeList.toArray());
    }

    @Test
    void ensureGetPropertyTypeListWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);

        PropertyType propertyType1 = new PropertyType("Apartment");
        propertyTypeRepository.add(propertyType1);

        ArrayList<PropertyType> expected = new ArrayList<>();
        expected.add(propertyType);
        expected.add(propertyType1);

        CreateRequestController controller = new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        List<PropertyType> propertyTypeList = controller.getPropertyTypes();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
    }
}