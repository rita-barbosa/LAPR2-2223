package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
        Announcement[] announce = getAnnouncementArray(list);
        // array,0,array.length-1
        this.mergeSort(announce, 0, announce.length - 1, sortingOrder);
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

    private void mergeSort(Announcement[] announce, int low, int high, String sortingOrder) {
        if (high - low + 1 > 1) {
            int mid = (low + high) / 2;
            mergeSort(announce, low, mid, sortingOrder);
            mergeSort(announce, mid + 1, high, sortingOrder);
            if (sortingOrder.equalsIgnoreCase("Ascending")) {
                mergeAcending(announce, low, mid, high);
            } else {
                mergeDescending(announce, low, mid, high);
            }
        }
    }

    private void mergeAcending(Announcement[] announce, int low, int mid, int high) {
        int i, j, k;
        Announcement[] c = new Announcement[high - low + 1];
        k = 0;
        i = low;
        j = mid + 1;
        while (i <= mid && j <= high) {
            if (announce[i].getRequest().getProperty().getArea() <= announce[j].getRequest().getProperty().getArea()) {
                c[k++] = announce[i++];
            } else {
                c[k++] = announce[j++];
            }
        }
        while (i <= mid) {
            c[k++] = announce[i++];
        }
        while (j <= high) {
            c[k++] = announce[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            announce[i] = c[k++];
        }
    }

    private void mergeDescending(Announcement[] announce, int low, int mid, int high) {
        int i, j, k;
        Announcement[] c = new Announcement[high - low + 1];
        k = 0;
        i = low;
        j = mid + 1;
        while (i <= mid && j <= high) {
            if (announce[i].getRequest().getProperty().getArea() >= announce[j].getRequest().getProperty().getArea()) {
                c[k++] = announce[i++];
            } else {
                c[k++] = announce[j++];
            }
        }
        while (i <= mid) {
            c[k++] = announce[i++];
        }
        while (j <= high) {
            c[k++] = announce[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
            announce[i] = c[k++];
        }
    }
}