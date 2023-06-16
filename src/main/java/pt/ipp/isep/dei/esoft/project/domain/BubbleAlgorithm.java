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
        Announcement[] deals = getAnnouncementArray(list);
        Double[] areas = getAreaArray(deals);
        if (sortingOrder.equalsIgnoreCase("Ascending")) {
            this.sortAscending(areas, deals, list.size());
        }else {
            this.sortDescending(areas, deals, list.size());
        }
        this.sortedList = Arrays.asList(deals);
        return this.sortedList;
    }

    private Double[] getAreaArray(Announcement[] deals) {
        Double[] area = new Double[deals.length];
        for (int i = 0; i < deals.length; i++) {
            area[i] = deals[i].getRequest().getProperty().getArea();
        }
        return area;
    }

    private Announcement[] getAnnouncementArray(List<?> list) {
        Announcement[] deals = new Announcement[list.size()];
        for (int i = 0; i < list.size(); i++) {
            deals[i] = (Announcement) list.get(i);
        }
        return deals;
    }

    public void sortAscending(Double[] areas, Announcement[] deals, int size) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size - 1; j++)
                if (areas[j] > areas[j + 1]) {
                    Announcement temp = deals[j];
                    deals[j] = deals[j + 1];
                    deals[j + 1] = temp;
                }
    }

    public void sortDescending(Double[] areas, Announcement[] deals, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (areas[j] < areas[j + 1]) {
                    Announcement temp = deals[j];
                    deals[j] = deals[j + 1];
                    deals[j + 1] = temp;
                }
            }
        }
    }

}
