package com.adhd.algo.arrays;

/*You are given an unordered array consisting of consecutive integers  [1, 2, 3, ..., n]
 without any duplicates. You are allowed to swap any two elements. 
You need to find the minimum number of swaps required to sort the array in ascending order.*/
public class MinimumSwaps {

    static int minimumSwaps(int[] arr) {
        int rightPointer = arr.length-1;
        int count = 0;
        int minimumSwaps = 0;

        while (count < arr.length) {
            int arrValue = count + 1;

            if (arr[count] == arrValue) {
                count++;
                continue;
            }

            while (arrValue != arr[rightPointer]) {
                rightPointer--;
            }
            if (count != rightPointer) {
                swap(arr, count, rightPointer);
                minimumSwaps++;
            }

            count++;
            rightPointer = arr.length-1;
        }

        return minimumSwaps;
    }

    static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 1, 5};
        int min = minimumSwaps(a);
        System.out.println(min);
    }
}