package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegacySystemDtoTest {

    @Test
    void ensureLegacySystemDtoIsNotNull() {
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

        assertNotNull(legacyDto);
    }

    @Test
    void ensureLegacySystemDtoIsNotOtherObject() {
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

        assertNotEquals(legacyDto, new Object());
    }

    @Test
    void getOwnerName() {
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

        assertNotNull(legacyDto);
        assertEquals("Georgia PEDDIE", legacyDto.getOwnerName());
    }

    @Test
    void getOwnerPassportNum() {
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

        assertNotNull(legacyDto);
        assertEquals("711111111", legacyDto.getOwnerPassportNum());
    }

    @Test
    void getOwnerTIN() {
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

        assertNotNull(legacyDto);
        assertEquals("111-11-0000", legacyDto.getOwnerTIN());
    }

    @Test
    void getOwnerEmail() {
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

        assertNotNull(legacyDto);
        assertEquals("GeorgiaPEDDIE2155@gmail.com", legacyDto.getOwnerEmail());
    }

    @Test
    void getOwnerPhone() {
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

        assertNotNull(legacyDto);
        assertEquals("907-488-1419", legacyDto.getOwnerPhone());
    }

    @Test
    void getPropertyType() {
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

        assertNotNull(legacyDto);
        assertEquals("house", legacyDto.getPropertyType());
    }

    @Test
    void getPropertyArea() {
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

        assertNotNull(legacyDto);
        assertEquals(1710, legacyDto.getPropertyArea());
    }

    @Test
    void getPropertyLocation() {
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

        assertNotNull(legacyDto);
        assertEquals("449 N Santa Claus Lane, North Pole, AK, 99705", legacyDto.getPropertyLocation());
    }


    @Test
    void getPropertyDistanceFromCenter() {
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

        assertNotNull(legacyDto);
        assertEquals(29, legacyDto.getPropertyDistanceFromCenter());
    }

    @Test
    void getPropertyNumberBedrooms() {
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

        assertNotNull(legacyDto);
        assertEquals("3", legacyDto.getPropertyNumberBedrooms());
    }

    @Test
    void getPropertyNumberBathrooms() {
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

        assertNotNull(legacyDto);
        assertEquals("4", legacyDto.getPropertyNumberBathrooms());
    }

    @Test
    void getPropertyNumberParkingSpaces() {
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

        assertNotNull(legacyDto);
        assertEquals("2", legacyDto.getPropertyNumberParkingSpaces());
    }

    @Test
    void getPropertyCentralHeating() {
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

        assertNotNull(legacyDto);
        assertEquals("Y", legacyDto.getPropertyCentralHeating());
    }

    @Test
    void getPropertyAirConditioning() {
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

        assertNotNull(legacyDto);
        assertEquals("Y", legacyDto.getPropertyAirConditioning());
    }

    @Test
    void getPropertyBasement() {
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

        assertNotNull(legacyDto);
        assertEquals("Y", legacyDto.getPropertyBasement());
    }

    @Test
    void getPropertyLoft() {
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

        assertNotNull(legacyDto);
        assertEquals("Y", legacyDto.getPropertyLoft());
    }

    @Test
    void getPropertySunExposure() {
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

        assertNotNull(legacyDto);
        assertEquals("S", legacyDto.getPropertySunExposure());
    }

    @Test
    void getPropertyRequestedPrice() {
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

        assertNotNull(legacyDto);
        assertEquals(208500, legacyDto.getPropertyRequestedPrice());
    }

    @Test
    void getPropertyPrice() {
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

        assertNotNull(legacyDto);
        assertEquals(203748, legacyDto.getPropertyPrice());
    }

    @Test
    void getCommission() {
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

        assertNotNull(legacyDto);
        assertEquals(5, legacyDto.getCommission());
    }

    @Test
    void getContractDuration() {
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

        assertNotNull(legacyDto);
        assertEquals("NA", legacyDto.getContractDuration());
    }

    @Test
    void getPropertyDateAnnounceRequest() {
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

        assertNotNull(legacyDto);
        assertEquals("08-01-2001", legacyDto.getPropertyDateAnnounceRequest());
    }

    @Test
    void getPropertyDateSale() {
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

        assertNotNull(legacyDto);
        assertEquals("10-01-2001", legacyDto.getPropertyDateSale());
    }

    @Test
    void getTypeBusiness() {
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

        assertNotNull(legacyDto);
        assertEquals("sale", legacyDto.getTypeBusiness());
    }

    @Test
    void getAgencyID() {
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

        assertNotNull(legacyDto);
        assertEquals(1, legacyDto.getAgencyID());
    }

    @Test
    void getAgencyName() {
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

        assertNotNull(legacyDto);
        assertEquals("North Pole", legacyDto.getAgencyName());
    }

    @Test
    void getAgencyLocation() {
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

        assertNotNull(legacyDto);
        assertEquals("71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705", legacyDto.getAgencyLocation());
    }

    @Test
    void getAgencyPhoneNumber() {
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

        assertNotNull(legacyDto);
        assertEquals("907-488-4800", legacyDto.getAgencyPhoneNumber());
    }

    @Test
    void getAgencyEmailAddress() {
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

        assertNotNull(legacyDto);
        assertEquals("northpole@realstateUSA.com", legacyDto.getAgencyEmailAddress());
    }
}