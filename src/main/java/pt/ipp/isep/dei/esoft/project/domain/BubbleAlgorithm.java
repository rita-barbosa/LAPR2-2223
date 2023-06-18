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
        Double[] areas = getAreasArray(announce);
        if (sortingOrder.equalsIgnoreCase("Ascending")) {
            this.sortAscending(areas, list.size());
        }else {
            this.sortDescending(areas, list.size());
        }
        this.sortedList = getDealsSorted(areas, announce);
        return this.sortedList;
    }

    private List<Announcement> getDealsSorted(Double[] areas, Announcement[] announce) {
        List<Announcement> newAnnounce = new ArrayList<>(announce.length);
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas.length; j++) {
                if (areas[i] == announce[j].getRequest().getProperty().getArea()) {
                    newAnnounce.add(announce[j]);
                }
            }
        }
        return newAnnounce;
    }

    private Double[] getAreasArray(Announcement[] announce) {
        Double[] areas = new Double[announce.length];
        for (int i = 0; i < announce.length; i++) {
            areas[i] = announce[i].getRequest().getProperty().getArea();
        }
        return areas;
    }

    private Announcement[] getAnnouncementArray(List<?> list) {
        Announcement[] announce = new Announcement[list.size()];
        for (int i = 0; i < list.size(); i++) {
            announce[i] = (Announcement) list.get(i);
        }
        return announce;
    }

    public void sortAscending(Double[] areas, int size) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size - 1; j++)
                if (areas[j] > areas[j + 1]) {
                    Double temp = areas[j];
                    areas[j] = areas[j + 1];
                    areas[j + 1] = temp;
                }
    }

    public void sortDescending(Double[] areas, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (areas[j] < areas[j + 1]) {
                    Double temp = areas[j];
                    areas[j] = areas[j + 1];
                    areas[j + 1] = temp;
                }
            }
        }
    }

}