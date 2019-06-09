package com.adhd.algo.searchingQ;

public class MinTimeRequired {
    static long minTime(long[] machines, long goal) {
        long itemCount = 0;
        long noOfDays = 0;

        while (itemCount < goal) {
            noOfDays++;

            for (long daysNeededForMachine : machines) {
                if ((noOfDays == 1 && daysNeededForMachine == 1) 
                    || (noOfDays != 1 && noOfDays % daysNeededForMachine == 0)) {
                    itemCount++;
                }
            }
        }

        return noOfDays;
    }

    public static void main(String[] args) {
        long[] machines = new long[]{1, 3, 4};
        long minDaysNeeded = minTime(machines, 10);
        System.out.println(minDaysNeeded);
    }
}