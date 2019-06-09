package com.adhd.algo.greedyAlgo;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Consider an array of integers, arr = [0...n] . We define the absolute difference between two elements,  
 * a[i] and a[j] (where ) i != j, to be the absolute value of a[i] - a[j] .
 * 
 * Given an array of integers, find and print the minimum absolute difference between any two elements in the array. 
 */
public class MinAbsoluteDiff {
    static int minimumAbsoluteDifference(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i]-arr[j]) < minDiff) {
                    minDiff = Math.abs(arr[i]-arr[j]);
                }
            }
        }
        return minDiff;
    }

    //Accepted solution
    static int minimumAbsoluteDifference2(int[] arr) {
        Arrays.sort(arr);
        int minDiff = arr[1] - arr[0];
        for (int i = 1; i < arr.length-1; i++) {
           if (Math.abs(arr[i] - arr[i+1]) < minDiff) {
                minDiff = Math.abs(arr[i] - arr[i+1]);
           }
        }
        return minDiff;
    }
    static int minimumAbsoluteDifference3(int[] a) {
        Arrays.sort(a);
		return IntStream.range(0, a.length - 1).map(i -> a[i + 1] - a[i]).min().getAsInt();
    }
    public static void main(String[] args) {
        int[] arr = new int[]{3, -7, 0};
        System.out.println(minimumAbsoluteDifference2(arr));

        arr = new int[]{-59, -36, -13, 1, -53, -92, -2, -96, -54, 75};
        System.out.println(minimumAbsoluteDifference2(arr));
    }
}