package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;

public class ListDealsNetworkController {
    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository = null;

    /**
     * Instantiates a new ListDealsNetworkController.
     */
    public ListDealsNetworkController() {
        getAgencyRepository();
    }

    /**
     * Instantiates a new ListDealsNetworkController.
     *
     * @param agencyRepository the agency repository
     */
    public ListDealsNetworkController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    /**
     * Gets agency repository.
     *
     * @return the agency repository
     */
    public AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public List<Announcement> getAllDealsList() {
        return agencyRepository.getAllDealsAnnouncements();
    }

    public Optional<List<AnnouncementDto>> toDto(List<Announcement> allDealsList) {

        return AnnouncementMapper.toNetworkDto(allDealsList, agencyRepository.getAgenciesList());
    }

    public Optional<List<AnnouncementDto>> getListSortedByAlgorithm(String sortingOrder, String algorithm) {
        return getSortedList(sortingOrder, algorithm, getAllDealsList());
    }

    private Optional<List<AnnouncementDto>> getSortedList(String sortingOrder, String algorithm, List<Announcement> listOfAllDeals) {
        if (algorithm.equalsIgnoreCase("Merge Sort")) {
            MergeAlgorithm sortedList = new MergeAlgorithm(listOfAllDeals);
            sortedList.sort(sortingOrder, getAllDealsList());
            return toDto(sortedList.getSortedList());
        } else {
            BubbleAlgorithm sortedList = new BubbleAlgorithm(listOfAllDeals);
            sortedList.sort(sortingOrder, getAllDealsList());
            return toDto(sortedList.getSortedList());
        }
    }
}
