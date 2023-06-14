package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.gui.AnalyzeDealsApp;
import javafx.application.Application;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class StoreManagerUI implements Runnable {
    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
//        options.add(new MenuItem("Analyze Sale Deals", new AnalyzeDealsUI()));
        options.add(new MenuItem("Analyze Sale Deals", new AnalyzeDealsApp()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nStore Manager Menu:");

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
        } while (option != -1);
    }
}
