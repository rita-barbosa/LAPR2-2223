package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public interface SortingAlgorithm {

     List<Announcement> sort(String sortingOrder, List<?> list) ;

}
