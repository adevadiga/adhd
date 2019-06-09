package com.adhd.algo.stringsq;

public class AlternatingChars {

    static int alternatingCharacters(String s) {
        char[] sChars = s.toCharArray();
        StringBuilder b = new StringBuilder();
        int count = 0;
        b.append(sChars[0]);

        for (int i = 1; i < sChars.length; i++){
            if (sChars[i] == sChars[i-1]) {
                count++;
            } else {
                b.append(sChars[i]);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int c = alternatingCharacters("AAABBB");
        System.out.println(c);
    }
}