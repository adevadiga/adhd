package com.adhd.recursionBackTrack;

/**
 * Fib(0) = 0
 * Fib(1) = 1
 * Fib(n) = Fib(n-1) + Fib(n-2)
 */
public class FibNumbers {

    public static int fibonacci(int n) {
       if (n == 0) return 0;
       if (n == 1) return 1;

       return fibonacci(n-1) + fibonacci(n-2);
    }

    static int fib(int n) {
        int[] fibs = new int[n+1];
        fibs[0] = 0;
        fibs[1] = 1;
        //Doesn't work. Need adjsuting of lower bound

        for (int i = 2 ; i <= n ; i++) {
            fibs[i] = fibs[i-1] + fibs[0];
        }

        return fibs[n];
    }

    static int fib2(int n) {
        int prevPrev = 0;
        int prev = 1;

        for (int i = 0; i < n-1; i++) {
            int next = prevPrev + prev;
            prevPrev = prev;
            prev = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        System.out.println(fib2(3));
    }
}