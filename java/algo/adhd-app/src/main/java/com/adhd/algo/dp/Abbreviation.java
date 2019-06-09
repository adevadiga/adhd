package com.adhd.algo.dp;

public class Abbreviation {
    public static String canDo(String x, String y) {
        StringBuilder trimmedX = new StringBuilder();
        if (x == null || y == null) return "NO";
        if (x.equals(y)) return "YES";

        //assume - Y is all CAPS
        //If X contain a smallcase-char which is not in Y => remove x
        //if X contain a uppercase-char which is not in Y => False 
        //If X contain a smallcase-char which exists in Y => capitalize x
        //If X contain a uppercase-char which exists in Y => retain it

        char[] charsInX = x.toCharArray();
        String yUpperCase = y.toUpperCase();
        //char[] charsInY = y.toCharArray();

        for (char charX : charsInX) {
            boolean isSmallCase = Character.isLowerCase(charX);
            boolean isFoundInY = yUpperCase.indexOf(Character.toUpperCase(charX)) >= 0;

            if (isSmallCase && !isFoundInY) {
                ;//Remove
            }

            if (!isSmallCase && !isFoundInY) {
                return "NO";
            }

            if (isSmallCase && isFoundInY) {
                trimmedX.append(Character.toUpperCase(charX));
            }
    
            if (!isSmallCase && isFoundInY) {
                trimmedX.append(charX);
            }

        }
                
        return trimmedX.toString().equals(y) ? "YES" : "NO";
    }

    public static void main(String[] args) {
        String a = "AbcDE";
        String b = "ABDE";
        System.out.println(canDo(a, b));

        a = "AbcDE";
        b = "AFDE";
        System.out.println(canDo(a, b));
    }
}