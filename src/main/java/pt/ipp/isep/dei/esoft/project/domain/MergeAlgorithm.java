package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        Announcement[] announce = getAnnouncementArray(list);
        Double[] areas = getAreasArray(announce);
        this.mergeSort(areas, 0, areas.length - 1, sortingOrder);
        this.sortedList = Arrays.asList(getDealsSorted(areas, announce));
        return this.sortedList;
    }

    private Announcement[] getDealsSorted(Double[] areas, Announcement[] announce) {
        Announcement[] newAnnounce = new Announcement[announce.length];
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas.length; j++) {
                if (areas[i] == announce[j].getRequest().getProperty().getArea()) {
                    newAnnounce[i] = announce[j];
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

    private void mergeSort(Double[] areas, int low, int high, String sortingOrder) {
        if (high - low + 1 > 1) {
            int mid = (low + high) / 2;
            mergeSort(areas, low, mid, sortingOrder);
            mergeSort(areas, mid + 1, high, sortingOrder);
            if (sortingOrder.equalsIgnoreCase("Ascending")) {
                mergeAscending(areas, low, mid, high);
            } else {
                mergeDescending(areas, low, mid, high);
            }
        }
    }

    private void mergeAscending(Double[] areas, int low, int mid, int high) {
        int i, j, k;
        Double[] c = new Double[high - low + 1];
        k = 0;
        i = low;
        j = mid + 1;
        while (i <= mid && j <= high) {
            if (areas[i] <= areas[j]) {
                c[k++] = areas[i++];
            } else {
                c[k++] = areas[j++];
            }
        }
        while (i <= mid) {
            c[k++] = areas[i++];
        }
        while (j <= high) {
            c[k++] = areas[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            areas[i] = c[k++];
        }
    }

    private void mergeDescending(Double[] areas, int low, int mid, int high) {
        int i, j, k;
        Double[] c = new Double[high - low + 1];
        k = 0;
        i = low;
        j = mid + 1;
        while (i <= mid && j <= high) {
            if (areas[i] >= areas[j]) {
                c[k++] = areas[i++];
            } else {
                c[k++] = areas[j++];
            }
        }
        while (i <= mid) {
            c[k++] = areas[i++];
        }
        while (j <= high) {
            c[k++] = areas[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            areas[i] = c[k++];
        }
    }
}