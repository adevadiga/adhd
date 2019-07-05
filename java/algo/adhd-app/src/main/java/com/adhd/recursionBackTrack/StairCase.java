package com.adhd.recursionBackTrack;

import java.util.Arrays;

/**
 * Davis has a number of staircases in his house and he likes to climb each staircase 1, 2, or 3  steps at a time.
 * Being a very precocious child, he wonders how many ways there are to reach the top of the staircase
 * 
 * stepPerms(n) = [stepPerms(n-1) + 1] + [stepPerms(n-2) + 2] + [stepPerms(n-3) + 3]
 */
public class StairCase {

    static int stepPerms(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        
        return stepPerms(n-1) + stepPerms(n-2) + stepPerms(n-3);
    }

    static int countWays(int n) {
        int[] mem = new int[n+1];
        Arrays.fill(mem, -1);
        return countWays(n, mem);
    }

    static int countWays(int n, int[] mem) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (mem[n] > -1) {
            return mem[n];
        }

        mem[n] = countWays(n-1, mem) + countWays(n-2, mem) + countWays(n-3, mem);
        return mem[n];
    }

    public static void main(String[] args) {
        System.out.println(stepPerms(1));
        System.out.println(stepPerms(3));
        System.out.println(stepPerms(7));

        System.out.println(countWays(1));
        System.out.println(countWays(3));
        System.out.println(countWays(7));
    }
}