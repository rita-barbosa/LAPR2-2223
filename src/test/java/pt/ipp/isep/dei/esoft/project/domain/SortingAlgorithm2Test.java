package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortingAlgorithm2Test {

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

        SortingAlgorithm2 sortingAlgorithm2 = new SortingAlgorithm2(sortDtoList);

        List<VisitDto> expected = sortDtoList;

        assertEquals(expected, sortingAlgorithm2.getSortDtoList());
    }

    @Test
    void sort() {
        List<VisitDto> sortDtoList = new ArrayList<>();

        LocalDate visitDate = LocalDate.of(2023, 6, 10);
        LocalDate visitDate1 = LocalDate.of(2023, 7, 11);
        LocalDate visitDate2 = LocalDate.of(2023, 5, 12);
        LocalDate visitDate3 = LocalDate.of(2023, 12, 20);
        LocalDate visitDate4 = LocalDate.of(2023, 9, 5);
        LocalDate visitDate5 = LocalDate.of(2023, 11, 7);
        Integer startHour = 10;
        Integer endHour = 12;
        String userName = "someone";
        String userPhoneNumber = "12345678";
        Integer id = 0;
        Boolean acceptanceStatus = false;

        VisitDto visitDto = new VisitDto(visitDate1, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto1 = new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto2 = new VisitDto(visitDate4, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto3 = new VisitDto(visitDate3, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto4 = new VisitDto(visitDate2, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        VisitDto visitDto5 = new VisitDto(visitDate5, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
        sortDtoList.add(visitDto);
        sortDtoList.add(visitDto1);
        sortDtoList.add(visitDto2);
        sortDtoList.add(visitDto3);
        sortDtoList.add(visitDto4);
        sortDtoList.add(visitDto5);

        SortingAlgorithm2 sortingAlgorithm2 = new SortingAlgorithm2(sortDtoList);

        List<VisitDto> expected = new ArrayList<>();
        expected.add(visitDto4);
        expected.add(visitDto1);
        expected.add(visitDto);
        expected.add(visitDto2);
        expected.add(visitDto5);
        expected.add(visitDto3);

        assertEquals(expected, sortingAlgorithm2.sort(sortDtoList));
    }
}