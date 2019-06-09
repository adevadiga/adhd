package com.adhd.algo.sorting;

import java.util.Arrays;

/**
 * HackerLand National Bank has a simple policy for warning clients about possible fraudulent account activity. 
 * If the amount spent by a client on a particular day is greater than or equal to 2X
 *  the client's median spending for a trailing number of days, they send the client
 *  a notification about potential fraud. 
 * The bank doesn't send the client any notifications until they have at least that trailing number of prior days' transaction data.

Given the number of trailing days d and a client's total daily expenditures for a period of n  days, find and print the number of times the client will receive a notification over all n days.

For example, d=3 and expendiure=[10,20,30,40,50] . On the first three days, they just collect spending data. 
At day 4, we have trailing expenditures of [10 20 30]. The median is 20 and the day's expenditure is 40. 
Because 40 >= 2*20, there will be a notice. The next day, our trailing expenditures are  [20 30 40] and the expenditures are 50 . 
This is less than 2*30 so no notice will be sent. Over the period, there was one notice sent


What is Median:
The median of a list of numbers can be found by arranging all the numbers from smallest 
to greatest. If there is an odd number of numbers, the middle one is picked. If there is an even number of numbers,
 median is then defined to be the average of the two middle values

 */
public class MovingMedian {

    static int solve(int[] expenditures, int d) {
        int[] counts = new int[201];
		for (int i = 0; i < d; i++) {
			counts[expenditures[i]]++;
        }
        System.out.println(Arrays.toString(counts));
        int result = 0;
		for (int i = d; i < expenditures.length; i++) {
			int lower = 0;
			int leftNum = 0;
			while ((leftNum + counts[lower]) * 2 <= d) {
				leftNum += counts[lower];
                lower++;
                
                System.out.println("leftNum: "+ leftNum);
                System.out.println("lower: "+ lower);
            }
            System.out.println("----leftNum: "+ leftNum);
            System.out.println("----lower: " + lower);

            counts[expenditures[i - d]]--;
			counts[expenditures[i]]++;
        }
        
        
        return 1;
    }

    static int median(int d, int[] count) {
        int[] a = Arrays.copyOf(count, count.length);
        int r = d / 2;
        if (d % 2 == 1) {
            r++;
        }
        int res = 0;
        boolean odd = d % 2 == 1;
        for (int k = 0; k <= 200; k++) {
            while (r > 0 && a[k] > 0) {
                a[k]--;
                r--;
            }
            if (r == 0) {
                res += k;
                if (d % 2 == 0) {
                    d--;
                    r++;
                    if (a[k] > 0) {
                        return 2 * k;
                    }
                } else {
                    break;
                }
            }
        }
        return res * (odd ? 2 : 1);
    }

    static int activityNotifications(int[] expenditure, int d) {
        int n = expenditure.length;
        int[] cnt = new int[201];

        for (int i = 0; i < d; i++) {
            cnt[expenditure[i]]++;
        }
        int res = 0;
        for (int i = d; i < n; i++) {
            int m = median(d, cnt);

            if (expenditure[i] >= m) {
                res++;
            }
            cnt[expenditure[i - d]]--;
            cnt[expenditure[i]]++;
        }

        return 0;
    }
    public static void main(String[] args) {
        
        int[] e = new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5};
        solve(e, 5);
    }
}