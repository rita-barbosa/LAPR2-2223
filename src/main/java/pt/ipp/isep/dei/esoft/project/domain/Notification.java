package pt.ipp.isep.dei.esoft.project.domain;

public interface Notification {
    final String TEXT_TO = "To: ";
    final String TEXT_TOPIC = "Topic: ";
    final String FILE_NAME = "notification.";
    final String FILE_TYPE = ".txt";

    Boolean sendNotification(String email);

}
