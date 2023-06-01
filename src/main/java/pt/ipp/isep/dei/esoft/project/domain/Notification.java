package pt.ipp.isep.dei.esoft.project.domain;

import java.io.IOException;

public interface Notification {
    String TEXT_TO = "To: ";
    String TEXT_TOPIC = "Topic: ";
    String FILE_NAME = "notification.";
    String FILE_TYPE = ".txt";

    Boolean sendNotification(String to, String from, String message);

}
