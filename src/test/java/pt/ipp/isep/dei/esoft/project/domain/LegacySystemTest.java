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

        File file = new File("importInformationTestFile.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
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
}