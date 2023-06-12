package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithm2 implements SortingAlgorithm, Serializable {

    List<VisitDto> sortDtoList;

    public SortingAlgorithm2(){
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
        VisitDto[] visit = new VisitDto[list.size()];
        for (int i = 0; i < list.size(); i++) {
            visit[i] = (VisitDto) list.get(i);
        }
        this.sortingAlgorithm(visit, list.size());
        return this.sortDtoList;
    }

    private void sortingAlgorithm(VisitDto[] visits, int size){
        if (size < 2){
            return;
        }

        int middle = size / 2;
        VisitDto[] first = new VisitDto[middle];
        VisitDto[] second = new VisitDto[size - middle];

        for (int i = 0; i < middle; i++) {
            first[i] = visits[i];
        }
        for (int i = middle; i < size; i++) {
            second[i - middle] = visits[i];
        }

        sortingAlgorithm(first, middle);
        sortingAlgorithm(second, size - middle);
        sortAscending(visits, first, second, size - middle, middle);
    }

    private void sortAscending(VisitDto[] visits, VisitDto[] first, VisitDto[] second, int right, int left){
        int aux1 = 0, aux2 = 0, aux3 = 0;
        while (aux1 < left && aux2 < right){
            if (first[aux1].getVisitDate().isEqual(second[aux2].getVisitDate()) || first[aux1].getVisitDate().isBefore(second[aux2].getVisitDate())) {
                visits[aux3++] = first[aux1++];
            } else{
                visits[aux3++] = second[aux2++];
            }
        }

        while (aux1 < left){
            visits[aux3++] = first[aux1++];
        }
        while (aux2 < right){
            visits[aux3++] = second[aux2++];
        }
    }

}
