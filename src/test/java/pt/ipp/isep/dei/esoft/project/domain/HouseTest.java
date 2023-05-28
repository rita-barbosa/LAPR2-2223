package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    void ensureHouseIsCreatedSuccessfully() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);
    }

    @Test
    void ensureHouseDoesNotEqualNull() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);

        assertNotEquals(house, null);
    }

    @Test
    void ensureHouseDoesNotEqualOtherObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);

        assertNotEquals(house, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);

        assertEquals(house, house);
    }

    @Test
    void testTwoPropertiesWithSameAttributesEquals() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);
        House house1 = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);

        assertEquals(house, house1);
    }

    @Test
    void testEqualsDifferentObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);
        House house1 = new House(new PropertyType("house"), av, 38.4,
                "street 3", "city 3", "district 3", "st4", "12340", false,
                false, 4, SunExposureTypes.SOUTH, 0, 1,
                12.5, uriList);

        assertNotEquals(house, house1);
    }

    @Test
    void ensureToStringWorks() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);
        String expected = String.format("Property Type: house\n" +
                        "Area: 32,40 m²\n" +
                        "Distance from city center: 12,50 miles\n" +
                        "Location: street 3, city 3, district 3, st3, 12340\n" +
                        "Photographs:\n" +
                        "    * https://www.example.com/images/photo.jpg\n" +
                        "Number of Bedrooms: 2 \n" +
                        "Number of Bathrooms: 0 \n" +
                        "Parking Spaces: 2 \n" +
                        "Available Equipments:\n" +
                        "    * AC\n" +
                        "    * Coffee Machine\n" +
                        "    * Heating Floor\n" +
                        "    * Massage Chair\n" +
                        "    * Jacuzzi\n" +
                        "    * Automatic Lights System\n" +
                        "Basement: true \n" +
                        "Inhabitable Loft: false \n" +
                        "Sun Exposure: North\n"
        );
        assertEquals(expected, house.toString());
    }

    @Test
    void testHashCodeSameObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);

        assertEquals(house.hashCode(), house.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");
        House house = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true,
                false, 2, SunExposureTypes.NORTH, 2, null,
                12.5, uriList);
        House house1 = new House(new PropertyType("house"), av, 38.4,
                "street 3", "city 3", "district 3", "st4", "12340", false,
                false, 4, SunExposureTypes.SOUTH, 0, 1,
                12.5, uriList);

        assertNotEquals(house.hashCode(), house1.hashCode());
    }
}