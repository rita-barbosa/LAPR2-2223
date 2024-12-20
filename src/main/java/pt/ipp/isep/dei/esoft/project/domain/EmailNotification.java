package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EmailNotification implements Notification {

    static Integer counter = 1;

    @Override
    public Boolean sendNotification(String to, String topic, String message) {
        String fileName = "Notifications/" + "EMAIL." + counter + FILE_TYPE;
        File file = new File(fileName);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            PrintWriter text = new PrintWriter(file);
            text.write(TEXT_TO + to + "\n");
            text.write(TEXT_TOPIC + topic + "\n");
            text.write(message);
            text.close();
            counter++;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
