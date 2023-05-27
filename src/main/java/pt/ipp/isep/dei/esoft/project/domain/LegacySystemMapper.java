package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Optional;

public class LegacySystemMapper {
    public static LegacySystemDto toDto(List<String> legacySystemInformationList) {
        return new LegacySystemDto(legacySystemInformationList);
    }

    public static Agency toModelAgency(LegacySystemDto dto) {
        int agencyId = dto.getAgencyID();
        String agencyName = dto.getAgencyName();
        String agencyLocation = dto.getAgencyLocation();
        String agencyPhoneNumber = dto.getAgencyPhoneNumber();
        String agencyEmailAddress = dto.getAgencyEmailAddress();

        return new Agency(agencyId, agencyName, agencyEmailAddress, agencyPhoneNumber, agencyLocation);

    }

    public static Person toModelPerson(LegacySystemDto dto) {
        String ownerName = dto.getOwnerName();
        String ownerTIN = dto.getOwnerTIN();
        String ownerPhoneNumber = dto.getOwnerPhone();
        String ownerEmail = dto.getOwnerEmail();

        return new Person(ownerName, ownerTIN, ownerPhoneNumber, ownerEmail);
    }
}
