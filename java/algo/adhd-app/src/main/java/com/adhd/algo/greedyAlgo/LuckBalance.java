package com.adhd.algo.greedyAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/luck-balance/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 * 
 * Lena is preparing for an important coding competition that is preceded by a
 * number of sequential preliminary contests. She believes in "saving luck", and
 * wants to check her theory. Each contest is described by two integers, L[i]
 * and T[j] :
 * 
 * L[i] - is the amount of luck associated with a contest. If Lena wins the
 * contest, her luck balance will decrease by ; if she loses it, her luck
 * balance will increase by . T[i] = denotes the contest's importance rating.
 * It's equal to if the contest is important, and it's equal to if it's
 * unimportant. If Lena loses no more than K important contests, what is the
 * maximum amount of luck she can have after competing in all the preliminary
 * contests? This value may be negative.
 */

public class LuckBalance {

    //contests[0..][] -> contains  luck value
    //contents[][0..] -> contains importance
    static int luckBalance(int k, int[][] contests) {
        int impContestCount = 0;
        int totalLuckBalance = 0;
        List<Integer> importantLucks = new ArrayList<>();
        for (int[] cont : contests) {
            int luck = cont[0];
            int importanceValue = cont[1];

            if (importanceValue == 0) {
                totalLuckBalance += luck;
            } else {
                importantLucks.add(luck);
            }
        }

        Collections.sort(importantLucks, Collections.reverseOrder());
        for (int i = 0; i < importantLucks.size(); i++) {
			totalLuckBalance += importantLucks.get(i) * (i + 1 <= k ? 1 : -1);
		}
        return totalLuckBalance;
    }

    public static void main(String[] args) {
        int[][] contexts = new int[][]{
            {5, 1},
            {2, 1},
            {1, 1},
            {8, 1},
            {10, 0},
            {5, 0}
        };

        int val = luckBalance(3, contexts);
        System.out.println(val);
    }
}