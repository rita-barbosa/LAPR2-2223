package pt.ipp.isep.dei.esoft.project.domain;

import java.time.LocalDate;
import java.util.*;

public class RequestList {

    private List<Request> requests;

    public RequestList(){
        this.requests = new ArrayList<>();
    }

    public List<Request> getList(){
        return requests;
    }

    /**
     * This method adds a request to the existent list of announcements.
     *
     * @param request - request intended to be added.
     * @return {@code true} if the request was successfully added; {@code false} otherwise;
     */
    public Boolean addRequest(Request request) {
        Boolean success = false;
        if (validateRequest(request)) {
            success = requests.add(request.clone());
        }
        return success;
    }

    /**
     * This method checks if the list of requests already contains the request received.
     *
     * @param request - request intended to be checked.
     * @return {@code true} if the request isn't in the list of requests; {@code false} otherwise;
     */
    private Boolean validateRequest(Request request) {
        return requests != null && !(requests.contains(request));
    }

    public List<Request> getRequestsByAgentEmail(String agentEmail) {
        List<Request> listRequests = new ArrayList<>();

        for (Request a : requests) {
            if (a.hasAgentWithEmail(agentEmail)) {
                listRequests.add(a.clone());
            }
        }
        sortRequestsByMostRecentAdded(listRequests);
        return listRequests;
    }

    private void sortRequestsByMostRecentAdded(List<Request> listRequests){
        Collections.sort(listRequests, requestDate);
    }

    Comparator<Request> requestDate = new Comparator<Request>() {
        public int compare(Request a1, Request a2) {
            LocalDate a1RequestDate = a1.getRequestDate();
            LocalDate a2RequestDate = a2.getRequestDate();

            return a1RequestDate.compareTo(a2RequestDate);
        }
    };

    public RequestList clone() {
        RequestList clone = new RequestList();
        if (!(requests.isEmpty())) {
            for (Request in :
                    this.requests) {
                clone.requests.add(in.clone());
            }
        }
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestList that = (RequestList) o;
        return Objects.equals(requests, that.requests) && Objects.equals(requestDate, that.requestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requests, requestDate);
    }

    public Optional<Request> getRequestById(Integer requestId) {
        for (Request a : requests) {
            if (a.hasId(requestId)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public Boolean anyRequestHasId(Integer requestId) {
        for (Request a : requests) {
            if (a.hasId(requestId)) {
                return true;
            }
        }
        return false;
    }


}
