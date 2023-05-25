package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AgencyController;
import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.Comparator;
import java.util.List;

public class DisplayAgenciesUI implements Runnable{
    private final AgencyController controller = new AgencyController();

    @Override
    public void run() {
        Comparator<Agency> sortAgenciesByAnnouncementCount = new Comparator<Agency>() {
            public int compare(Agency a1, Agency a2) {
                Integer value1 = a1.getAnnouncementsList().size();
                Integer value2 = a2.getAnnouncementsList().size();

                return value2.compareTo(value1);
            }
        };
        List<Agency> agencies = controller.getAgencies();
        agencies.sort(sortAgenciesByAnnouncementCount);
        for (Agency announcement : agencies) {
            List<Employee> employees = announcement.getAgentList();
            Comparator<Employee> sortCitiesByAlphabeticOrder = new Comparator<Employee>() {
                public int compare(Employee a1, Employee a2) {
                    String value1 = a1.getName();
                    String value2 = a2.getName();

                    return value1.compareTo(value2);
                }
            };
            employees.sort(sortCitiesByAlphabeticOrder);
            System.out.println(announcement.getDescription());
            for(Employee employee : employees){
                System.out.println('-' + employee.getName());
            }
            System.out.println();
        }
    }

}
