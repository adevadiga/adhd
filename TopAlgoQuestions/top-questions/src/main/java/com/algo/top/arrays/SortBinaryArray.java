package com.algo.top.arrays;

import java.util.Arrays;

public class SortBinaryArray {

  static void sort(int[] binary) {
    int countOfZeroes = 0;

    for (int i = 0; i < binary.length; i++) {
      if (binary[i] == 0) {
        countOfZeroes++;
      }
    }

    int k = 0;
    while(countOfZeroes-- > 0) {
      binary[k++] = 0;
    }

    while (k < binary.length) {
      binary[k++] = 1;
    }
  }

  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
  static void sort2(int[] arr) {
    int j = -1;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 1) {
        j++;
        swap(arr, i , j);
      }
    }
  }

  public static void main(String[] args) {
    int a[] = { 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0 }; 

    sort(a);
    System.out.println(Arrays.toString(a));
  }
}