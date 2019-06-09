package com.adhd.algo.sorting;

import java.util.Arrays;

public class FuckingMergeSort {
    static void sort(int[] a) {
        mergeSort(a, 0, a.length-1);
    }

    static void mergeSort(int[] a , int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid+1, right);
            merge(a, left, mid+1, right);
        }
    }

    static void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int k = 0;
        int i = left;
        int j = mid;
        
        while (i <= mid-1 && j <= right) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid-1) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }
        //Copy

        k = 0;
        for (i = left; i <= right; i++) {
            a[i] = temp[k++];
        }
    }
    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 3, 1, 2};
        sort(a);
        System.out.println(Arrays.toString(a));

        a = new int[]{1, 1, 1, 2, 2};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}