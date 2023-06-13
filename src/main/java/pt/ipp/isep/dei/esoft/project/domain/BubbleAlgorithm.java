package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleAlgorithm implements SortingAlgorithm, Serializable {

    List<Announcement> sortedList;

    public BubbleAlgorithm(List<Announcement> list) {
        this.sortedList = list;
    }

    public List<Announcement> getSortedList() {
        return sortedList;
    }


    @Override
    public List<Announcement> sort(String sortingOrder, List<?> list) {
        Announcement[] announce = getAnnouncementArray(list);
        if (sortingOrder.equalsIgnoreCase("Ascending")) {
            this.sortAscending(announce, list.size());
        }else {
            this.sortDescending(announce, list.size());
        }
        this.sortedList = Arrays.asList(announce);
        return this.sortedList;
    }

    private Announcement[] getAnnouncementArray(List<?> list) {
        Announcement[] announce = new Announcement[list.size()];
        for (int i = 0; i < list.size(); i++) {
            announce[i] = (Announcement) list.get(i);
        }
        return announce;
    }

    public void sortAscending(Announcement[] announce, int size) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size - 1; j++)
                if (announce[j].getRequest().getProperty().getArea() > announce[j + 1].getRequest().getProperty().getArea()) {
                    Announcement temp = announce[j];
                    announce[j] = announce[j + 1];
                    announce[j + 1] = temp;
                }
    }

    public void sortDescending(Announcement[] announce, int size) {
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
