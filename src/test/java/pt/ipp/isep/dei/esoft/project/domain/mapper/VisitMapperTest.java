package pt.ipp.isep.dei.esoft.project.domain.mapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Visit;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapperTest {

    @Test
    void ensureToDtoWorks() {
        List<Visit> visitsList = new ArrayList<>();
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");

        visitsList.add(visit);

        List<VisitDto> visitDtoList = new ArrayList<>();
        VisitDto dto = new VisitDto(visit.getVisitDate(), visit.getStartHour(), visit.getEndHour(), visit.getUserName(), visit.getUserPhoneNumber());

        visitDtoList.add(dto);
        assertTrue(VisitMapper.toDto(visitsList).isPresent());
        assertEquals(visitDtoList, VisitMapper.toDto(visitsList).get());
    }

    @Test
    void testToDto() {
        Visit visit = new Visit(12, 5, 2023, 12, 15, "Jake Moon",
                "555-775-5555");

        LocalDate visitDate = visit.getVisitDate();
        Integer startHour = visit.getStartHour();
        Integer endHour = visit.getEndHour();
        String userName = visit.getUserName();
        String userPhoneNumber = visit.getUserPhoneNumber();
        Integer id = visit.getId();
        Boolean acceptanceStatus = visit.getAcceptanceStatus();

        VisitDto dto = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);

        assertEquals(dto, VisitMapper.toDto(visit));



    }
}