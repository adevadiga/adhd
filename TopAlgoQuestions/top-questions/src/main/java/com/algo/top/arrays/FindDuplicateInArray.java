package com.algo.top.arrays;
/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.
Input: [3,1,3,4,2] = 13 10
Output: 3
*/
public class FindDuplicateInArray {

  // 1. Sort - O(nlogn), space O(1) or O(n) if in place is not supported and we need to clone
  // 2. Set - O(n), space O(n)
  // 3. Floyd's Tortoise and Hare (Cycle Detection)

  static int findDuplicate(int[] nums) {
    // Find the intersection point of the two runners.
    int tortoise = nums[0];
    int hare = nums[0];

    do {
      tortoise = nums[tortoise];
      hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Find the "entrance" to the cycle.
    int ptr1 = nums[0];
    int ptr2 = tortoise;
    
    while (ptr1 != ptr2) {
      ptr1 = nums[ptr1];
      ptr2 = nums[ptr2];
    }

    return ptr1;
  }
}