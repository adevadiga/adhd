package com.adhd.algo.warmup;

public class RepeatedString {

    static long repeated(String s, long n) {
        char[] sChars = s.toCharArray();
        int numberOfLetterAInString = 0;
        for (char ch: sChars) {
            if(ch == 'a' || ch == 'A')
                numberOfLetterAInString++;
        }

        long times = n / sChars.length;
        long remained = n % sChars.length;
        int numberOfCharsInRemained = 0;
        for (int i = 0; i < remained; i++) {
            if('a' == sChars[i] || 'A' == sChars[i])
                numberOfCharsInRemained++;
        }

        long result = times * numberOfLetterAInString + numberOfCharsInRemained;
        return result;
    }

    public static void main(String[] args) {

        long res = repeated("aba", 10);
        System.out.println(res);

        res = repeated("a", 1000000000000L);
        System.out.println(res);
    }
}