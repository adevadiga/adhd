package com.adhd.algo.arrays;

import java.util.Arrays;

public class LeftRotation {

    static int[] rotLeft(int[] a, int d) {
        int[] result = new int[a.length];
        int  k =0;
        for (int j = d; j < a.length; j++) {
            result[k++] = a[j];
        } 
        for (int j = 0; j <d; j++) {
            result[k++] = a[j];
        }
        return result;
    }

    static int[] rotLeft2(int[] a, int d) {
        for (int j = 0; j < a.length; j++) {
            int newIndex = (j+d) % a.length;
            return null;
        }
        return null;
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = rotLeft(a, 4);
        System.out.println(Arrays.toString(b));
    }
}