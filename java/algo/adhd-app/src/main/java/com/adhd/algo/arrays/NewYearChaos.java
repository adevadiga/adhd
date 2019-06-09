package com.adhd.algo.arrays;

public class NewYearChaos {

   // int[] q = {1, 2, 5, 3, 7, 8, 6, 4};
   static void minimumBribes4(int[] q) {
       int bribes = 0;
       for (int i = q.length-1; i >=0 ; i--) {
           int currentPos = i+1;
           int origPos = q[i];
           if (origPos - currentPos > 2) {
                System.out.println("Too chaotic");
                return;
           }
            for (int j = origPos - 1; j >= i-1; j--) {
                if(q[j] > origPos)
                    bribes += 1;
            }
       }
       System.out.println(bribes);
   }
    static void minimumBribes3(int[] q) {
        int minBribes = 0;
        int end = q.length-1;
        for (int i = q.length-1; i >=0 ; i--) {
            int actualPos = q[i]; 

            int j = i+1;
            int count = 0;
            while (j <= end) {
                if (q[j] < actualPos) {
                    count++;
                }
                j++;
            }
            //System.out.println(count);
            if (count  > 2) {
                System.out.println("Too chaotic");
                return;
            }
            minBribes += count;
        }

        System.out.println(minBribes);
    }
    static void minimumBribes2(int[] q) {
        int minBribes = 0;
        int minFromRight = Integer.MAX_VALUE;

        for (int i = q.length-1; i >=0 ; i--) {
            int position = i + 1; //8
            int actualPos = q[i]; //4
            if (actualPos < position) {
                //this guy has shifted back.
                //Does he has pushed someone back as well???? - that means somebody behind position
                //with value less than him
                int start = i+1;
                int end = q.length-1;
                int count = 0;
                while (start <= end) {
                    int val = q[start];
                    if (val < actualPos) {
                        count++;
                    }
                    start++;
                }

                System.out.println(count);
                if (count  > 2) {
                    System.out.println("Too chaotic");
                    return;
                }

                minBribes += count;

            } else {
                int diff = actualPos - position;
                if (diff > 2) {
                    System.out.println("Too chaotic");
                    return;
                }
                if (diff > 0) {
                    minBribes += diff;
                }
            }

        }
        System.out.println(minBribes);
       
    }

    static void minimumBribes(int[] q) {
        int minBribes = 0;
        int count = 0;

        while (count < q.length) {
            int expectedRank = count + 1;
            int actualRank = q[count];
            int diff = actualRank - expectedRank;
            if (diff > 2) {
                System.out.println("Too chaotic");
                return;
            }
            //If negative then its pushed down. Don't count
            System.out.println(diff);
            if (diff > 0) {
                minBribes += diff;
            }
            count++;
        }

        System.out.println(minBribes);

    }

    public static void main(String[] args) {
        // int[] q = {2, 1, 5, 3, 4};
        // minimumBribes(q);

        
        int[] q = {1, 2, 5, 3, 7, 8, 6, 4};
        minimumBribes4(q);
    }


}