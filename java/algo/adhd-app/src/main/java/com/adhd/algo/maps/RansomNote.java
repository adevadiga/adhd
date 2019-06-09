package com.adhd.algo.maps;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magazineMap = new HashMap<>();

        for (String m : magazine) {
            Integer count = magazineMap.get(m);
            count = count != null ? count + 1 : 1;

            magazineMap.put(m, count);
        }

        int found = 0;
        for (String s : note) {
            Integer count = magazineMap.get(s);
            if (count == null || count == 0) {
                System.out.println("No");
                return;
            }

            magazineMap.put(s, count-1);
            found++;
        }

        if (found == note.length) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void main(String[] args) {
        String[] m = new String[]{"give","me","one","grand","today","night"};
        String[] n= new String[]{"give","one","grand","today"};

        checkMagazine(m,n);
    }
}