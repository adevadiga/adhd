package com.adhd.algo.arrays;

import java.util.Arrays;

/*
Instead of storing the actual values in the array, you store the difference between the current 
element and the previous element. So you add sum to a[p] showing that a[p] is greater than its 
previous element by sum. You subtract sum from a[q+1] to show that a[q+1] is less than a[q] by sum 
(since a[q] was the last element that was added to sum). By the end of all this, you have an array 
that shows the difference between every successive element. By adding all the positive differences, 
you get the value of the maximum element
*/
public class  ArrayManipulation {

    static long arrayManipulation(int n, int[][] queries) {
        long[] result = new long[n+1];
        long max = 0;
        for (int[] query : queries) {
            int low = query[0];
            int high = query[1];
            int value = query[2];

            for (int i = low; i <= high; i++) {
                result[i] += value;
                if (result[i] > max) {
                    max = result[i];
                }
            }
        }
        return max;
    }

    static long arrayManipulation2(int n, int[][] queries) {
        long[] result = new long[n+1];
        for (int[] query : queries) {
            int low = query[0];
            int high = query[1];
            int value = query[2];

            result[low] += value;
            if(high+1 < n) result[high+1] -= value; 
        }

        long temp = 0l;
        long max = 0l;
        for (long elem : result) {
            temp += elem;
            if (max < temp) {
                max = temp;
            }
        }
        System.out.println(Arrays.toString(result));

        return max;
    }

    public static void main(String[] args) {
        int[][] input = new int[3][];
        input[0] = new int[]{1, 2, 100};
        input[1] = new int[]{2, 5, 100};
        input[2] = new int[]{3, 4, 100};

        long res = arrayManipulation2(5, input);
        System.out.println(res);
    }
}