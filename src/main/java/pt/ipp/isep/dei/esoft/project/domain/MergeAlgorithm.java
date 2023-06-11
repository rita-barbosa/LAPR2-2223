package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MergeAlgorithm implements SortingAlgorithm, Serializable {
    List<Announcement> sortedList;

    public MergeAlgorithm() {
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
        Announcement[] announce = new Announcement[list.size()];
        for (int i = 0; i < list.size(); i++) {
            announce[i] = (Announcement) list.get(i);
        }
        this.mergeSort(announce, list.size(), sortingOrder);
        return this.sortedList;
    }

    private void mergeSort(Announcement[] announce, int size, String sortingOrder) {
        if (size < 2) {
            return;
        }

        int mid = size / 2;
        Announcement[] l = new Announcement[mid];
        Announcement[] r = new Announcement[size - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = announce[i];
        }
        for (int i = mid; i < size; i++) {
            r[i - mid] = announce[i];
        }
        mergeSort(l, mid, sortingOrder);
        mergeSort(r, size - mid, sortingOrder);

        if (sortingOrder.equalsIgnoreCase("Ascending")) {
            mergeAcending(announce, l, r, mid, size - mid);
        }else {
            mergeDescending(announce, l, r, mid, size - mid);
        }
    }

    private void mergeAcending(Announcement[] announce, Announcement[] l, Announcement[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getRequest().getProperty().getArea() <= r[j].getRequest().getProperty().getArea()) {
                announce[k++] = l[i++];
            } else {
                announce[k++] = r[j++];
            }
        }
        while (i < left) {
            announce[k++] = l[i++];
        }
        while (j < right) {
            announce[k++] = r[j++];
        }
    }

    private void mergeDescending(Announcement[] announce, Announcement[] l, Announcement[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getRequest().getProperty().getArea() <= r[j].getRequest().getProperty().getArea()) {
                announce[k++] = l[i++];
            } else {
                announce[k++] = r[j++];
            }
        }
        while (i < left) {
            announce[k++] = l[i++];
        }
        while (j < right) {
            announce[k++] = r[j++];
        }
    }

}
