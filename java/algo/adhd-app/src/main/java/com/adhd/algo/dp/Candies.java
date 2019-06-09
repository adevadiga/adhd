package com.adhd.algo.dp;

public class Candies {
    static long candies(int n, int[] arr) {
        if (n == 0 || n == 1) return (long)n;

        int lastGivenCandiCount = 0;
        for (int i = 0; i < n; i++) {
            int currentRank = arr[i];
            int nextRank =  arr[i+1];

            // if(currentRank < nextRank) {
            //     lastGivenCandiCount += 2;//TODO
            // } else if(currentRank > nextRank) {
            //     lastGivenCandiCount += 1;
            // } else {
            //     lastGivenCandiCount = 1;
            // }
        }
        /*int[] candieCount = new int[n];

        int lastGivenCandiCount = 1;
        int k = 0;
        candieCount[k++] = lastGivenCandiCount;

        for (int i = 1; i < n; i++) {
            int currentRank = arr[i];
            int nextRank = i+1 < n-1 ? 0 : arr[i+1];

            if (currentRank < nextRank) {

            }
        }*/
return -1;
    }
}