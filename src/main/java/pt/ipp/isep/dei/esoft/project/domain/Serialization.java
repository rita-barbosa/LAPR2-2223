package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;

public class Serialization {
    public static void saveData() throws IOException {
        Repositories.getInstance().getPersonRepository().savePeople();
        Repositories.getInstance().getAgencyRepository().saveAgencies();
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        Repositories.getInstance().getPersonRepository().loadPeople();
        Repositories.getInstance().getAgencyRepository().loadAgencies();
    }
}
