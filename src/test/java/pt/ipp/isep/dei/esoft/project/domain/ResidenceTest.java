package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResidenceTest {

    @Test
    void ensureResidenceIsCreatedSuccessfully() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
    }

    @Test
    void ensureResidenceDoesNotEqualNull() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        assertNotEquals(residence, null);
    }

    @Test
    void ensureResidenceDoesNotEqualOtherObject() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        assertNotEquals(residence, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
        assertEquals(residence, residence);
    }

    @Test
    void testTwoResidencesWithSameAttributesEquals() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
        Residence residence1 = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        assertEquals(residence, residence1);
    }

    @Test
    void testEqualsDifferentObject() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
        Residence residence1 = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12845", 5, 2,
                null, 15.2, uriList);

        assertNotEquals(residence, residence1);
    }

    @Test
    void ensureToStringWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
        String expected = String.format("Property Type: land\n" +
                "Area: 35,50 m²\n" +
                "Distance from city center: 15,20 m\n" +
                "Location: street, city, district, state, 12345\n" +
                "Photographs:\n" +
                "    uri=https://www.example.com/images/photo.jpg\n" +
                "Number of Bedrooms: 2 \n" +
                "Number of Bathrooms: 0 \n" +
                "Parking Space: 2 \n" +
                "AvailableEquipment:\n" +
                "    description='AC'\n" +
                "    description='Coffee Machine'\n" +
                "    description='Heating Floor'\n" +
                "    description='Massage Chair'\n" +
                "    description='Jacuzzi'\n" +
                "    description='Automatic Lights System'\n");
        assertEquals(expected, residence.toString());
    }

    @Test
    void ensureGetAvailableEquipmentWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        List<AvailableEquipment> avEquip = new ArrayList<>();
        avEquip.add(new AvailableEquipment("AC"));
        avEquip.add(new AvailableEquipment("Coffee Machine"));
        avEquip.add(new AvailableEquipment("Heating Floor"));
        avEquip.add(new AvailableEquipment("Massage Chair"));
        avEquip.add(new AvailableEquipment("Jacuzzi"));
        avEquip.add(new AvailableEquipment("Automatic Lights System"));

        assertEquals(avEquip, residence.getAvailableEquipment());
    }

    @Test
    void ensureGetNumberBedroomWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 9,
                null, 15.2, uriList);

        assertEquals(residence.getNumberBedroom(), 9);
    }

    @Test
    void ensureGetNumberBathroomWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                4, 15.2, uriList);

        assertEquals(residence.getNumberBathroom(), 4);
    }

    @Test
    void ensureGetNumberBathroomisNullWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        assertNull(residence.getNumberBathroom());
    }

    @Test
    void ensureGetParkingSpaceWorks() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 1, 2,
                null, 15.2, uriList);

        assertEquals(residence.getParkingSpace(), 1);
    }

    @Test
    void testHashCodeSameObject() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);

        assertEquals(residence.hashCode(), residence.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Residence residence = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12345", 2, 2,
                null, 15.2, uriList);
        Residence residence1 = new Residence(new PropertyType("land"), (35.5), av, "street",
                "city", "district", "state", "12845", 5, 2,
                null, 15.2, uriList);

        assertNotEquals(residence.hashCode(), residence1.hashCode());
    }

}