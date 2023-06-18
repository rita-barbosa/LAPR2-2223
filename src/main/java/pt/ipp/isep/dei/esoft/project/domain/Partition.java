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
        calculatePartitions(listOfDeals);
    }

    public int getMinDifference() {
        return minDifference;
    }

    public List<String> getSubLists() {
        List<String> subListsString = new ArrayList<>();
        subListsString.add(this.getPartitionInfo(this.subList1, 1));
        subListsString.add(this.getPartitionInfo(this.subList2, 2));
        subListsString.add(String.valueOf(this.minDifference));
        return subListsString;
    }

    private void calculatePartitions(List<Integer> listOfDeals) {
        int listOfDealsSize = listOfDeals.size(); // 1 : O(1)
        int size = getPowerOfTwo(listOfDealsSize); //3n + 2 :

        this.minDifference = Integer.MAX_VALUE; // 1A

        for (int i = 0; i < size; i++) { // (2^n -1) + 1 = 2^n
            List<Integer> subList1 = new ArrayList<>(listOfDeals); // (2^n -1) A
            List<Integer> subList2 = new ArrayList<>(listOfDeals); // (2^n -1)  A
            int subListSum1 = 0; // (2^n -1) A
            int subListSum2 = 0; // (2^n -1) A

            String binary = decimalToBinary(i); // 7n + 4 * (2^n -1)

            for (int j = 0; j < binary.length(); j++) { // (n + 1) * (2^n -1)
                if (binary.charAt(j) == '0') { // n * (2^n -1) C
                    subList1.set(j, 0); // n * (2^n -1) A
                    subListSum2 += listOfDeals.get(j); // n * (2^n -1) A + (n) * (2^n -1)op
                } else {
                    subList2.set(j, 0);
                    subListSum1 += listOfDeals.get(j);
                }
            }

            if (binary.length() < size) { // (2^n -1) C
                for (int k = binary.length(); k < listOfDealsSize; k++) { // (n - 1) + 1 = n * (2^n -1)
                    subList1.set(k, 0); // (n-1) * (2^n -1) A
                    subListSum2 += listOfDeals.get(k); // (n-1) * (2^n -1) A +  (n-1) * (2^n -1)op
                }
            }

            int difference = calculateDifference(subListSum1, subListSum2); // 6 * 2^n-1 A
            if (this.minDifference > difference) { // 2^n-1 C
                this.minDifference = difference; //2^n-1 A
                this.subList1 = subList1; // 2^n-1 A
                this.subList2 = subList2; //2^n-1 A
            }
        }
    }


    public static int getPowerOfTwo(int listSize) { // 1 + 1 + 1 + n + (n-1) + (n-1) + 1 = 3n + 2
        int n = 2; //1A
        if (listSize == 0) {  //1C
            return 0;
        } else if (listSize == 1) { //1C
            return 1;
        } else {
            for (int i = 0; i < listSize - 1; i++) { // (n-1) + 1 = n
                n = n * 2; // (n-1)op + (n-1)1A
            }
            return n - 1; //1R
        }
    }

    public static String decimalToBinary(int decimal) { // 1 + 1 + 1 + (n +1) + (n) + (n) + (n) +  (n) + (n) + (n) = 7n + 4
        if (decimal == 0) { //1C
            return "0";
        }
        String binary = ""; // 1A
        while (decimal > 0) { // (n) + 1C
            int bit = decimal % 2; // (n)op + (n)A
            binary = bit + binary; // (n)op + (n)A
            decimal = decimal / 2; // (n)op + (n)A
        }

        return binary; //1R
    }

    private String getPartitionInfo(List<Integer> subList, int idx) {
        StringBuilder s = new StringBuilder();

        s.append(String.format(" AGENCY |  Number Properties%n", idx));

        for (int i = 0; i < subList.size(); i++) {
            if (subList.get(i) != 0) {
                s.append(String.format(" - ID:   %2d  |    %d%n", i + 1, subList.get(i)));
            }
        }
        return s.toString();
    }

    private static int calculateDifference(int sum1, int sum2) { // 1+1+1+1+1+1 = 6
        int dif = (sum2) - (sum1); //1op + 1A
        if (dif < 0) { // 1C
            dif = (-dif); //1op +1A
        }
        return dif; //1R
    }
}
