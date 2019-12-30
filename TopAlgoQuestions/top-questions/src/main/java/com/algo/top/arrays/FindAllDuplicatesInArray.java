package com.algo.top.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), 
some elements appear twice and others appear once.
Find all the elements that appear twice in this array.

Note that array elements are between 1 & n
*/
public class FindAllDuplicatesInArray {

  // Set - O(n), O(n)
  public List<Integer> findDuplicates(int[] nums) {
    Set<Integer> set = new HashSet<>();
    List<Integer> duplicates = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      if (set.contains(nums[i])) {
        duplicates.add(nums[i]);
      }

      set.add(nums[i]);
    }

    return duplicates;
  }

  // Smart solution
  // when find a number i, flip the number at position i-1 to negative. 
  // if the number at position i-1 is already negative, i is the number that occurs twice.
  // Don't sweat over -1 its just for array index
  public static List<Integer> findDuplicatesSmart(int[] nums) {
    System.out.println(Arrays.toString(nums));
    List<Integer> duplicates = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i])-1;

      if (nums[index] < 0) {
        duplicates.add(Math.abs(index+1));
      }

      nums[index] = -nums[index];
      System.out.println("i: "+ i+ ", " + "index: "+index + ", " + Arrays.toString(nums));
    }

    return duplicates;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{4,3,2,7,8,2,3,1};
    findDuplicatesSmart(arr);
  }


}