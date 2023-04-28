package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.CriteriaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayPropertiesController {

    //public Announcement announcementList;
    private List<Announcement> announcementList = null;
    private AgencyRepository agencyRepository = null;
    private CriteriaRepository criteriaRepository = null;

    public DisplayPropertiesController(){
        getAgencyRepository();
        getAgenciesList();
        announcementList = getAnnouncementsList();
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
        //sortAnnouncementsByMostRecentAdded(clonedAnnouncementList);
        sortAnnouncementsByMostRecentAdded();
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
            for (Agency agency: agencies) {
            announcementList.addAll(agency.getAnnouncements());
        }
        return announcementList;
    }

    public List<Announcement> sortAnnouncementsByMostRecentAdded(){
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);

        Comparator<Announcement> acceptanceDate = new Comparator<Announcement>() {
            public int compare(Announcement a1, Announcement a2){
                LocalDate a1AcceptanceDate = a1.getAcceptanceDate();
                LocalDate a2AcceptanceDate = a2.getAcceptanceDate();

                return a1AcceptanceDate.compareTo(a2AcceptanceDate);
            }
        };

        Collections.sort(clonedAnnouncementList, Collections.reverseOrder(acceptanceDate));
        return clonedAnnouncementList;

//        Collections.sort(clonedAnnouncementList, Collections.reverseOrder(new Comparator<Announcement>() {
//            @Override
//            public int compare(Announcement o1, Announcement o2) {
//                LocalDate o1AcceptanceDate = o1.getAcceptanceDate();
//                LocalDate o2AcceptanceDate = o2.getAcceptanceDate();
//
//                return o1AcceptanceDate.compareTo(o2AcceptanceDate);
//            }
//        }));
//        return clonedAnnouncementList;
    }

    public CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }

}
