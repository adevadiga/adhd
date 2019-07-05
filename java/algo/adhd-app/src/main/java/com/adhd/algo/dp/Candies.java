package com.adhd.algo.dp;

import java.util.stream.IntStream;

public class Candies {
    static long candies(int n, int[] arr) {
        int[] lefts = new int[arr.length];
        int previous = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > previous){
                lefts[i] = lefts[i-1] + 1;
            } else {
                lefts[i] = 1;
            }

            previous = arr[i];
        }

        int[] rights = new int[arr.length];
        previous = Integer.MAX_VALUE;
        for (int j = n-1; j >= 0; j--) {
            if (arr[j] > previous) {
                rights[j] = rights[j + 1] + 1;
            } else {
                rights[j] = 1;
            }

            previous = arr[j];
        }

        return IntStream.range(0, n).mapToLong(i -> Math.max(lefts[i], rights[i])).sum();

    }
    public static void main(String[] args) {
        
    }
}