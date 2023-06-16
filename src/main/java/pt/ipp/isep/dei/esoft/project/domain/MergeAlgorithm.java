package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MergeAlgorithm implements SortingAlgorithm, Serializable {
    List<Announcement> sortedList;

    public MergeAlgorithm(List<Announcement> list) {
        this.sortedList = list;
    }

    public List<Announcement> getSortedList() {
        return sortedList;
    }

    @Override
    public List<Announcement> sort(String sortingOrder, List<?> list) {
        Announcement[] deals = getAnnouncementArray(list);
        Double[] areas = getAreaArray(deals);
        this.mergeSort(areas, deals, 0, deals.length - 1, sortingOrder);
        this.sortedList = Arrays.asList(deals);
        return this.sortedList;
    }

    private Announcement[] getAnnouncementArray(List<?> list) {
        Announcement[] deals = new Announcement[list.size()];
        for (int i = 0; i < list.size(); i++) {
            deals[i] = (Announcement) list.get(i);
        }
        return deals;
    }

    private Double[] getAreaArray(Announcement[] deals) {
        Double[] area = new Double[deals.length];
        for (int i = 0; i < deals.length; i++) {
            area[i] = deals[i].getRequest().getProperty().getArea();
        }
        return area;
    }

    private void mergeSort(Double[] areas, Announcement[] deals, int low, int high, String sortingOrder) {
        if (high - low + 1 > 1) {
            int mid = (low + high) / 2;
            mergeSort(areas, deals, low, mid, sortingOrder);
            mergeSort(areas, deals, mid + 1, high, sortingOrder);
            if (sortingOrder.equalsIgnoreCase("Ascending")) {
                mergeAscending(areas, deals, low, mid, high);
            } else {
                mergeDescending(areas, deals, low, mid, high);
            }
        }
    }

    private void mergeAscending(Double[] areas, Announcement[] deals, int low, int mid, int high) {
        Announcement[] c = new Announcement[high - low + 1];
        int k = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (areas[i] <= areas[j]) {
                c[k++] = deals[i++];
            } else {
                c[k++] = deals[j++];
            }
        }
        while (i <= mid) {
            c[k++] = deals[i++];
        }
        while (j <= high) {
            c[k++] = deals[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            deals[i] = c[k++];
        }
    }

    private void mergeDescending(Double[] areas, Announcement[] deals, int low, int mid, int high) {
        Announcement[] c = new Announcement[high - low + 1];
        int k = 0;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (areas[i] >= areas[j]) {
                c[k++] = deals[i++];
            } else {
                c[k++] = deals[j++];
            }
        }
        while (i <= mid) {
            c[k++] = deals[i++];
        }
        while (j <= high) {
            c[k++] = deals[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            deals[i] = c[k++];
        }
    }
}