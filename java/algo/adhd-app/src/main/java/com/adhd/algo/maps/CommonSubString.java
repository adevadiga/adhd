package com.adhd.algo.maps;

public class CommonSubString {

    static String twoStrings(String s1, String s2) {
        char[] hits = new char[125];
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        for (char ch : s1Chars) {
            hits[ch]++;
        }

        for (char ch : s2Chars) {
            if (hits[ch] != 0) {
                System.out.println("Yes");
                return "YES";
            }
        }

        return "NO";
    }
}