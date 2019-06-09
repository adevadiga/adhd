package com.adhd.algo.maps;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*Two strings are anagrams of each other if the letters of one string can be rearranged 
to form the other string. 
Given a string, find the number of pairs of substrings of the string that are
 anagrams of each other.

 For example s = mom, the list of all anagrammatic pairs [m,m] [mo,om] is  at positions respectively.

*/
public class Anagrams {
    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        int result = 0;
        Map<String, Integer> countMap = new HashMap<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                
                String key = s.substring(i, j+1);
                System.out.println(key);
                char[] keys = key.toCharArray();
                Arrays.sort(keys);
                
                key = new String(keys);

                Integer count = countMap.get(key);
                count = count == null ? 1 : count + 1 ;

                countMap.put(key, count);
            }
        }
        System.out.println(countMap);
        for(Integer count : countMap.values()) {
            result += (count * (count-1)) / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "kkkk";
        sherlockAndAnagrams(s);
    }
}


/*
 * ab  => No
 * 
 * aba => [a,a] [ab, ba]
 * abca => [a,a]
 * If you have 4 chars, then
 *          =>There are 4 one letter words
 *          => 3 two letter
 *          => 2 three letter
 *          => 1 four letter
 * 
 * 
 */