package pt.ipp.isep.dei.esoft.project.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The type Criteria repository.
 */
public class CriteriaRepository {

    /**
     * The list with the existing criterias.
     */
    public List<String> criterias;

    /**
     * Instantiates a new Criteria repository.
     */
    public CriteriaRepository(){
        criterias = new ArrayList<>();
    }

    /**
     * This method returns a copy of the list of criteria.
     *
     * @return a copy of the list of criteria.
     */
    public List<String> getCriteriaList(){
        return criterias;
    }

    /**
     * This method adds a new criteira to the list of criterias.
     *
     * @param criteria - the criteria to be
     * @return the criteria that was added to the list
     */
    public Optional<String> add(String criteria){
        Optional<String> newCriteria = Optional.empty();
        boolean success = false;

        if (validateBusinessType(criteria)){
            newCriteria = Optional.of(criteria);
            success = criterias.add(newCriteria.get());
        }

        if (!success){
            newCriteria = Optional.empty();
        }

        return newCriteria;
    }

    /**
     * This method verifies if the criteira received is already present in the list of criterias.
     *
     * @param criteria - the criteira to be validated
     * @return {@code true} if the list of criteiras does not contain the received agency; {@code false} otherwise.
     */
    private boolean validateBusinessType(String criteria) {
        return (!(criterias.contains(criteria)));
    }
}
