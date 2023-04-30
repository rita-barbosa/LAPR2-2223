package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;


public class CriteriaRepository {

    public List<String> criteria = new ArrayList<>();

    public CriteriaRepository(){
        this.criteria.add("Type of Business");
        this.criteria.add("Type of Property");
        this.criteria.add("Number of Bedrooms");
        this.criteria.add("Price");
        this.criteria.add("City");
        this.criteria.add("State");
    }

    public List<String> getCriteriaList(){
        return List.copyOf(criteria);
    }

}
