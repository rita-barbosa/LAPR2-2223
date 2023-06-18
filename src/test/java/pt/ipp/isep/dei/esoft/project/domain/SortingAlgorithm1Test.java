package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithm1Test {

    @Test
    void getSortDtoList() {
        List<VisitDto> sortDtoList = new ArrayList<>();

        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        LocalDate visitDate1 = LocalDate.of(2023, 6, 11);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate1, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        sortDtoList.add(visitDto);
        sortDtoList.add(visitDto1);

        SortingAlgorithm1 sortingAlgorithm1 = new SortingAlgorithm1(sortDtoList);

        List<VisitDto> expected = sortDtoList;

        assertEquals(expected, sortingAlgorithm1.getSortDtoList());
    }

    @Test
    void sort() {
        List<VisitDto> sortDtoList = new ArrayList<>();

        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        LocalDate visitDate1 = LocalDate.of(2023, 7, 11);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate1, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        sortDtoList.add(visitDto);
        sortDtoList.add(visitDto1);

        SortingAlgorithm1 sortingAlgorithm1 = new SortingAlgorithm1(sortDtoList);

        List<VisitDto> expected = new ArrayList<>();
        expected.add(visitDto1);
        expected.add(visitDto);

        assertEquals(expected, sortingAlgorithm1.sort(sortDtoList));
    }
}