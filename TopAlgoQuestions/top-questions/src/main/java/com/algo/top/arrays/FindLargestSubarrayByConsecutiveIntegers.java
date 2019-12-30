package com.algo.top.arrays;

import java.util.HashSet;
import java.util.Set;

public class FindLargestSubarrayByConsecutiveIntegers {

  // Assume distinct integers. Find all sub-arrays
  // If all elements are distinct, 
  // then a subarray has contiguous elements if and only if the difference 
  // between maximum and minimum elements in subarray is equal to the difference
  // between last and first indexes of subarray.

  static int findLength(int[] arr) {
    int maxLength = 0;
    for (int i = 0; i < arr.length-1; i++) {
      int min = arr[i], max = arr[i]; 
      for (int j = i+1; j < arr.length; j++) {  
        min = Math.min(min, arr[j]);
        max = Math.max(max, arr[j]);

        //Now the main condition
        if (max- min == j - i) {
          maxLength = Math.max(maxLength, j - i+1);
        }
      }
    }
    return maxLength;
  }

  //The above solution doesn't work if subarray contains duplicates. 
  // To check duplicate elements in a subarray, we create a hash set for every subarray 
  // and if we find an element already in hash, we don’t consider the current subarray.
  static int findLengthWithDuplicates(int[] arr) {
    int maxLength = 0;
    for (int i = 0; i < arr.length-1; i++) {
      Set<Integer> set = new HashSet<>();
      set.add(arr[i]);

      int min = arr[i], max = arr[i];

      for (int  j = i+1; j < arr.length; j++) {
        if(set.contains(arr[j])) {
          break;
        }

        min = Math.min(min, arr[j]);
        max = Math.min(max, arr[j]);
        set.add(arr[j]);

        if (max - min == j-i) {
          maxLength = Math.max(maxLength, j-i+1);
        }
      }
    }

    return maxLength;
  }
  public static void main(String[] args) {
    int arr[] = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45}; 
    System.out.println("Length of the longest contiguous subarray is "
                + findLength(arr)); 
  }
}