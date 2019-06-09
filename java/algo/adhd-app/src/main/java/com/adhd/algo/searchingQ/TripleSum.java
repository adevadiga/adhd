package com.adhd.algo.searchingQ;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/triple-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 * 
 * Triple Sum. Given 3 array find triplets such that 
 * p <= q & q>=r
 * 
 * For each element of array-b find out how many elements of a & c are less than it
 */
public class TripleSum {

    static long triplets(int[] a, int[] b, int[] c) {
        a = Arrays.stream(a).sorted().distinct().toArray();
        b = Arrays.stream(b).sorted().distinct().toArray();
        c = Arrays.stream(c).sorted().distinct().toArray();

        long triplets = 0;
        int x = 0, y = 0, z = 0;
        while ( y < b.length) {
            while (x < a.length && a[x] <= b[y]) {
                x++;
            }
            while (z < c.length && c[z] <= b[y]) {
                z++;
            }

            triplets += ((long)x) * z;

            y++;
        }

        return triplets;
    }

    public static void main(String[] args) {
        
    }
}