package pt.ipp.isep.dei.esoft.project.ui.console.menu;

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
                options.get(option).run();
            }
        } while (option != -1);
    }
}
