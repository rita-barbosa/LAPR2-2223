package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Request list.
 */
public class RequestList implements Serializable {

    /**
     * The Requests.
     */
    private List<Request> requests;

    /**
     * Instantiates a new Request list.
     */
    public RequestList(){
        this.requests = new ArrayList<>();
    }

    /**
     * Get the list.
     *
     * @return the list
     */
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

    /**
     * Gets requests by agent email.
     *
     * @param agentEmail the agent email
     * @return the requests by agent email
     */
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

    /**
     * Sort requests by most recent added.
     *
     * @param listRequests the list requests
     */
    private void sortRequestsByMostRecentAdded(List<Request> listRequests){
        Collections.sort(listRequests, requestDate);
    }

    /**
     * The Request date.
     */
    Comparator<Request> requestDate = new Comparator<Request>() {
        public int compare(Request a1, Request a2) {
            LocalDate a1RequestDate = a1.getRequestDate();
            LocalDate a2RequestDate = a2.getRequestDate();

            return a1RequestDate.compareTo(a2RequestDate);
        }
    };

    /**
     * This method creates a new copy of an requestList object.
     *
     * @return a copy of the requestList object.
     */
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

    /**
     * This method compares this RequestList object to the specified object.
     *
     * @param o - the object to compare to.
     * @return {@code true} if the objects are equal,{@code false} otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestList that = (RequestList) o;
        return Objects.equals(requests, that.requests);
    }

    /**
     * Generates a hash code for this RequestList object.
     *
     * @return The hash code of the RequestList object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requests);
    }

    /**
     * Gets request by id.
     *
     * @param requestId the request id
     * @return the request by id
     */
    public Optional<Request> getRequestById(Integer requestId) {
        for (Request a : requests) {
            if (a.hasId(requestId)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    /**
     * Any request has id boolean.
     *
     * @param requestId the request id
     * @return the boolean
     */
    public Boolean anyRequestHasId(Integer requestId) {
        for (Request a : requests) {
            if (a.hasId(requestId)) {
                return true;
            }
        }
        return false;
    }


}
