package com.adhd.linkedlist;

/**
 * A linked list is said to contain a cycle if any node 
 * is visited more than once while traversing the list. 
 * 
 * For example, in the following graph there is a cycle formed when node  points back to node .
 */

class Node {
    int data;
    Node next;
}
public class DetectACycle {

    boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast. next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return false;
        }

        return true;

        /*slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;*/

    }
}