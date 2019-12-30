package com.algo.top.arrays;

public class MaxLengthSequencyByReplacingZero {

  static int solve(int[] a) {
    int maxCount = 0;
    int maxIndex = -1;

    int prevZeroIndex = -1;
    int count = 0;

    for (int i = 0; i < a.length; i++) {
      if (a[i] == 1)
        count++;
      else {
        // reset count to 1 + number of ones to the left of current 0
        count = i - prevZeroIndex;

        // update prev_zero_index to current index
			  prevZeroIndex = i;
      }

      // update maximum count and index of 0 to be replaced if required
		  if (count > maxCount) {
        maxCount = count;
        maxIndex = prevZeroIndex;
      }
    }

    return maxIndex;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
    int index = solve(arr);
    System.out.println(index);

    arr = new int[]{1, 1, 1, 1, 0};
    index = solve(arr);
    System.out.println(index);
  }
}