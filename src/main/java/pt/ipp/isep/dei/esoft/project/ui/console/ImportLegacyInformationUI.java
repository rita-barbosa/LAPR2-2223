package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportLegacyInformationController;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ImportLegacyInformationUI implements Runnable {

    String filepath;

    private static final String LEGACY_FILE_EXTENSION = "csv";
    private static final Integer MIN_FILE_NAME_PARTS = 2;

    ImportLegacyInformationController controller;

    private ImportLegacyInformationController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\nImport information from legacy system file");

//* **AC1:** The system administrator must be able to choose a file to import.
//* **AC2:** The system should only accept CSV files.
//* **AC3:** The file content must be validated, showing a message to the system administrator if the file is empty or its content is not in the requested format.
//* **AC4:** The import operation, when successful, should trigger a success message to the system administrator.

        filepath = requestFilepath();

        submitData();
    }

    private void submitData() {
        if (getController().importInformationFromFile(filepath)) {
            System.out.println("\nLegacy system information successfully imported!");
        } else {
            System.out.println("\nThe legacy system information was not imported!");
        }
    }

    private String requestFilepath() {
        Scanner read = new Scanner(System.in);
        String filepath;
        String fileName;
        File file;
        do {
            System.out.println("\nPlease provide the filepath for the legacy system information file.");
            filepath = read.nextLine();
            String[] filePathArray = filepath.split("\\\\");
            fileName = filePathArray[filePathArray.length - 1];
            file = new File(filepath);
        } while (!doesFileExist(file) || !validateFileName(file, fileName));
        return filepath;
    }
    
    private Boolean doesFileExist(File file){
        return file.exists();
    }

    private Boolean validateFileName(File file, String fileName) {
        if (doesFileExist(file)) {
            String[] fileNameArray = fileName.split("\\.");
            try {
                if (!fileNameArray[fileNameArray.length - 1].equals(LEGACY_FILE_EXTENSION) && fileNameArray.length < MIN_FILE_NAME_PARTS) {
                    System.out.println("ERROR: The file does not have the required extension (csv).");
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("ERROR: The file does not have an extension!\n Please provide a filepath where the file has the required extension (csv).");
                return false;
            }
        }else {
            System.out.printf("ERROR: The file does not exist.%n");
            return false;
        }
        return true;
    }

}
