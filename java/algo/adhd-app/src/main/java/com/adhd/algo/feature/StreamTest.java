package com.adhd.algo.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    private String mapper(String s) {
        return null;
    }
    public void test() {
        List<String> ls = new ArrayList<>();
        ls.add("a");
        ls.add("b");

        List<String> lsNew = ls.stream().map(s -> mapper(s)).collect(Collectors.toList());
        System.out.println(lsNew);

        for (String k : lsNew) {
            System.out.println(k);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hurry");
        new StreamTest().test();
    }
}