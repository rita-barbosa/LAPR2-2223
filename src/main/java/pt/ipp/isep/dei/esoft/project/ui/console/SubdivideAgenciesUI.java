package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SubdivideAgenciesController;

import java.util.List;

public class SubdivideAgenciesUI implements Runnable {

    /**
     * The Controller.
     */
    private final SubdivideAgenciesController controller = new SubdivideAgenciesController();

    @Override
    public void run() {
        System.out.println("\n=============================================================\n");
        System.out.println("Subdivision of Agencies and consequent partitions based on number of deals");
        System.out.println("\n=============================================================");
        displayList(controller.getAgenciesPartitions());
    }

    private void displayList(List<String> sublistStringList) {
        for (String partition : sublistStringList) {
            System.out.println(partition);
            //System.out.println("=============================================================");
        }
    }
}
