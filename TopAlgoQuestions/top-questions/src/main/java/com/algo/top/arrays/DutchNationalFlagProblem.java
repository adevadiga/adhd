package com.algo.top.arrays;

import java.util.Arrays;

/*
  Sort an array containing 0’s, 1’s and 2’s(Dutch national flag problem)
*/

// Can count 0's 1's and 2's and segregate.

public class DutchNationalFlagProblem {

  static void solve2(int[] nums) {
    int low = 0, high = nums.length-1;
    int mid = 0;
    while (mid <= high) {
      switch(nums[mid]) {
        case 0:
              swap(nums, low, mid);
              low++;
              mid++;
              break;
        case 1: 
              mid++;
              break;
        case 2: 
              swap(nums, high, mid);
              mid++;
              high--;
              break;
      }
    }
  }

  static void solve(int[] nums) {
    int pivot = 1;

    int k = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < pivot) {
        k++;
        swap(nums, i, k);
      }
    }
    System.out.println(Arrays.toString(nums));

    k = nums.length;
    for (int i = nums.length-1; i >= 0; i--) {
      if (nums[i] > pivot) {
        k--;
        swap(nums, i, k);
      }
    }

    System.out.println(Arrays.toString(nums));
  }

  static void swap(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static void main(String[] args) {
    int[] a = new int[]{2,0,2,1,1,0};
    solve2(a);
    System.out.println(Arrays.toString(a));
  }
}