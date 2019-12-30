package com.algo.top.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find the length of the longest sub-sequence such
 * that elements in the subsequence are consecutive integers, the consecutive
 * numbers can be in any order.
 * 
 * This is not sub-array instead a sub sequence
 * 
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 */

 /**
  * Use HashMap
  The key thing is to keep track of the sequence length and store that in the boundary points of the sequence.
  For example, as a result, for sequence {2, 3, 4, 5}, map.get(2) and map.get(5) should both return 4.

  Whenever a new element n is inserted into the map, do two things:
    1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n.
     Variables left and right will be the length of those two sequences, 
     while 0 means there is no sequence and n will be the boundary point later.
     Store (left + right + 1) as the associated value to key n into the map.

     2. Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
  */
public class FindLongestConsecutiveSubsequence {

  // Time: O(nlgn). Space: O(n)
  static int solutionUsingSorting(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    Arrays.sort(nums);
    int longestStreak = 1;
    int currentStreak = 1;

    for (int i = 1; i < nums.length; i++) {
      // Ignore duplicates in the sequence count
      if (nums[i] != nums[i-1]) {
        if (nums[i] == nums[i-1]+1) {
          currentStreak++;
        } else {
          currentStreak = 1;
          longestStreak = Math.max(longestStreak, currentStreak);
        }
      }
    }
    return Math.max(longestStreak, currentStreak);
  }

  static int  solution(int[] nums) {
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();

    for(int n : nums) {
      if (!map.containsKey(n)) {
        int left = map.getOrDefault(n-1, 0);
        int right = map.getOrDefault(n+1, 0);

        //Length of sequence of n
        int sum = left + right + 1;
        map.put(n, sum);
        res = Math.max(res, sum);

        // extend the length to the boundary(s) of the sequence 
        // Does nothing if left & right is 0 - both will be n
        map.put(n-left, sum);
        map.put(n+right, sum);

      } else {
        //Duplicate
        continue;
      }
    }

    System.out.println(res);
    return res;
   
  }

  public static void main(String[] args) {
    int[] a = new int[]{12, 13, 15, 56, 14, 89};
    solution(a);

    int[] b = new int[]{0};
    solution(b);
  }
}