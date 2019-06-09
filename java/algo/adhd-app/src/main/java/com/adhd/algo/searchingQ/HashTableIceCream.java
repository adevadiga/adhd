package com.adhd.algo.searchingQ;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool their money to buy ice cream. On any given day, the parlor offers a line of flavors.
 *  Each flavor has a cost associated with it.

Given the value of money and the cost of each flavor for t trips to the Ice Cream Parlor,
 help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money during each visit. 
 ID numbers are the 1- based index number associated with a . For each trip to the parlor, print the ID numbers for the two types of ice cream
  that Sunny and Johnny purchase as two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.

  For example, there are  n=5 flavors having cost = [2, 1, 3, 5, 6 ]. 
  Together they have money=5 to spend. They would purchase flavor ID's 1 and 3 for a cost of 2 + 3 = 5. Use 1 based indexing for your response.
 */
public class HashTableIceCream {

    static void whatFlavors(int[] cost, int money) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < cost.length; i++) {
            int complement = money - cost[i];
            if (map.containsKey(complement)) {
                int index1 = i+1;
                int index2 = map.get(complement)+1;
                String answer = String.format("%d %d", index1 < index2 ? index1 : index2, index1 > index2 ? index1 : index2);
                System.out.println(answer);
                return;
            }
            map.put(cost[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String[] args) {
        int[] cost = new int[]{1, 4, 5, 3, 2};
        whatFlavors(cost, 4);

        cost = new int[]{2, 2, 4, 3};
        whatFlavors(cost, 4);
    }
}