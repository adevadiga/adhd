package com.adhd.algo.searchingQ;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * You will be given an array of integers and a target value. 
 * Determine the number of pairs of array elements that have a difference equal to a target value.

For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values meeting the condition: 2-1=1, 3-2=1, and 4-3=1.
 */
public class Pairs {

    static int pairs(int k, int[] arr) {
        Set<Integer> numbers = Arrays.stream(arr).boxed().collect(Collectors.toSet());

		return (int) Arrays.stream(arr).filter(number -> numbers.contains(number + k)).count();
        /*int totalPairs = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : arr) {
            if (map.containsKey(value + k) || map.containsKey(value - k)) {
                totalPairs += map.getOrDefault(value + k, 0) + map.getOrDefault(value - k, 0);
            }

            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        return totalPairs;*/
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 4, 2};
        System.out.println(pairs(2, arr));
    }
}