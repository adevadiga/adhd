package com.adhd.algo.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given queries. Each query is of the form two integers described
 * below:
 * 
 * 1 x : Insert x in your data structure.
 * 
 * 2 y : Delete one occurence of y from your data structure, if present.
 * 
 * 3 z : Check if any integer is present whose frequency is exactly z. If yes,
 * print 1 else 0.
 */

public class FrequencyQueries {

    public static Integer INSERT = 1;
    public static Integer DELETE = 2;
    public static Integer SEARCH = 3;

    static List<Integer> freqQuery(int[][] queries) {
        List<Integer> type3Result = new ArrayList<>();
        Map<Integer, Integer> storageMap = new HashMap<>();
        for (int[] query : queries) {
            int operation = query[0];
            int v = query[1];

            if(operation == 3) {
                if(storageMap.containsValue(v)){
                    type3Result.add(1);
                }
                else
                    type3Result.add(0);
                
            } else if (operation == 1) {
                storageMap.put(v, storageMap.getOrDefault(v, 0) + 1);
            } else if (operation == 2) {
                //storageMap.put(v, storageMap.getOrDefault(v, 1) - 1);
                if (storageMap.containsKey(v)) {
                    if(storageMap.get(v)<=1)
                        storageMap.remove(v);
                    else
                        storageMap.put(v, storageMap.get(v)-1);

                }
            }
        }

        return type3Result;
    }
    static List<Integer> freqQuery2(List<List<Integer>> queries) {
        List<Integer> type3Result = new ArrayList<>();
        Map<Integer, Integer> storageMap = new HashMap<>();

        for (List<Integer> query : queries) {
            int operation = query.get(0);
            int v = query.get(1);
            
            if(operation == 3) {
                if(storageMap.containsValue(v)){
                    type3Result.add(1);
                }
                else
                    type3Result.add(0);
                
            } else if (operation == 1) {
                storageMap.put(v, storageMap.getOrDefault(v, 0) + 1);
            } else if (operation == 2) {
                //storageMap.put(v, storageMap.getOrDefault(v, 1) - 1);
                if (storageMap.containsKey(v)) {
                    if(storageMap.get(v)<=1)
                        storageMap.remove(v);
                    else
                        storageMap.put(v, storageMap.get(v)-1);

                }
            }
        }

        return type3Result;
    }

    static int search(Map<Integer, Integer> storageMap, int occurence) {

        for (int count : storageMap.values()) {
            if (count == occurence) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        /*int h = 10000026;
        List<List<Integer>> queries = new ArrayList<>();
        List<Integer> query = new ArrayList<>();
        query.add(3);query.add(5);
        queries.add(query);

        query = new ArrayList<>();
        query.add(3);query.add(3);
        queries.add(query);

        query = new ArrayList<>();
        query.add(1);query.add(10000004);
        queries.add(query);

        query = new ArrayList<>();
        query.add(1);query.add(10000016);
        queries.add(query);

        query = new ArrayList<>();
        query.add(1);query.add(10000011);
        queries.add(query);

        query = new ArrayList<>();
        query.add(3);query.add(10);
        queries.add(query);

        query = new ArrayList<>();
        query.add(1);query.add(10000006);
        queries.add(query);

        query = new ArrayList<>();
        query.add(3);query.add(5);
        queries.add(query);

        System.out.println(freqQuery(queries));*/
    }

}

