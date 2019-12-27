package com.algo.top.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number 
 * of continuous subarrays whose sum equals to k.
 */
public class FindSubArraysWith0Sum {

  // O(n2)
  static int find(int[] nums, int k) {
    int count = 0;
    for (int i = 0; i < nums.length - 1 ; i++) {
      int sum=0;
      for (int j = i+1; j < nums.length; j++) {
        sum += nums[j];

        if (sum == k) {
          count++;
        }
      }
    }
    return count;
  }

  // O(n) via hashMap
  /*
  The idea behind this approach is as follows: 
  If the cumulative sum(represanted by sum[i], sum[i] for sum upto ith & jth
  index) upto two indices is the same,
   the sum of the elements lying in between those indices is zero. 

   Extending the same thought further, if the cumulative sum upto two indices, 
   say i and j is at a difference of k i.e. 
   if sum[i] - sum[j] = k , the sum of elements lying between indices i and j is k.
  */

  static int find2(int[] nums, int k) {
    int count = 0;
    int sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // Note a zero sum is needed for single case. Ex: two elements 1&1 sum is 2, check for complement of 0 in map :)
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (map.containsKey(sum-k)) {
        count += map.get(sum-k);
      }

      map.put(sum, map.getOrDefault(sum, 0)+1);
    }

    return count;
  }


  public static void main(String[] args) {
    int[] arr = new int[]{1,1,1};
    find2(arr, 2);
  }
}