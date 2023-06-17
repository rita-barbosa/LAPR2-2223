package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.CriteriaDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.domain.mapper.CriteriaMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OrderRequestController {

    /**
     * The Agency repository.
     */
    private AgencyRepository agencyRepository = null;
    /**
     * The Person repository.
     */
    private PersonRepository personRepository = null;
    /**
     * The Criteria repository.
     */
    private CriteriaRepository criteriaRepository = null;
    /**
     * The Authentication repository.
     */
    private AuthenticationRepository authenticationRepository = null;

    /**
     * The Property type repository.
     */
    private PropertyTypeRepository propertyTypeRepository = null;
    /**
     * The Business type repository.
     */
    private BusinessTypeRepository businessTypeRepository = null;

    /**
     * Instantiates a new Schedule visit controller.
     */
    public OrderRequestController() {
        getAgencyRepository();
        getPersonRepository();
        getCriteriaRepository();
        getAuthenticationRepository();
        getBusinessTypeRepository();
        getPropertyTypeRepository();
    }

    /**
     * Instantiates a new Order request controller.
     *
     * @param agencyRepository         the agency repository
     * @param personRepository         the person repository
     * @param criteriaRepository       the criteria repository
     * @param authenticationRepository the authentication repository
     */
    public OrderRequestController(AgencyRepository agencyRepository,
                                   PersonRepository personRepository,
                                   CriteriaRepository criteriaRepository,
                                   AuthenticationRepository authenticationRepository,
                                   PropertyTypeRepository propertyTypeRepository,
                                   BusinessTypeRepository businessTypeRepository) {
        this.agencyRepository = agencyRepository;
        this.personRepository = personRepository;
        this.criteriaRepository = criteriaRepository;
        this.authenticationRepository = authenticationRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
    }

    /**
     * Gets agency repository.
     *
     * @return the agency repository
     */
    private AgencyRepository getAgencyRepository() {
        if (agencyRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agencyRepository = repositories.getAgencyRepository();
        }
        return agencyRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Gets the property type repository.
     *
     * @return the property type repository
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**
     * Gets the list of property types.
     *
     * @return the property types
     */
    public Optional<List<CriteriaDto>> getPropertyTypeList() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return CriteriaMapper.toDto(propertyTypeRepository.getPropertyTypeDesignationsList());
    }

    /**
     * Gets the business type repository.
     *
     * @return the business type repository
     */
    private BusinessTypeRepository getBusinessTypeRepository() {
        if (businessTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }

    /**
     * Gets the list of business types.
     *
     * @return the business types
     */
    public Optional<List<CriteriaDto>> getBusinessTypeList() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return CriteriaMapper.toDto(businessTypeRepository.getBusinessTypeDesignationsList());
    }

    /**
     * Gets person repository.
     *
     * @return the person repository
     */
    private PersonRepository getPersonRepository() {
        if (personRepository == null) {
            Repositories repositories = Repositories.getInstance();
            personRepository = repositories.getPersonRepository();
        }
        return personRepository;
    }

    /**
     * Gets criteria repository.
     *
     * @return the criteria repository
     */
    private CriteriaRepository getCriteriaRepository() {
        if (criteriaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            criteriaRepository = repositories.getCriteriaRepository();
        }
        return criteriaRepository;
    }

    /**
     * Gets criteria list.
     *
     * @return the criteria list
     */
    public Optional<List<CriteriaDto>> getCriteriaList() {
        CriteriaRepository criteriaRepository = getCriteriaRepository();
        return CriteriaMapper.toDto(criteriaRepository.getCriteriaList());
    }


    /**
     * Gets user person.
     *
     * @return the user person
     */
    public Optional<Person> getUserPerson() {
        String userEmail = getAuthenticationRepository().getCurrentUserSession().getUserId().toString();
        return personRepository.getPersonByEmail(userEmail);
    }


    /**
     * Gets announcement list dto.
     *
     * @return the announcement list dto
     */
    public Optional<List<AnnouncementDto>> getAnnouncementListDto(List<Announcement> list) {
        return toDto(list);
    }

    /**
     * Get all announcements list.
     *
     * @return the list
     */
    public Optional<List<AnnouncementDto>> getAllNonDealAnnouncementsDto() {
        Agency agency = new Agency();
        Optional<List<Announcement>> list = agencyRepository.getAllNonDealAnnouncementsList();
        if (list.isPresent() && list.get().size() > 0){
            agency.getAnnouncements().setAnnouncements(list.get());
        }
        return AnnouncementMapper.toDto(agency.getAnnouncements().sortAnnouncementsByMostRecentAcceptanceDate());
    }

    /**
     * To model announcement.
     *
     * @param announcementDto the announcement dto
     * @return the announcement
     */
    public Optional<Announcement> toModel(AnnouncementDto announcementDto) {
        Optional<Announcement> newAnnouncement = Optional.empty();
        Optional<Agency> newAgency;

        newAgency = getAgencyRepository().getAgencyByAnnouncementId(announcementDto.getAnnouncementId());
        if (newAgency.isPresent()) {
            newAnnouncement = newAgency.get().getAnnouncementById(announcementDto.getAnnouncementId());
        }
        return newAnnouncement;
    }

    /**
     * To dto list.
     *
     * @param announcementList the announcement list
     * @return the list with announcement dtos
     */
    public Optional<List<AnnouncementDto>> toDto(List<Announcement> announcementList) {
        List<AnnouncementDto> listDto = new ArrayList<>();
        for (Announcement announcement : announcementList) {
            listDto.add(AnnouncementMapper.toDto(announcement));
        }
        return Optional.of(listDto);
    }

    /**
     * Get announcement list by business type.
     *
     * @param businessTypeDto the business type dto
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByBusinessType(CriteriaDto businessTypeDto, List<AnnouncementDto> list) {
        String businessType = CriteriaMapper.getDesignationFromDto(businessTypeDto);
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.announcementHasBusinessType(announcementList.getList(), businessType))).get();
    }

    /**
     * Get announcements list by property type.
     *
     * @param propertyTypeDto the property type dto
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByPropertyType(CriteriaDto propertyTypeDto, List<AnnouncementDto> list) {
        String propertyType = CriteriaMapper.getDesignationFromDto(propertyTypeDto);
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.announcementHasPropertyType(announcementList.getList(), propertyType))).get();
    }

    /**
     * Get announcements list by number bedrooms.
     *
     * @param numberBedrooms the number bedrooms
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByNumberBedrooms(Integer numberBedrooms, List<AnnouncementDto> list) {
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.announcementHasNumberBedrooms(announcementList.getList(), numberBedrooms))).get();
    }

    /**
     * Get announcements list by price.
     *
     * @param priceSorting the price sorting order
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByPrice(String priceSorting, List<AnnouncementDto> list) {
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        if (priceSorting.equals("Ascending")) {
            return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByAscendingPrice(announcementList.getList()))).get();
        }
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByDescendingPrice(announcementList.getList()))).get();
    }

    /**
     * Get announcements list by city.
     *
     * @param citySorting the city sorting order
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByCity(String citySorting, List<AnnouncementDto> list) {
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        if (citySorting.equals("Ascending")) {
            return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByAscendingCity(announcementList.getList()))).get();
        }
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByDescendingCity(announcementList.getList()))).get();
    }

    /**
     * Get announcements list by state.
     *
     * @param stateSorting the state sorting order
     * @return the announcement list
     */
    public List<AnnouncementDto> getAnnouncementsByState(String stateSorting, List<AnnouncementDto> list) {
        AnnouncementList announcementList = new AnnouncementList(AnnouncementMapper.toModelNonDeals(list, agencyRepository.getAgenciesList()));
        if (stateSorting.equals("Ascending")) {
            return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByAscendingState(announcementList.getList()))).get();
        }
        return AnnouncementMapper.toDto(new ArrayList<>(announcementList.sortAnnouncementsByDescendingState(announcementList.getList()))).get();
    }

    /**
     * Order Request result.
     *
     * @param announcement the announcement
     * @param orderAmount the start hour
     */
    public OrderRequestResult orderRequest(Announcement announcement, Double orderAmount) {
        Email userEmail = getAuthenticationRepository().getCurrentUserSession().getUserId();
        Stream<Order> userOrders = announcement.getListOfOrders().stream().filter(a -> a.getClientEmail().equals(userEmail));
        if (userOrders.anyMatch(a -> a.getAcceptanceAnswer().equals(Order.ACCEPTANCE_ANSWER_BY_DEFAULT))){
            return OrderRequestResult.PENDING_ORDER;
        }
        Stream<Order> orderPrice = announcement.getListOfOrders().stream().filter(a -> a.getOrderAmount() == orderAmount);
        if (orderPrice.findAny().isPresent()) {
            announcement.addOrder(new Order(orderAmount,userEmail));
            return OrderRequestResult.SAME_VALUE;
        }

        announcement.addOrder(new Order(orderAmount,userEmail));
        return OrderRequestResult.SUCCESS;

    }
}

