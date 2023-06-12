package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void ensurePropertyIsCreatedSuccessfully() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
    }

    @Test
    void ensurePropertyDoesNotEqualNull() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertNotEquals(property, null);
    }

    @Test
    void ensurePropertyDoesNotEqualOtherObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertNotEquals(property, new Object());
    }

    @Test
    void ensureTheSameObjectIsEqual() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        assertEquals(property, property);
    }

    @Test
    void testTwoPropertiesWithSameAttributesEquals() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Property property1 = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertEquals(property, property1);
    }

    @Test
    void testEqualsDifferentObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> uriList1 = new ArrayList<>();
        uriList.add("https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_2x3.jpg");
        Property property1 = new Property(new PropertyType("Apartment"), 12.6, 150.8, uriList1,
                "May Av.", "New York", "York", "AL", "58240");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertNotEquals(property, property1);
    }

    @Test
    void ensureToStringWorks() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        String expected = String.format("Property Type: land\n" +
                "Area: 35,50 m²\n" +
                "Distance from city center: 89,30 miles\n" +
                "Location: street, city, district, ST, 12345\n" +
                "Photographs:\n" +
                "    * https://www.example.com/images/photo.jpg\n");
        assertEquals(expected, property.toString());
    }

    @Test
    void ensureGetPropertyTypeWorks() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        PropertyType propertyType = new PropertyType("land");

        assertEquals(property.getPropertyType(), propertyType);
    }

    @Test
    void ensureGetLocationWorks() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        Location location = new Location("street", "city", "district", "ST", "12345");

        assertEquals(property.getLocation(), location);
    }

    @Test
    void testHashCodeSameObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_2x3.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertEquals(property.hashCode(), property.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> uriList1 = new ArrayList<>();
        uriList.add("https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_2x3.jpg");
        Property property1 = new Property(new PropertyType("Apartment"), 12.6, 150.8, uriList1,
                "May Av.", "New York", "York", "AL", "58240");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");

        assertNotEquals(property.hashCode(), property1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_2x3.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Property clone = property.clone();
        assertEquals(property, clone);
    }
}