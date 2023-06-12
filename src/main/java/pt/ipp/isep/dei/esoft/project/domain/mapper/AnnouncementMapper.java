package pt.ipp.isep.dei.esoft.project.domain.mapper;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.OrderDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Announcement mapper.
 */
public class AnnouncementMapper {
    /**
     * To dto optional.
     *
     * @param announcements the announcements
     * @return the optional
     */
    public static Optional<List<AnnouncementDto>> toDto(List<Announcement> announcements) {
        List<AnnouncementDto> listAnnouncementsDto = new ArrayList<>();
        for (Announcement announce : announcements) {
            AnnouncementDto dto = toDto(announce);
            listAnnouncementsDto.add(dto);
        }
        return Optional.of(listAnnouncementsDto);
    }

    /**
     * To dto announcement dto.
     *
     * @param announcement the announcement
     * @return the announcement dto
     */
    public static AnnouncementDto toDto(Announcement announcement) {
        String requestAttributes = announcement.getRequestAttributes();
        String commissionAttributes = announcement.getCommissionAttributes();
        int id = announcement.getId();
        String acceptanceDate = announcement.getAcceptanceDate().toString();
        List<Order> listOrders = announcement.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        return new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);
    }

    public static Optional<List<AnnouncementDto>> toNetworkDto(List<Announcement> announcements) {
        List<AnnouncementDto> listAnnouncementsDto = new ArrayList<>();
        for (Announcement announce : announcements) {
            AnnouncementDto dto = toNetworkDto(announce);
            listAnnouncementsDto.add(dto);
        }
        return Optional.of(listAnnouncementsDto);
    }

    public static AnnouncementDto toNetworkDto(Announcement announcement) {
        int id = announcement.getId();
        String saleDate = announcement.getSaleDate().toString();
        double saleAmount = announcement.getSaleAmount();
        String requestDate = announcement.getRequest().getRequestDate().toString();
        String acceptanceDate = announcement.getAcceptanceDate().toString();
        String commissionAttributes = announcement.getCommissionAttributes();
        String requestAttributes = announcement.getRequestAttributes();
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        Optional<Agency> agency = agencyRepository.getAgencyByEmployeeEmail(announcement.getAgentEmail());
        String agencyDescription = "";
        int agencyId = 0;
        if (agency.isPresent()) {
            agencyId = agency.get().getId();
            agencyDescription = agency.get().getDescription();
        }
        return new AnnouncementDto(id, saleDate, saleAmount, requestDate, acceptanceDate, commissionAttributes,
                requestAttributes, agencyDescription, agencyId);
    }

    public static Request toModelSaleRequest(AnnouncementDto announcementDto, PropertyType propertyType, Employee agent) {
        String ownerEmail = announcementDto.getOwnerEmail();
        Double amount = announcementDto.getAmount();
        Double area = announcementDto.getArea();
        String streetName = announcementDto.getStreetName();
        String city = announcementDto.getCity();
        String district = announcementDto.getDistrict();
        String state = announcementDto.getState();
        String zipcode = announcementDto.getZipCode();
        Double distanceCityCenter = announcementDto.getDistanceCityCenter();
        List<String> uriList = announcementDto.getUriList();
        if (propertyType.getDesignation().equalsIgnoreCase("land")) {
            return new Request(ownerEmail, propertyType, amount, area, streetName, city, district, state, zipcode, uriList, distanceCityCenter, agent);
        } else if (propertyType.getDesignation().equalsIgnoreCase("apartment") || propertyType.getDesignation().equalsIgnoreCase("house")) {
            Integer numberBedrooms = announcementDto.getNumberBedroom();
            Integer numberBathrooms = announcementDto.getNumberBathroom();
            Integer numberParkingSpaces = announcementDto.getNumberParkingSpace();
            List<String> availableEquipmentDescription = announcementDto.getAvailableEquipmentDescriptionList();
            if (propertyType.getDesignation().equalsIgnoreCase("apartment")) {
                return new Request(ownerEmail, propertyType, amount, area, streetName, city, district, state, zipcode, uriList, distanceCityCenter, agent, numberBathrooms, numberBedrooms, numberParkingSpaces, availableEquipmentDescription);
            }
            if (propertyType.getDesignation().equalsIgnoreCase("house")) {
                Boolean existenceBasement = announcementDto.isExistenceBasement();
                Boolean inhabitableLoft = announcementDto.isInhabitableLoft();
                String sunExposure = announcementDto.getSunExposure();
                return new Request(ownerEmail, propertyType, amount, area, streetName, city, district, state, zipcode, uriList, distanceCityCenter, agent, numberBathrooms, numberBedrooms, numberParkingSpaces, availableEquipmentDescription, existenceBasement, inhabitableLoft, sunExposure);
            }
        }
        return null;
    }
}
