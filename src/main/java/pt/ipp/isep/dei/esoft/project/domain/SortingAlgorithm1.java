package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SortingAlgorithm1 implements SortAlgorithm, Serializable {

    List<VisitDto> sortDtoList;

    public SortingAlgorithm1(List<VisitDto> visitDtoList){
        this.sortDtoList = visitDtoList;
    }

    public List<VisitDto> getSortDtoList(){
        return sortDtoList;
    }

//    public void setSortDtoList(List<VisitDto> sortDtoList){
//        this.sortDtoList = sortDtoList;
//    }

    @Override
    public List<VisitDto> sort(List<?> list){
//        if (sortingOrder.equalsIgnoreCase("Ascending")){
//            this.sortingAlgorithm(list.toArray(), list.size());
//        }
        VisitDto[] visit = getVisitDtoArray(list);
        this.sortingAlgorithm(visit, list.size());
        this.sortDtoList = Arrays.asList(visit);
        return this.sortDtoList;
    }

    private VisitDto[] getVisitDtoArray(List<?> list){
        VisitDto[] visitDto = new VisitDto[list.size()];
        for (int i = 0; i < list.size(); i++) {
            visitDto[i] = (VisitDto) list.get(i);
        }
        return visitDto;
    }

    public void sortingAlgorithm(Object[] list, int size){
        VisitDto[] visit = (VisitDto[]) list;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (visit[j + 1].getVisitDate().isBefore(visit[j].getVisitDate())){
                    VisitDto aux = visit[j];
                    visit[j] = visit[j + 1];
                    visit[j + 1] = aux;
                }
            }
        }
    }

}
