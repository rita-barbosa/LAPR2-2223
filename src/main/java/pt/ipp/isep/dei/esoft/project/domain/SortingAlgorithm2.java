package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SortingAlgorithm2 implements SortAlgorithm, Serializable {

    List<VisitDto> sortDtoList;

    public SortingAlgorithm2(List<VisitDto> visitDtoList){
        this.sortDtoList = visitDtoList;
    }

    public List<VisitDto> getSortDtoList(){
        return sortDtoList;
    }

    @Override
    public List<VisitDto> sort(List<?> list){
        VisitDto[] visit = getVisitDtoArray(list);
        this.mergeAlgorithm(visit, 0, visit.length - 1);
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


    private void mergeAlgorithm(VisitDto[] visitDtos, int low, int high){
        if (high - low + 1 > 1){
            int middle = (low + high) / 2;
            mergeAlgorithm(visitDtos, low, middle);
            mergeAlgorithm(visitDtos, middle + 1, high);
            ascendingAlgorithm(visitDtos, low, middle, high);
        }
    }

    private void ascendingAlgorithm(VisitDto[] visitDtos, int low, int middle, int high){
        int first = low;
        int second = middle + 1;
        int third = 0;
        VisitDto[] v = new VisitDto[high - low + 1];

        while (first <= middle && second <= high){
            if (visitDtos[first].getVisitDate().isEqual(visitDtos[second].getVisitDate()) ||visitDtos[first].getVisitDate().isBefore(visitDtos[second].getVisitDate())){
                v[third++] = visitDtos[first++];
            } else {
                v[third++] = visitDtos[second++];
            }
        }
        while (first <= middle){
            v[third++] = visitDtos[first++];
        }
        while (second <= high){
            v[third++] = visitDtos[second++];
        }
        third = 0;
        for (int i = low; i <= high; i++) {
            visitDtos[i] = v[third++];
        }
    }

}
