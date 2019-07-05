package com.adhd.algo.searchingQ;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Usually, a great many problems related to "subarray computation" could be
 * solved with prefix array, which saves time for repeating computation.
 * 
 * 
 * https://www.hackerrank.com/challenges/maximum-subarray-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 * 
 * We define the following:
 * 
 * A subarray of array a of length n is a contiguous segment from a[i] through
 * a[j] where 0<=i<=j<n. The sum of an array is the sum of its elements. Given
 * an element array of integers, a, and an integer, m, determine the maximum
 * value of the sum of any of its subarrays modulo m. For example, Assume and .
 * The following table lists all subarrays and their moduli:
 * 
 * 
 */
public class MaxSubArraySums {

    static long maximumSum2(long[] a, long m) {
        long[] sums = buildSums(a, m);

		long result = Arrays.stream(sums).max().getAsLong();
		NavigableSet<Long> sortedSums = new TreeSet<>();
		for (long sum : sums) {
			Long higher = sortedSums.higher(sum);
			if (higher != null) {
				result = Math.max(result, addMod(sum, -higher, m));
			}

			sortedSums.add(sum);
		}
		return result;
    }
    static long[] buildSums(long[] a, long m) {
		long[] sums = new long[a.length];
		long sum = 0;
		for (int i = 0; i < sums.length; i++) {
			sum = addMod(sum, a[i], m);
			sums[i] = sum;
		}
		return sums;
	}

	static long addMod(long x, long y, long modulus) {
		return ((x + y) % modulus + modulus) % modulus;
    }
    
    static long maximumSum(long[] a, long m) {
        /*long maxSumModules = 0;

        for (int i = 0; i < a.length-1; i++) {
            long sum = a[i];
            for (int j = i+1; j < a.length; j++) {
                sum += a[j];
                maxSumModules = Math.max(maxSumModules, sum % m);
            }
        }

        return maxSumModules;*/
        long maxEndingHere = 0;
        //long maxSoFar = 0;
        long maxSumModules = 0;

        for (long value : a) {
            maxEndingHere = Math.max(maxEndingHere, maxEndingHere + value);
            //maxSoFar = Math.max(maxEndingHere, maxSoFar);

            System.out.println(maxEndingHere);
            maxSumModules = Math.max(maxSumModules, maxEndingHere % m);
        }
        return maxSumModules;
    }
    public static void main(String[] args) {
        long[] a = new long[]{1, 5, 9};
        long r = maximumSum(a, 5);
        System.out.println(r);
    }
}