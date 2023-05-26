package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestMapper {


    public static Optional<List<RequestDto>> toDto(List<Request> requests){
        List<RequestDto> listRequestsDto = new ArrayList<>();
        for (Request request : requests) {
            RequestDto dto = toDto(request);
            listRequestsDto.add(dto);
        }
        return Optional.of(listRequestsDto);
    }

    public static RequestDto toDto(Request request){
        String propertyAttributes = request.getPropertyAttributes();
        String businessAttributes = request.getBusinessAttributes();
        int id = request.getId();
        String requestDate  = request.getRequestDate().toString();

        return new RequestDto(propertyAttributes, businessAttributes, id, requestDate);
    }


    public static Integer getRequestIdFromDto(Integer requestIdDto){
        return RequestDto.getRequestId();
    }
}
