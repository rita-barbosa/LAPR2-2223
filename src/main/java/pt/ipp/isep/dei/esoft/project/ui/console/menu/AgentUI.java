package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.menu.gui.ListVisitsApp;
import javafx.application.Application;
import pt.ipp.isep.dei.esoft.project.ui.console.AcceptOrdersUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListRequestsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListVisitsUI;
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
        options.add(new MenuItem("List property announcement requests", new ListRequestsUI()));
        options.add(new MenuItem("List booking requests", new ListVisitsUI()));
        options.add(new MenuItem("List booking requests (Java Fx)", new ListVisitsApp()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nAgent Menu:");

            if ((option >= 0) && (option < options.size())) {
                if (options.get(option).getGui() == null) {
                    options.get(option).run();
                } else if (options.get(option).getUi() == null) {
                    try {
                        Application.launch(options.get(option).getGui().getClass());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

//            if ((option >= 0) && (option < options.size())) {
//                options.get(option).run();
//            }
        } while (option != -1);
    }
}
