package com.adhd.algo.sorting;

import java.util.Arrays;

public class MergeSortCountInversion {

    static long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    static long mergeSort(int[] arr, int left, int right) {
        long invCount = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            invCount = mergeSort(arr, left, mid);
            invCount += mergeSort(arr, mid + 1, right);
            invCount += merge(arr, left, mid + 1, right);
        }

        return invCount;
    }

    static long merge(int[] arr, int left, int mid, int right) {
        long invCount = 0;
        int[] temp = new int[right - left + 1];
        int k = 0;

        int  i = left;
        int j = mid;

        while ( i <= mid-1 && j <= right) {
            if (arr[i] <= arr[j]) {//= is important to reduce inversion.
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i);
            }
        }

        while (i <= mid-1) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        k = 0;
        for (i = left; i <=right; i++) {
            arr[i] = temp[k++];
        }
        return invCount;
    }
    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 3, 1, 2};
        long r = countInversions(a);
        System.out.println(r);
        System.out.println(Arrays.toString(a));
    }
}