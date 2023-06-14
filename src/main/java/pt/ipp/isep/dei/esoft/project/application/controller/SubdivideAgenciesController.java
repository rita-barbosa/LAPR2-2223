package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Partition;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class SubdivideAgenciesController {

    private AgencyRepository agencyRepository;


    /**
     * Instantiates a new Subdivide agencies controller.
     */
    public SubdivideAgenciesController() {
        getAgencyRepository();
    }

    /**
     * Instantiates a new Subdivide agencies controller.
     *
     * @param agencyRepository         the agency repository
     */
    public SubdivideAgenciesController(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    public List<String> getAgenciesPartitions(){
        Partition p = new Partition(agencyRepository.getDealsNumberOfAgencies());
        return p.getSubLists();
    }









}
