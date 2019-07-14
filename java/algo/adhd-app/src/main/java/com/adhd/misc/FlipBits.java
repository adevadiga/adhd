package com.adhd.misc;

public class FlipBits {
    static long flippingBits(long n) {
        long mask = (1L << 32) - 1;
        return n ^ mask;
    }
}