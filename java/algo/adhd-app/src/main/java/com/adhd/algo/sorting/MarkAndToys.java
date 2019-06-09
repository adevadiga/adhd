package com.adhd.algo.sorting;

import java.util.Arrays;

public class MarkAndToys {

    static int maximumToys(int[] prices, int k) {
        int maxPriceOfToys = 0;
        int count = 0;
        Arrays.sort(prices);

        int i = 0;
        while (maxPriceOfToys < k) {
            if (maxPriceOfToys + prices[i] > k) {
               break;
            } 
            maxPriceOfToys += prices[i];
            count++;
            i++;
        }

        return count;
    }
    public static void main(String[] args) {
        int[] prices = new int[]{1, 12, 5, 111, 200, 1000, 10};
        int total = maximumToys(prices, 50);
        System.out.println(total);
    }
}