package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Request mapper.
 */
public class RequestMapper {


    /**
     * To dto optional.
     *
     * @param requests the requests
     * @return the optional
     */
    public static Optional<List<RequestDto>> toDto(List<Request> requests){
        List<RequestDto> listRequestsDto = new ArrayList<>();
        for (Request request : requests) {
            RequestDto dto = toDto(request);
            listRequestsDto.add(dto);
        }
        return Optional.of(listRequestsDto);
    }

    /**
     * To dto request dto.
     *
     * @param request the request
     * @return the request dto
     */
    public static RequestDto toDto(Request request){
        String propertyAttributes = request.getPropertyAttributes();
        String businessAttributes = request.getBusinessAttributes();
        int id = request.getId();
        String requestDate  = request.getRequestDate().toString();

        return new RequestDto(propertyAttributes, businessAttributes, id, requestDate);
    }


    /**
     * Get request id from dto integer.
     *
     * @param requestIdDto the request id dto
     * @return the integer
     */
    public static Integer getRequestIdFromDto(Integer requestIdDto){
        return RequestDto.getRequestId();
    }
}
