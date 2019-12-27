package com.algo.top.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindPairWithGivenSum {

  // Use sorting. Sorting for merge sort worst case o(nlogn)
  static void find(int[] a, int k) {
    Arrays.sort(a);
    for (int i = 0, j = a.length - 1;  i < a.length && j >= 0;) {
      int sum = a[i] + a[j];
      if (sum == k) {
        System.out.println(i+ ", " + j); 
        return;
      } else if (sum < k ) {
        i++;
      } else {
        j--;
      }
    }
  }

  // Time = O(n)  Space = O(n)
  static int[] onePassHashTable(int[] a, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < a.length; i++) {
      int complement = target - a[i];

      if (map.containsKey(complement)) {
        return new int[] { i, map.get(complement) };
      }

      map.put(a[i], i);

    }
   
    throw new IllegalArgumentException("No tuples found");
  }
  public static void main(String[] args) {
    System.out.println("FindPairWithGivenSum");
    int[] a = { 1, 4, 45, 6, 10, -8 }; 
    int n = 16; 
    find(a, n);

    int[] tuple = onePassHashTable(a, n);
    System.out.println(Arrays.toString(tuple));
  }
}