package com.adhd.algo.greedyAlgo;

import java.util.Arrays;
import java.util.Collections;

/**
 * A group of friends want to buy a bouquet of flowers. 
 * The florist wants to maximize his number of new customers and the money he makes.
 *  To do this, he decides he'll multiply the price of each flower by the number of that customer's previously purchased flowers plus 1. 
 * The first flower will be original price, (0+1)*original price , the next will be (1+1)*original price and so on.
 * 
 * Given the size of the group of friends, the number of flowers they want to purchase and the original prices of the flowers,
 *  determine the minimum cost to purchase all of the flowers.
 */
public class GreedyFlorist {
    static int getMinimumCost(int k, int[] c) {
        int n = c.length;
        int minCost = 0;
        int tempCount = 0;
        int previousPurchases = 0;

        Arrays.sort(c);

        if(k >= n) {
            for(int i = 0; i < n; i++)
                minCost += c[i];
        } else {
            for(int i = n - 1; i >= 0; i--) {
                if (tempCount == k) {
                    tempCount = 0;
                    previousPurchases++;
                }

                minCost += (previousPurchases + 1) * c[i];
                tempCount++;
            }
        }

        return minCost;
    }
}