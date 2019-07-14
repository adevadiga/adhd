package com.adhd.recursionBackTrack;

public class RecursiveDigitSum {
    static int superDigit(String n, int k) {
        int value = superDigit(n);
        value *= k;
        return superDigit(String.valueOf(value));

    }

    static int superDigit(String n) {
        if (n.length() == 1) return Integer.parseInt(n);
        char[] digits = n.toCharArray();
        int sum = 0;
        for (char d : digits) {
            sum += d - '0';
        }

        return superDigit(String.valueOf(sum));
    }
    
    public static void main(String[] args) {
        System.out.println(superDigit("9875", 4));
        System.out.println(superDigit("148", 3));
        System.out.println(superDigit("9875", 4));
    }
}