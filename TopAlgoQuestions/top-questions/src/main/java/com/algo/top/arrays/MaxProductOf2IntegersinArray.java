package com.algo.top.arrays;

public class MaxProductOf2IntegersinArray {

  static int solve2(int[] a) {
    int max = Integer.MIN_VALUE;
    int secondMax = Integer.MIN_VALUE;
    int negativeMax = Integer.MIN_VALUE;
    int negativeSecondMax = Integer.MIN_VALUE;

    for (int i = 0; i < a.length-1; i++) {
      if (a[i] > max) {
        secondMax = max;
        max = a[i];
      } else if (a[i] > secondMax) {
        secondMax = a[i];
      }

      if (a[i]< 0 && Math.abs(a[i]) > Math.abs(negativeMax)) {
        negativeSecondMax = negativeMax;
        negativeMax = a[i];
      } else if (a[i]< 0 && Math.abs(a[i]) > Math.abs(negativeSecondMax)) {
        negativeSecondMax = a[i];
      }
    }

    return Math.max(max*secondMax, negativeMax*negativeSecondMax);
  }

  //O(n2)
  static void solve(int[] a) {
    int maxProduct = 1;

    for (int i = 0; i < a.length-1; i++) {
      for (int j = i+1; j < a.length; j++) {
        maxProduct = Math.max(a[i]*a[j], maxProduct);
      }
    }
    System.out.println(maxProduct);
  }

  //Can use sorting.

  public static void main(String[] args) {
    int[] a = new int[]{-1, -3, -4, 2, 0, -5};
    int max = solve2(a);
    System.out.println(max);
  }
}