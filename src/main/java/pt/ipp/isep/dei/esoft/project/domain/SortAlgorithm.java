package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.util.List;

public interface SortAlgorithm {

    List<VisitDto> sort(List<?> list) ;
}
