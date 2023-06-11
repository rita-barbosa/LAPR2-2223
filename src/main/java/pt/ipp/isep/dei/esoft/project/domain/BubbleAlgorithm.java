package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BubbleAlgorithm implements SortingAlgorithm, Serializable {

    List<Announcement> sortedList;

    public BubbleAlgorithm() {
        this.sortedList = new ArrayList<>();
    }

    public List<Announcement> getSortedList() {
        return sortedList;
    }

    public void setSortedList(List<Announcement> sortedList) {
        this.sortedList = sortedList;
    }

    @Override
    public List<Announcement> sort(String sortingOrder, List<?> list) {
        if (sortingOrder.equalsIgnoreCase("Ascending")) {
            this.sortAscending(list.toArray(), list.size());
            return this.sortedList;
        }
        sortDescending(list.toArray(), list.size());
        return this.sortedList;
    }

    public void sortAscending(Object[] list, int size) {
        Announcement[] announce = (Announcement[]) list;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size - 1; j++)
                if (announce[j].getRequest().getProperty().getArea() > announce[j + 1].getRequest().getProperty().getArea()) {
                    Announcement temp = announce[j];
                    announce[j] = announce[j + 1];
                    announce[j + 1] = temp;
                }
    }

    public void sortDescending(Object[] list, int size) {
        Announcement[] announce = (Announcement[]) list;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (announce[j].getRequest().getProperty().getArea() < announce[j + 1].getRequest().getProperty().getArea()) {
                    Announcement temp = announce[j];
                    announce[j] = announce[j + 1];
                    announce[j + 1] = temp;
                }
            }
        }
    }

}
