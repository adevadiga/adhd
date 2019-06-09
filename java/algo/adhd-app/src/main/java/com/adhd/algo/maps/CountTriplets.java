package com.adhd.algo.maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
ou are given an array and you need to find number of tripets of indices  (i,j,k) such that the
 elements at those indices are in geometric progression for a given common ratio r and i < j <k.
*/
public class CountTriplets {


    static long countTriplets2(List<Long> arr, long r) {
        Map<Long, Long> potential = new HashMap<>();
        Map<Long, Long> counter = new HashMap<>();
        long count  = 0;
        for (long a : arr) {
            long key = a / r;

            if (counter.containsKey(key) && a % r == 0) {
                count += counter.get(key);
            }
            if (potential.containsKey(key) && a % r == 0) {
                long c = potential.get(key);
                counter.put(a, counter.getOrDefault(a, 0L) + c);
            }

            potential.put(a, potential.getOrDefault(a, 0l) + 1);

            System.out.println("counter: " + counter);
            System.out.println("potential: " + potential);
            System.out.println("-----");
            
        }

        return count;
    }


    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Integer> multipleOfR = new HashMap<>();
        for (Long e : arr) {
            if (e % r == 0 || e == 1) {
                Integer count = multipleOfR.get(e);
                count = count != null ? count + 1 : 1;
                multipleOfR.put(e, count);
            }
        }

        System.out.println(multipleOfR);
        long result = 0l;
        for (long elem : arr) {
            Integer count1 = multipleOfR.get(elem*r);
            Integer count2 = multipleOfR.get(elem*r*r);
           
            if (count1 != null && count2 != null) {
                if (elem == 1 && r == 1) {
                    --count1;
                    --count2;
                }
                result += count1*count2;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        /*long[] a = new long[]{1l, 3l, 9l, 9l, 27l, 81l};
        List<Long> arr = new ArrayList<>();
        arr.add(1l);
        arr.add(2l);
        arr.add(2l);
        arr.add(4l);
        long l = countTriplets(arr, 2l);
        System.out.println(l);*/

        // List<Long> jj = Arrays.asList(1l, 3l, 9l, 9l, 27l, 81l);
        // long l = countTriplets(jj, 3l);
        // System.out.println(l);

        //List<Long> jj = Arrays.asList(1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l, 1l);
        List<Long> jj = Arrays.asList(3l, 9l, 27l);
        long l = countTriplets2(jj, 3l);
        System.out.println(l);
    }
}

/**
 * I have number x => Count the total numbers which are mutiple of x*r & x*r*r
 */