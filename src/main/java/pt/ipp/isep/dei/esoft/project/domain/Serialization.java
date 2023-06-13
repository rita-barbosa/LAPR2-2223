package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class Serialization {
    public static void saveData() {
        Repositories.getInstance().getPersonRepository().savePeople();
        Repositories.getInstance().getAgencyRepository().saveAgencies();
    }

    public static void loadData() {
        Repositories.getInstance().getAgencyRepository().loadAgencies();
        Repositories.getInstance().getPersonRepository().loadPeople();
    }
}
