package com.adhd.algo.stringsq;

import java.util.Arrays;

/**
 * Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.

Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams. Can you help her find this number?
 */
public class MakingAnagrams {
    static int makeAnagram(String a, String b) {
        int[] hitsInA = new int[126];
        int[] hitsInB = new int[126];

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        for (char ch : aChars) {
            hitsInA[ch]++;
        }

        for (char ch : bChars) {
            if (hitsInA[ch] > 0) {
                hitsInA[ch]--;
            } else {
                hitsInB[ch]++;
            }
        }
        int charAUnique = 0;
        for (int i = 0; i <hitsInA.length; i++) {
            if (hitsInA[i] > 0) {
                charAUnique += hitsInA[i];
            }
        }
        int charBUnique = 0;
        for (int i = 0; i <hitsInB.length; i++) {
            if (hitsInB[i] > 0) {
                charBUnique += hitsInB[i];
            }
        }
        return charAUnique + charBUnique;
    }
    public static void main(String[] args) {
        // int r = makeAnagram("cde", "abc");
        // System.out.println(r);

        int r = makeAnagram("jxwtrhvujlmrpdoqbisbwhmgpmeoke", "fcrxzwscanmligyxyvym");
        System.out.println(r);
    }
}