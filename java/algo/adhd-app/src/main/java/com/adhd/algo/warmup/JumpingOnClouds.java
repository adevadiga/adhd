package com.adhd.algo.warmup;

public class JumpingOnClouds {

    static int jumpingOnClouds(int[] c) {
        int step = -1;
        int n = c.length;
        for (int i = 0; i < n; i++, step++) {
            if (i<n-2 && c[i+2]==0) i++;
        }
        return step;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 1, 0, 0, 1, 0};
        int minJump = jumpingOnClouds(arr);
        System.out.println(minJump);

        arr = new int[]{0, 0, 0, 0, 1, 0};
        minJump = jumpingOnClouds(arr);
        System.out.println(minJump);

        
        arr = new int[]{0, 0, 0, 0, 1, 0};
        minJump = jumpingOnClouds(arr);
        System.out.println(minJump);


    }
}