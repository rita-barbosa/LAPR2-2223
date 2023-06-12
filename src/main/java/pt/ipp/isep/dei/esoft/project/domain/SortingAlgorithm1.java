package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithm1 implements SortingAlgorithm, Serializable {

    List<VisitDto> sortDtoList;

    public SortingAlgorithm1(){
        this.sortDtoList = new ArrayList<>();
    }

    public List<VisitDto> getSortDtoList(){
        return sortDtoList;
    }

    public void setSortDtoList(List<VisitDto> sortDtoList){
        this.sortDtoList = sortDtoList;
    }

    @Override
    public List<VisitDto> sort(String sortingOrder, List<?> list){
//        if (sortingOrder.equalsIgnoreCase("Ascending")){
//            this.sortingAlgorithm(list.toArray(), list.size());
//        }
        this.sortingAlgorithm(list.toArray(), list.size());
        return this.sortDtoList;
    }

    public void sortingAlgorithm(Object[] list, int size){
        VisitDto[] visit = (VisitDto[]) list;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (visit[j].getVisitDate().isAfter(visit[j + 1].getVisitDate())){
                    VisitDto aux = visit[j];
                    visit[j] = visit[j + 1];
                    visit[j + 1] = aux;
                }
            }
        }
    }

}
