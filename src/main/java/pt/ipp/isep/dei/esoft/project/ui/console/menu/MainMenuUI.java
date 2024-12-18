package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementList;
import pt.ipp.isep.dei.esoft.project.domain.Serialization;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayPropertiesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterUserUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The MainMenuUI class provides a command-line user interface for the main menu of an application.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    /**
     * Constructs a MainMenuUI object.
     */
    public MainMenuUI() {
    }

    /**
     * Runs the main menu user interface.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Sign up", new RegisterUserUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Display Listed Properties", new DisplayPropertiesUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
        try {
            Serialization.saveData();
        } catch (Exception e){
            System.out.println("ERROR: Couldn't save data.");
        }
    }
}