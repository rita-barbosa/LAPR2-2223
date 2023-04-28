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

    private List<Announcement> announcementList;
    private AgencyRepository agencyRepository = null;
    private CriteriaRepository criteriaRepository = null;

    public DisplayPropertiesController(){
        getAgencyRepository();
        getAgenciesList();
        announcementList = getAnnouncementsList();
        List<Announcement> clonedAnnouncementList = new ArrayList<>(announcementList);
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
            announcementList.addAll(agency.getAnnouncementsList());
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

    }

    public CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }


    public List<Announcement> getAnnouncementsByBusinessType(List<Announcement> announcementList, String businessType){
        Agency agency = new Agency();
        announcementList = agency.announcementHasBusinessType(announcementList, businessType);

        return announcementList;
    }

    public List<Announcement> getAnnouncementsByPropertyType(List<Announcement> announcementList, String propertyType){
        Agency agency = new Agency();
        announcementList = agency.announcementHasPropertyType(announcementList, propertyType);

        return announcementList;
    }

    public List<Announcement> getAnnouncementsByNumberBedrooms(List<Announcement> announcementList, Integer numberBedrooms){
        Agency agency = new Agency();
        announcementList = agency.announcementHasNumberBedrooms(announcementList, numberBedrooms);

        return announcementList;
    }

    public List<Announcement> getAnnouncementsByPrice(List<Announcement> announcementList, String priceSorting){
        if (priceSorting.equals("Ascending")){
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingPrice(announcementList);
            return announcementList;
        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDescendingPrice(announcementList);
            return announcementList;
        }
    }

    public List<Announcement> getAnnouncementsByCity(List<Announcement> announcementList, String citySorting){
        if (citySorting.equals("Ascending")){
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingCity(announcementList);
            return announcementList;
        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDescendingCity(announcementList);
            return announcementList;

        }
    }

    public List<Announcement> getAnnouncementsByState(List<Announcement> announcementList, String stateSorting){
        if (stateSorting.equals("Ascending")){
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByAscendingState(announcementList);
            return announcementList;

        } else {
            Agency agency = new Agency();
            announcementList = agency.sortAnnouncementsByDesscendingState(announcementList);
            return announcementList;
        }

        }
    }
