package com.adhd.algo.sorting;
/**
 * Given an array of integers, sort the array in ascending order using the Bubble Sort algorithm above. 
 * Once sorted, print the following three lines:
 */
public class FuckFoo {
    static void countSwaps(int[] a) {
        int totalSwaps = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {//TODO: Actually we can do n-1-i upper limit
                if (a[j] > a[j+1]) {
                    swap(a, j, j+1);
                    totalSwaps++;
                }
            }
        }

        System.out.println("Array is sorted in " + totalSwaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[n-1]);
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = new int[]{6,4,1};
        countSwaps(a);
    }
}