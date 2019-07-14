package com.adhd.misc;

public class Primality {
    static String primality(int n) {
        if (n < 2) {
			return "Not prime";
        }
        
        for (int i = 2 ; i <= n / 2; i++) {
            if (n % i == 0) return "Not prime";
        }
        return "Prime";
    }   
}