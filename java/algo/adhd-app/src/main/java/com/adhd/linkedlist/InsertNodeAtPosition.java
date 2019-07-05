package com.adhd.linkedlist;

/**
 * You’re given the pointer to the head node of a linked list, an integer to add
 *  to the list and the position at which the integer must be inserted. 
 * Create a new node with the given integer, insert this node at the desired position and return the head node.
 * 
 * A position of 0 indicates head, a position of 1 indicates one node away from the head and so on. 
 * The head pointer given may be null meaning that the initial list is empty.
 */

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;
    SinglyLinkedListNode(int data) {
        this.data = data;
    }
}
public class InsertNodeAtPosition {

    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        SinglyLinkedListNode nodeToInsert = new SinglyLinkedListNode(data);
        SinglyLinkedListNode node = head;
        SinglyLinkedListNode previous = null;

        while (position > 0 ) {
            previous = node;
            node = node.next;
            position--;
        }

        if (previous != null) {
            previous.next = nodeToInsert;
        }
        nodeToInsert.next = node;

        return head;
    }
}