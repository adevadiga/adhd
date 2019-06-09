package com.adhd.algo.warmup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SockMerchant {

    static int sockMerchantMap(int n, int[] ar) {
        int totalPairs = 0;
        Map<Integer, Integer> colorAndCount = new HashMap<>();
        for (int i=0; i < n; i++) {
            int elem = ar[i];
            int count = 0;
            if (colorAndCount.containsKey(elem)) {
                count = colorAndCount.get(elem);
            }
            colorAndCount.put(elem, ++count);
        }
        for (Entry<Integer, Integer>  pair : colorAndCount.entrySet()) {
            totalPairs += pair.getValue() /2;
        }
        return totalPairs;
    }

    static int sockMerchantSet(int n, int[] ar) {
        int totalPairs = 0;
        Set<Integer> colorSockSet = new HashSet<>();

        for (int elem : ar) {
            /*if (!colorSockSet.contains(elem)) {
                colorSockSet.add(elem);
            } else {
                totalPairs++;
                colorSockSet.remove(elem);
            }*/
            if (colorSockSet.remove(elem)) {
                totalPairs++;
            } else {
                colorSockSet.add(elem);
            }
        }
        return totalPairs;
    }
    static int sock(int n, int[] ar) {
        int[] count = new int[101];
        for (int elem : ar) {
            count[elem]++;
        }
        int totalPairs = 0;
        for (int elem : count) {
            totalPairs += elem/2;
        } 
        return totalPairs;
    }

    
    public static void main(String[] args) {
        int arr[] = new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20};
        int val = SockMerchant.sockMerchantSet(9, arr);
        System.out.println(val);
    }

}