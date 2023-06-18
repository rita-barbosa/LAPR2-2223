package pt.ipp.isep.dei.esoft.project.domain.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VisitDtoTest {

    @Test
    void ensureGetIdWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(id, visitDto.getId());
    }

    @Test
    void ensureGetStartHourWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(startHour, visitDto.getStartHour());
    }

    @Test
    void ensureGetEndHourWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(endHour, visitDto.getEndHour());
    }

    @Test
    void ensureGetVisitDateWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(visitDate, visitDto.getVisitDate());
    }

    @Test
    void ensureGetUserNameWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(userName, visitDto.getUserName());
    }

    @Test
    void ensureGetUserPhoneNumberWorks() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(userPhoneNumber, visitDto.getUserPhoneNumber());
    }

    @Test
    void testEqualsSameObjects() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(visitDto, visitDto);
    }

    @Test
    void testEqualsDifferentObjects() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, 32, acceptanceStatus);

        assertEquals(visitDto, visitDto1);
    }

    @Test
    void testHashCodeSameObjects() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(visitDto.hashCode(), visitDto.hashCode());
    }

    @Test
    void testHasCodeDifferentObjects(){
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, 32, acceptanceStatus);

        assertEquals(visitDto.hashCode(), visitDto1.hashCode());
    }

    @Test
    void testToString() {
        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        String expected = "Visit Date:10/06/2023\nStart Hour:10\nEnd Hour:12\nUsername:someone\nUser Phone Number:12345678\n\n";

        assertEquals(expected, visitDto.toString());

    }
}