package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SmsNotification implements Notification {

    private static int counter = 1;

    @Override
    public Boolean sendNotification(String name, String phoneNumber, String message) {
        String fileName = "Notifications/" + "SMS" + counter + ".txt";
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            FileWriter text = new FileWriter(file);
            text.write(message);
            text.write("\nFor more information contact: \n" + name + "\n" + phoneNumber);
            counter++;
            text.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
