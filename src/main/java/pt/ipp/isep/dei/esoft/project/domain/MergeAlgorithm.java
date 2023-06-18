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

    private void mergeSort(Double[] areas, int low, int high, String sortingOrder) {
        if (high - low + 1 > 1) {
            int mid = (low + high) / 2;
            mergeSort(areas, low, mid, sortingOrder);
            mergeSort(areas, mid + 1, high, sortingOrder);
            if (sortingOrder.equalsIgnoreCase("Ascending")) {
                mergeAscending(areas, low, mid, high);      // 21 (n/2) + 19
            } else {
                mergeDescending(areas, low, mid, high);
            }
        }
    }

    private void mergeAscending(Double[] areas, int low, int mid, int high) { // 21(n/2) + 19 💀 help
        int i, j, k; // 3A
        Double[] c = new Double[high - low + 1]; // 1A + 2Op
        k = 0; // 1A
        i = low; // 1A
        j = mid + 1; // 1A
        while (i <= mid && j <= high) { //enquanto ambas as metades ainda têm valores // (n/2 + 1)C + (n/2 + 1)C = n + 2
            if (areas[i] <= areas[j]) { // 2 * (n/2 + 1)L + (n/2 + 1)C = (n + 1) + (n/2 + 1) = (3n + 4)/2
                c[k++] = areas[i++];  //   (n/2 + 1)AouI + (n/2 + 1)A = n + 2
            } else {                    // apenas uma das duas vai contabilizar e as duas têm o mesmo numero de operações
                c[k++] = areas[j++];    // logo nao afeta a complexidade, i think
            }
        } // (n + 2) + (n + 2) + (3n + 4)/2 = 7(n/2) + 6
        while (i <= mid) { //se a metade de mid + 1 a high se esgotar primeiro  // (n/2)C + 1 = n/2 + 1
            c[k++] = areas[i++];  //   [2AouI + 1A] * (n/2) = n + n/2
        }  // (n/2 + 1) + (n + n/2) = 2n + 1                                        // apenas 1 dos whiles' vai executar
        while (j <= high) { //se a metade de low a mid se esgotar primeiro
            c[k++] = areas[j++];
        }
        k = 0; // 1A
        for (i = low; i <= high; i++) { //overwrite areas com os valores de c  // (n + 1)A + (n + 1)C= 2n + 2
            areas[i] = c[k++];  //   [2AouI + 1A] * (n) = 3n
        } // (2n + 2) + 3n = 5n + 2
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