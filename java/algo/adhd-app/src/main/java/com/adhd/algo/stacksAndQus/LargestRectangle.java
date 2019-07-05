package com.adhd.algo.stacksAndQus;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/largest-rectangle/problem
 * 
 * This problem is a classic one in the context of Algorithmic Programming Contests. 
 * It asks for the area of the largest rectangle which can be segmented from a given Histogram with bars of different heights but
 *  fixed width.

There are many nice approaches to solving this problem. We will review two of those. For simplicity, consider each bar
 has a unit width. We will use a notion of a Rectangle for each bar and two indices,  j and k.


 Let's think about the most naive approach first. This will help us to understand a more efficient solution.

What we can do: for each bar, we can find the rectangle with the height of it and also containing it.

To do this for a bar, let's say ith bar, we go to left from i and stop at the first bar which is smaller than the ith bar.
 Say we have stopped at jth position.
Then, we move to the right of i and stop at the bar which is smaller than ith bar. Say we have stopped at k^th bar.
The wiidth will be = k - j -1
 */

public class LargestRectangle {
    //O(n)^2
    static long largestRectangle(int[] h) {
        long maxWidth = 0;
        int n = h.length;
        for (int i = 0; i < n; i++) {
            int currentBarHeight = h[i];

            int j = i-1;
            while (j >= 0 && h[j] >= currentBarHeight) {
                j--;
            }

            int k = i + 1;
            while (k <= n-1 && h[k] >= currentBarHeight) {
                k++;
            }

            int currentWidth =  k - j -1;

            maxWidth = Math.max(maxWidth, currentWidth * currentBarHeight);
        }

        return maxWidth;
    }


    /**
     * The objective is same, i.e. for each bar, we will calculate the same rectangle we did in the naive approach but of course efficiently.
     * ust restating: to find the width of the desired rectangle for any ith bar, 
     * we need the position of first smaller element left to i (was denoted by j in naive approach) and the position of the first smaller element right to i(denoted by k).
     * @param h
     * @return
     */
    static long largestRectangle2(int[] h) {
        return 0L;
    }

    public static void main(String[] args) {
        int[] h = new int[]{1, 2, 3, 4, 5};
        System.out.println(largestRectangle(h));
    }
}