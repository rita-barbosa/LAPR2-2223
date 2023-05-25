package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AddAgencyUI;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayAgenciesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerUI implements Runnable{
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Display Agencies.",new DisplayAgenciesUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Mananger Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
