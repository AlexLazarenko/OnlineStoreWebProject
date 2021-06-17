package edu.epam.web;

import java.util.*;

public class Test {
    public static String GroupTotals(String[] strArr) {
        // code goes here
        Map<String, Integer> treeMap = new TreeMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            String[] array = strArr[i].split(":");
            for (int x = 0; x < array.length; x++) {
                stringBuilder.append(array[x]);
                stringBuilder.append(",");
            }
        }
        String[] store = stringBuilder.toString().split(",");
        for (int i = 0; i < store.length; i =i+ 2) {
            if (treeMap.containsKey(store[i])) {
                treeMap.put(store[i], (treeMap.get(store[i]) + Integer.parseInt(store[i + 1])));
            } else {
                treeMap.put(store[i], Integer.valueOf(store[i + 1]));
            }
        }
        treeMap.entrySet().removeIf(entry -> (entry.getValue()==0));
        String finalStore=treeMap.toString();
        finalStore=finalStore.replace("=",":");
        finalStore=finalStore.replace("{","");
        finalStore=finalStore.replace("}","");
        finalStore=finalStore.replace(" ","");
        return finalStore;
    }

    public static void main(String[] args) {
        // keep this function call here
        System.out.println(GroupTotals(new String[]{"X:-1", "Y:1", "X:-4", "B:3", "X:5"}));
        Scanner s = new Scanner(System.in);
        //   System.out.print(GroupTotals(s.nextLine()));
    }
}
