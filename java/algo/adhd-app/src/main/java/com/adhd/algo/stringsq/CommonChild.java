package com.adhd.algo.stringsq;

import java.util.Arrays;

/*
A string is said to be a child of a another string if it can be formed by deleting 0 
or more characters from the other string. Given two strings of equal length, what's the
 longest string that can be constructed such that it is a child of both?

For example, ABCD and ABDC have two children with maximum length 3,
 ABC and ABD. They can be formed by eliminating either the D or C from both strings.
  Note that we will not consider ABCD as a common child because we can't rearrange characters and ABCD != ABDC.

  https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
*/
public class CommonChild {

    static int commonChild(String s1, String s2) {
        /*char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int[][] sequence = new int[s1Chars.length][s2Chars.length];
        
        for (int i = 1; i < s1Chars.length; i++) {
            for (int j = 1; j < s2Chars.length; j++) {
                if (s1Chars[i] == s2Chars[j]) {
                    sequence[i][j] = sequence[i-1][j-1] + 1;
                } else {
                    sequence[i][j] = Math.max(sequence[i][j-1], sequence[i-1][j]);
                }

                System.out.println(sequence[i][j]);
            }
        }
        return sequence[s1Chars.length-1][s2Chars.length-1];*/
        int length1 = s1.length();
		int length2 = s2.length();
		int[][] commonLengths = new int[length1 + 1][length2 + 1];
		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					commonLengths[i][j] = commonLengths[i - 1][j - 1] + 1;
				} else {
					commonLengths[i][j] = Math.max(commonLengths[i - 1][j], commonLengths[i][j - 1]);
                }
                System.out.println(commonLengths[i][j]);
			}
        }
        //System.out.println(Arrays.toString(commonLengths[1]));
		return commonLengths[length1][length2];
    }
    public static void main(String[] args) {
        // System.out.println(commonChild("HARRY", "SALLY"));
        // System.out.println(commonChild("AA", "BB"));

        //System.out.println(commonChild("SHINCHAN", "NOHARAAA"));
        System.out.println(commonChild("AA", "AB"));
        
    }

}