package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.dto.CriteriaDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Criteria mapper.
 */
public class CriteriaMapper {
    /**
     * To dto optional.
     *
     * @param criterias the criterias
     * @return the optional
     */
    public static Optional<List<CriteriaDto>> toDto(List<String> criterias) {
        List<CriteriaDto> criteriaDtoList = new ArrayList<>();
        for (String criterion : criterias) {
            CriteriaDto dto = toDto(criterion);
            criteriaDtoList.add(dto);
        }
        return Optional.of(criteriaDtoList);
    }

    /**
     * To dto criteria dto.
     *
     * @param criterion the criterion
     * @return the criteria dto
     */
    public static CriteriaDto toDto(String criterion) {
        return new CriteriaDto(criterion);
    }

    public static String getDesignationFromDto(CriteriaDto dto) {
        return dto.getDesignation();
    }
}
