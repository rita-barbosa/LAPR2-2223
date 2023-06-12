package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.RequestDto;

import static org.junit.jupiter.api.Assertions.*;
class RequestDtoTest {

    @Test
    void ensureGetPropertyAttributesWorks() {
        String propertyAttributes = "propertyAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(propertyAttributes, dto.getPropertyAttributes());
    }

    @Test
    void ensureGetBusinessAttributesWorks() {
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(businessAttributes, dto.getBusinessAttributes());
    }

    @Test
    void ensureGetRequestDateWorks() {
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(requestDate, dto.getRequestDate());
    }

    @Test
    void ensureGetRequestIdWorks() {
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(id, dto.getRequestId());
    }

    @Test
    void testToString() {
        String propertyAttributes = "propertyAttributes";
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto(propertyAttributes, businessAttributes, id, requestDate);

        String expected = "businessAttributespropertyAttributesRequest Date: 2020-10-10\n";

        assertEquals(expected, dto.toString());
    }

    @Test
    void testEqualsSameObjects() {
        String propertyAttributes = "propertyAttributes";
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto(propertyAttributes, businessAttributes, id, requestDate);

        assertEquals(dto, dto);
    }

    @Test
    void testEqualsDifferentObjects() {
        String propertyAttributes = "propertyAttributes";
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto(propertyAttributes, businessAttributes, id, requestDate);
        RequestDto dto1 = new RequestDto("something", "sale", 0, "2015-12-13");

        assertNotEquals(dto, dto1);
    }

    @Test
    void testHashCodeSameObject() {
        String propertyAttributes = "propertyAttributes";
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto(propertyAttributes, businessAttributes, id, requestDate);

        assertEquals(dto.hashCode(), dto.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        String propertyAttributes = "propertyAttributes";
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto(propertyAttributes, businessAttributes, id, requestDate);
        RequestDto dto1 = new RequestDto("something", "sale", 0, "2015-12-13");

        assertNotEquals(dto.hashCode(), dto1.hashCode());
    }
}