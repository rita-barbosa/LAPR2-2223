package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Visit;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Visit mapper.
 */
public class VisitMapper {

    /**
     * Converts a list of Visit objects to a list of VisitDto objects.
     *
     * @param visitsList the list of Visit objects
     * @return the list of VisitDto objects
     */
    public static Optional<List<VisitDto>> toDto(List<Visit> visitsList) {
        List<VisitDto> visitsDtoList = new ArrayList<>();
        for (Visit visit : visitsList) {
            VisitDto dto = toDto(visit);
            visitsDtoList.add(dto);
        }
        return Optional.of(visitsDtoList);
    }

    /**
     * Converts a Visit object to a VisitDto object.
     *
     * @param visit the Visit object
     * @return the VisitDto object
     */
    public static VisitDto toDto(Visit visit) {
        Integer id = visit.getId();
        Integer startHour = visit.getStartHour();
        Integer endHour = visit.getEndHour();
        LocalDate visitDate = visit.getVisitDate();
        String userName = visit.getUserName();
        String userPhoneNumber = visit.getUserPhoneNumber();
        Boolean acceptanceStatus = visit.getAcceptanceStatus();

        return new VisitDto(visitDate, startHour, endHour, userName, userPhoneNumber, id, acceptanceStatus);
    }
}
