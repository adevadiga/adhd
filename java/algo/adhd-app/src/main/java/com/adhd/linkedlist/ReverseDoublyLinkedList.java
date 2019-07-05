package com.adhd.linkedlist;

/**
 * You’re given the pointer to the head node of a doubly linked list. Reverse the order of the nodes in the list. 
 * The head node might be NULL to indicate that the list is empty. Change the next and prev pointers of all the
 *  nodes so that the direction of the list is reversed.
 *  Return a reference to the head node of the reversed list.
 */
public class ReverseDoublyLinkedList {

    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        if (head == null || head.next == null) return head;

        DoublyLinkedListNode rest = reverse(head.next);

        head.prev = head.next;
        head.next.next = head;
        head.next = null;

        return rest;
    }

    static DoublyLinkedListNode reverse2(DoublyLinkedListNode first) {
        if (first == null) return null;

        DoublyLinkedListNode t = first.next;
        first.next = first.prev;
        first.prev = t;

        if(first.prev == null) return first;

        return reverse2(first.prev);
    }
}