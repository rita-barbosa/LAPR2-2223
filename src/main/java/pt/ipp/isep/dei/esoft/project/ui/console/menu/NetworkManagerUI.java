package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import javafx.application.Application;
import pt.ipp.isep.dei.esoft.project.ui.console.DisplayAgenciesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListDealsNetworkUI;
import pt.ipp.isep.dei.esoft.project.ui.console.SubdivideAgenciesUI;
import pt.ipp.isep.dei.esoft.project.ui.console.gui.ListDealsNetworkApp;
import pt.ipp.isep.dei.esoft.project.ui.console.gui.SubdivideAgenciesApp;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NetworkManagerUI implements Runnable {
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Display Agencies.", new DisplayAgenciesUI()));
        options.add(new MenuItem("List Deals of Network.", new ListDealsNetworkApp()));
        options.add(new MenuItem( "Divide Agencies Into Subsets.", new SubdivideAgenciesApp()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nNetwork Manager Menu:");

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
