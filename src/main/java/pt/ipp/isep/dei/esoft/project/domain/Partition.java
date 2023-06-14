package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Partition {

    private List<Integer> listOfDeals;
    private int minDifference;
    private List<Integer> subList1;
    private List<Integer> subList2;


    public Partition(List<Integer> numbers) {
        this.listOfDeals = numbers;
        getPartitions(numbers);
    }

    public int getMinDifference() {
        return minDifference;
    }

    public List<String> getSubLists() {
        this.getPartitions(this.listOfDeals);
        List<String> subListsString = new ArrayList<>();
        subListsString.add(this.getPartitionInfo(subList1, 1));
        subListsString.add(this.getPartitionInfo(subList2, 2));
        subListsString.add("Difference : " + this.minDifference);
        return subListsString;
    }

    private void getPartitions(List<Integer> listOfDeals) {
        int listOfDealsSize = getListSize(listOfDeals);
        int size = getPowerOfTwo(listOfDealsSize);

        this.minDifference = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            List<Integer> subList1 = new ArrayList<>(listOfDeals);
            List<Integer> subList2 = new ArrayList<>(listOfDeals);
            int subListSum1 = 0;
            int subListSum2 = 0;

            String binary = decimalToBinary(i);

            for (int j = 0; j < binary.length(); j++) {
                if (binary.charAt(j) == '0') {
                    subList1.set(j, 0);
                    subListSum2 += listOfDeals.get(j);
                } else {
                    subList2.set(j, 0);
                    subListSum1 += listOfDeals.get(j);
                }
            }
            if (binary.length() < size) {
                for (int k = binary.length(); k < listOfDealsSize; k++) {
                    subList1.set(k, 0);
                    subListSum2 += listOfDeals.get(k);
                }
            }
            int difference = calculateDifference(subListSum1, subListSum2);
            if (this.minDifference > difference) {
                this.minDifference = difference;
                this.subList1 = subList1;
                this.subList2 = subList2;
            }
        }
    }

    private static int getListSize(List<Integer> list) {
        int counter = 0;

        for (Integer num : list) {
            counter++;
        }
        return counter;
    }

    public static int getPowerOfTwo(int listSize) {
        int n = 2;
        if (listSize == 0) {
            return 0;
        } else if (listSize == 1) {
            return 1;
        } else {
            for (int i = 0; i < listSize - 1; i++) {
                n = n * 2;
            }
            return n - 1;
        }
    }

    public static String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }
        String binary = "";
        while (decimal > 0) {
            int bit = decimal % 2; //1Op + 1A
            binary = bit + binary;
            decimal /= 2;
        }

        return binary;
    }

    private String getPartitionInfo(List<Integer> subList, int idx) {
        StringBuilder s = new StringBuilder();

        s.append(String.format("|------------Subset | %1d------------|%n", idx));
        s.append(String.format("|  Agency ID  |  Number Properties |%n"));
        for (int i = 0; i < subList.size(); i++) {
            s.append(String.format("|     %2d      |         %-3d        |%n", i, subList.get(i)));
        }
        return s.toString();
    }

    private static int calculateDifference(int sum1, int sum2) {
        int dif = (sum2) - (sum1);
        if (dif < 0) {
            dif = (-dif);
        }
        return dif;
    }
}
