package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.gui.ListVisitsApp;
import javafx.application.Application;
import pt.ipp.isep.dei.esoft.project.ui.console.AcceptOrdersUI;
import pt.ipp.isep.dei.esoft.project.ui.console.AcceptRequestsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AgentUI implements Runnable {
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Publish a sale announcement", new PublishAnnouncementUI()));
        options.add(new MenuItem("Accept Orders", new AcceptOrdersUI()));
        options.add(new MenuItem("Accept property announcement requests", new AcceptRequestsUI()));
        options.add(new MenuItem("List booking requests", new ListVisitsApp()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                if (options.get(option).getGui() == null) {
                    options.get(option).run();
                } else if (options.get(option).getUi() == null) {
                    try {
                        if (!Utils.getApplicationLaunched()) {
                            Application.launch(options.get(option).getGui().getClass());
                            Utils.setAplicationLaunched(true);
                        } else {
                            System.out.println("It's not possible to open another feature with graphic interface. \nPlease restart the application.");
                        }
                    } catch (Exception e) {
                        System.out.println("It's not possible to open another feature with graphic interface. \nPlease restart the application.");
                    }
                }
            }

        } while (option != -1);
    }
}
