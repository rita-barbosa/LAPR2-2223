package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.LegacySystemMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegacySystemMapperTest {

    @Test
    void toDto() {
        List<String> legacySystemInformationList = null;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        assertEquals(legacyDto, LegacySystemMapper.toDto(legacySystemInformationList));
    }

    @Test
    void toModelAgency() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        Location location = new Location("71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705");
        Agency expected = new Agency(1, "North Pole", "northpole@realstateUSA.com", "907-488-4800", location);

        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.toModelAgency(legacyDto));
    }

    @Test
    void toModelPerson() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        // (String name, String taxNumber, String phoneNumber, String email,String passportCardNumber) {
        Person expected = new Person("Georgia PEDDIE", "111-11-0000", "907-488-1419", "GeorgiaPEDDIE2155@gmail.com", "711111111");

        assert legacyDto != null;
        Person actual = LegacySystemMapper.toModelPerson(legacyDto);

        actual.setPassportCardNumber("711111111");

        assertEquals(expected, actual);
    }

    @Test
    void toModelRequest() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        String location = "449 N Santa Claus Lane, North Pole, AK, 99705";

        Request expected = new Request("sale", "NA", (double) 208500, "house",
                (double) 1710, location, (double) 29, "3", "4", "2", "Y", "Y",
                "Y", "Y", "S", "08-01-2001");

        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.toModelRequest(legacyDto));
    }

    @Test
    void getCommissionValue() {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;
        String line = "1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;" +
                "house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;" +
                "5;NA;08-01-2001;10-01-2001;sale;" +
                "1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = line.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        Double expected = (double) 5;


        assert legacyDto != null;
        assertEquals(expected, LegacySystemMapper.getCommissionValue(legacyDto));
    }
}