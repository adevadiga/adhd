package com.algo.top.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray
 * with equal number of 0 and 1.
 */

public class FindMaximumLengthSubArrayHavingEqualNumberOf0sand1s {

  // Time: O(n2) Space: O(1)
  static int findMaxLength(int[] nums) {
    int max = 0;
    for (int  i = 0; i < nums.length-1; i++) {
      int countOfZero = nums[i] == 0 ? 1 : 0;
      int countOfOne = nums[i] == 1 ? 1 : 0;

      for (int j = i+1; i < nums.length; i++) {
        if (nums[j] == 0) countOfZero++;
        else if (nums[j] == 1) countOfOne++;

        if (countOfZero == countOfOne) {
          max = Math.max(max, j-i+1);
        }
      }
    }
    System.out.println(max);
    return max;
  }

  /*
  The count variable is incremented by one for every 1 encountered and the same is decremented by one 
  for every 0 encountered.
  We start traversing the array from the beginning. If at any moment, the count becomes zero, 
  it implies that we've encountered equal number of zeros and ones from the beginning till the current index of the array(i).
  
  Not only this, another point to be noted is that if we encounter the same count twice while traversing
  the array, it means that the number of zeros and ones are equal between the indices corresponding to the equal count
  values.
  
  https://leetcode.com/problems/contiguous-array/solution/
  */
  static int findMaxLength2(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int maxLen = 0, count = 0;
    for (int i = 0; i < nums.length; i++) {
      count = count + (nums[i] == 0 ? -1 : 1);
      if (map.containsKey(count)) {
        maxLen = Math.max(maxLen, i - map.get(count));
      } else {
        map.put(count, i);
      }
    }

    return maxLen;
  }


  public static void main(String[] args) {
    int[] nums = new int[]{0, 0, 1, 0, 0, 0, 1, 1};
                              
    findMaxLength2(nums);
  }

}