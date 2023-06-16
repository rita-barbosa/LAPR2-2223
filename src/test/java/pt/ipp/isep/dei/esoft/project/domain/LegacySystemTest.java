package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegacySystemTest {

    @Test
    void importInformation() throws IOException {
        List<String> legacySystemInformationList;
        LegacySystemDto legacyDto = null;

        String r ="1;Georgia PEDDIE;711111111;111-11-0000;GeorgiaPEDDIE2155@gmail.com;907-488-1419;house;1710;449 N Santa Claus Lane, North Pole, AK, 99705;29;3;4;2;Y;Y;Y;Y;S;208500;203748;5;NA;08-01-2001;10-01-2001;sale;1;North Pole;71 ST. NICHOLAS DRIVE, NORTH POLE, FAIRBANKS NORTH STAR, AK, 99705;907-488-4800;northpole@realstateUSA.com";
        String[] attributes = r.split(";");
        if (attributes.length > 1) {
            legacySystemInformationList = new ArrayList<>();
            for (int i = 1; i < attributes.length; i++) {
                legacySystemInformationList.add(attributes[i].trim());
            }
            legacyDto = new LegacySystemDto(legacySystemInformationList);
        }

        assertNotNull(legacyDto);
    }
}