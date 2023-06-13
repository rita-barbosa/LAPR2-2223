package pt.ipp.isep.dei.esoft.project.domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Runtime.getRuntime;

public class Partition {

    private static List<String> sublistStringList = new ArrayList<>();
    private static int listNum = 0;


    public static List<String> getSubLists(List<Integer> listOfDeals) {
        int size = listOfDeals.size();
        List<Long> binaryRepList = getBinaryRepList(size);
        return getPartitions(listOfDeals, binaryRepList);
    }

    private static List<String> getPartitions(List<Integer> listOfDeals, List<Long> binaryRepList) {
        for (Long binary : binaryRepList){
           Long binaryRep = getBinaryRepresentation(binaryRepList.indexOf(binary));
           String partitionInfo = getPartitionInfo(listOfDeals, binaryRep);
            sublistStringList.add(partitionInfo);
        }
        return sublistStringList;
    }

    private static List<Long> getBinaryRepList(int size) {
        return null;
    }

    private static Long getBinaryRepresentation(int idx) {
        return null;
    }

    private static String getPartitionInfo(List<Integer> listOfDeals, Long binaryRep) {
        listNum++;
        // IDK BUT MAYBE PUT THE SUBLIST INTO A STRING????????
        List<Integer> sublist = new ArrayList<>();
        //Lista 1 : [9, 28, 32] : Tamanho input : 3 : Sublista : [9, 28, 0] : Diferenca : 5 : Time : 2.19345092773e-05
        Pair<Integer, String> differenceInfo = calculateDifference();

        return String.format("List %d: %s Input Size: %d Sublist: %s Diference: %d Time: %s",
                listNum, Arrays.toString(listOfDeals.toArray()), listOfDeals.size(), sublist, differenceInfo.getKey(), differenceInfo.getValue());
    }

    private static Pair<Integer, String> calculateDifference(){
        return new Pair<>(null, getRuntime().toString());
    }
}
