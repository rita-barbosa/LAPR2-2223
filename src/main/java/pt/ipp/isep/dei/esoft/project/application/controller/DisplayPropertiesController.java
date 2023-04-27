package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.CriteriaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class DisplayPropertiesController {

    //public Announcement announcementList;
    
    private AgencyRepository agencyRepository = null;
    private CriteriaRepository criteriaRepository = null;

    public DisplayPropertiesController(){
        getAgencyRepository();
        getAgenciesList();
        List<Announcement> announcementList = getAnnouncementsList();
        sortAnnouncementsByMostRecentAdded(announcementList);
        getCriteriaRepository();


    }

    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }


    public List<Agency> getAgenciesList() {
        AgencyRepository agencyRepository = getAgencyRepository();
        return agencyRepository.getAgenciesList();
    }

    public List<Announcement> getAnnouncementsList(){
        List<Announcement> announcementList = new ArrayList<>();
        List<Agency> agencies = getAgenciesList();

        for (Agency agency: getAgencyRepository().getAgenciesList()) {
            announcementList.addAll(agencies.getAnnouncementList()); //check it again
        }

        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByMostRecentAdded(List<Announcement> announcementList){
        return announcementList; //check it again
    }

    private CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }

}
