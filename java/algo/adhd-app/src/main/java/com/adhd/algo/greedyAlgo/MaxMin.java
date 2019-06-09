package com.adhd.algo.greedyAlgo;

import java.util.Arrays;

public class MaxMin {
    static int maxMin(int k, int[] arr) {
        int result_MinUnfairness = Integer.MAX_VALUE;
        Arrays.sort(arr);
        //return IntStream.range(0, arr.length - k + 1).map(i -> arr[i + k - 1] - arr[i]).min().getAsInt();

        
        for (int i = 0; i + (k-1) < arr.length; i++) {
            if ((arr[i+(k-1)] - arr[i]) < result_MinUnfairness) {
                result_MinUnfairness = arr[i+(k-1)] - arr[i];
            }
        }
        return result_MinUnfairness;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
            10,
            100,
            300,
            200,
            1000,
            20,
            30
        };
        //int result = maxMin(3, arr);
        //System.out.println(result);

        arr = new int[] {
            100,
            200,
            300,
            350,
            400,
            401,
            402
        };
        int result = maxMin(3, arr);
        System.out.println(result);
    }
}