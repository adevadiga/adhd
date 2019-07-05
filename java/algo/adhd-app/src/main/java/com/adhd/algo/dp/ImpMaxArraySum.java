package com.adhd.algo.dp;
/**
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum. 
 * For example, given an array arr=[-2,1,3,4,5] we have the following possible subsets:
    Subset      Sum
    [-2, 3, 5]   6
    [-2, 3]      1
    [-2, -4]    -6
    [-2, 5]      3
    [1, -4]     -3
    [1, 5]       6
    [3, 5]       8
Our maximum subset sum is 8.
 */

 /**
  * Note: Generate all possible pairs of array
  */
  /**
   * Loop for all elements in arr[] and maintain two sums incl and excl where incl = Max sum including the previous element and excl = Max sum excluding the previous element.

Max sum excluding the current element will be max(incl, excl) and max sum including the current element will be excl + current element (Note that only excl is considered because elements cannot be adjacent).

At the end of the loop return max of incl and excl.

https://stackoverflow.com/questions/51450950/ruby-max-array-sum-from-non-adjacent-integers-in-array
   */
public class ImpMaxArraySum {

    //Doesn't work for negative numbers.
    static int maxSubsetSum(int[] arr) {
        int included = arr[0];
        int excluded = 0;

        for (int i = 1; i < arr.length; i++) {
            included = excluded + arr[i];

            excluded = Math.max(included, excluded);
        }

        return Math.max(included, excluded);
    }
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1 , 3, -4, 5};
        System.out.println(maxSubsetSum(arr));
    }
}