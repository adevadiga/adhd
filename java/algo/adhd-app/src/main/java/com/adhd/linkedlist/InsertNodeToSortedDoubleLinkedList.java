package com.adhd.linkedlist;

/**
 * Inserting a Node Into a Sorted Doubly Linked List

Given a reference to the head of a doubly-linked list and an integer, data, create a new DoublyLinkedListNode object having data value  
and insert it into a sorted linked list while maintaining the sort.
 */

class DoublyLinkedListNode {
    int data;
    DoublyLinkedListNode next;
    DoublyLinkedListNode prev;

    DoublyLinkedListNode(int data) {
        this.data = data;
    }
};

public class InsertNodeToSortedDoubleLinkedList {

    static DoublyLinkedListNode sortedInsertCleaner(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode tempHead = new DoublyLinkedListNode(head.data);
        tempHead.next = head;

        DoublyLinkedListNode prev = tempHead;
        while (prev.next != null && prev.next.data < data) {
            prev = prev.next;
        }
        DoublyLinkedListNode node = new DoublyLinkedListNode(data);
        node.data = data;
        node.next = prev.next;
        if (prev.next != null) {
            prev.next.prev = node;
        }
        node.prev = prev;
        prev.next = node;

        return tempHead.next;
    }

    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode nodeToInsert = new DoublyLinkedListNode(data);
        DoublyLinkedListNode node = head;

        if (head == null) {
            return nodeToInsert;
        }

        DoublyLinkedListNode prev = null;
        while (node != null && node.data < data) {
            prev = node;
            node = node.next;
        }

        if (prev == null) {
            nodeToInsert.next = node;
            node.prev = nodeToInsert;
            return nodeToInsert;
        }

        nodeToInsert.next = prev.next;
        nodeToInsert.prev = prev;

        if (prev.next != null) {
            prev.next.prev = nodeToInsert;
        }
        prev.next = nodeToInsert;

        return head;
    }

    public static void main(String[] args) {
        DoublyLinkedListNode head = new DoublyLinkedListNode(2);

        DoublyLinkedListNode temp = new DoublyLinkedListNode(3);
        head.next = temp;
        temp.prev = head;

        temp = new DoublyLinkedListNode(4);
        head.next.next = temp;
        temp.prev = head.next;


        sortedInsert(head, 1);
    }

}