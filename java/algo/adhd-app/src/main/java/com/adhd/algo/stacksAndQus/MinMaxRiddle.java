package com.adhd.algo.stacksAndQus;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/min-max-riddle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=stacks-queues
 * 
 * 
 */
public class MinMaxRiddle {

    static long[] riddle(long[] arr) {
       long[] result = new long[arr.length];
       int window = 0;
       
        int resultIndex = 0;
       while (window < arr.length) {
            int k = 0;
            long[] minsOfEachWindow = new long[arr.length];
            for (int i = 0; i < arr.length; i++) {
                //find min from i to i+window
                long min = arr[i];
                int totalCOnsideredSize = 1;
                for (int j = i+1; j <= i+window && j < arr.length ; j++) {
                    min = Math.min(min, arr[j]);
                    totalCOnsideredSize++;
                }

                //Ignore case of considering only `long min = arr[i]`;
                if (totalCOnsideredSize == window + 1) {
                    minsOfEachWindow[k++] = min;
                }
            }

            //Find max in minsOfEachWindow
            long max = Arrays.stream(minsOfEachWindow).max().getAsLong();
            result[resultIndex++] = max;
            window++;
       }
       return result;
    }

    public static void main(String[] args) {
        long[] arr = new long[]{1, 2, 3, 5, 1, 13, 3};
        long[] r = riddle(arr);

        System.out.println(Arrays.toString(r));
    }
}