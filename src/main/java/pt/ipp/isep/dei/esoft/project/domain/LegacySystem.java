package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.domain.dto.LegacySystemDto;
import pt.ipp.isep.dei.esoft.project.domain.mapper.LegacySystemMapper;

import java.io.*;
import java.util.*;

public class LegacySystem {

    public static Optional<List<LegacySystemDto>> importInformation(String filePath) throws IOException {
        List<LegacySystemDto> legacySystemInformationDtoList = new ArrayList<>();

        File file = new File(filePath);
        if (!verifyFile(file)) {
            return Optional.of(legacySystemInformationDtoList);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                if (attributes.length > 1) {
                    List<String> legacySystemInformationList = new ArrayList<>();
                    for (int i = 1; i < attributes.length; i++) {
                        legacySystemInformationList.add(attributes[i].trim());
                    }
                    LegacySystemDto legacySystemDto = LegacySystemMapper.toDto(legacySystemInformationList);
                    legacySystemInformationDtoList.add(legacySystemDto);
                }
            }
            return Optional.of(legacySystemInformationDtoList);
        } catch (IOException e) {
            throw new IOException("ERROR: Couldn't read information from file.");
        }
    }

    private static boolean verifyFile(File file) {
        if (!fileExists(file)) {
            System.out.println("ERROR: File does not exist.");
            return false;
        }
        if (!verifyFileExtension(file.getName())) {
            System.out.println("ERROR: Invalid file extension.");
            return false;
        }
        return true;
    }

    private static boolean fileExists(File file) {
        return file.exists();
    }

    private static boolean verifyFileExtension(String fileName) {
        return fileName.toLowerCase().endsWith(".csv");
    }
}


