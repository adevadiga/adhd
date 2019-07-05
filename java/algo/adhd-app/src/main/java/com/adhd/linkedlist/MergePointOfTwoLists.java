package com.adhd.linkedlist;

import java.util.Stack;

/**
 * Given pointers to the head nodes of 2 linked lists that merge together at
 * some point, find the Node where the two lists merge. It is guaranteed that
 * the two head Nodes will be different, and neither will be NULL.
 */
public class MergePointOfTwoLists {


    //Mine solution - works awesomely
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        int length1 = 0;
        int length2 = 0;
        SinglyLinkedListNode node1 = head1;
        SinglyLinkedListNode node2 = head2;

        while (node1 != null) {
            length1++;
            node1 = node1.next;
        }

        while (node2 != null) {
            length2++;
            node2 = node2.next;
        }

        int delta = length1 - length2;
        SinglyLinkedListNode shorter = delta > 0 ? head2 : head1;
        SinglyLinkedListNode longer = delta > 0 ? head1 : head2;
        
        longer = moveAhead(longer, Math.abs(delta));

        while (shorter != null && longer != null) {
            if(shorter == longer){
                return shorter.data;
            }
            shorter = shorter.next;
            longer = longer.next;
        }

        return shorter.data;
    }

    static SinglyLinkedListNode moveAhead(SinglyLinkedListNode node, int delta) {
        while (node != null && delta > 0) {
            node = node.next;
            delta--;
        }

        return node;
    }

    static int findMergeNode2(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        Stack<SinglyLinkedListNode> stackA = buildStack(head1);
        Stack<SinglyLinkedListNode> stackB = buildStack(head2);

        SinglyLinkedListNode mergeNode = null;
        while (!stackA.empty() && !stackB.isEmpty() && stackA.peek() == stackB.peek()) {
            mergeNode = stackA.pop();
            stackB.pop();
        }

        return mergeNode.data;
    }

    static Stack<SinglyLinkedListNode> buildStack(SinglyLinkedListNode node) {
        Stack<SinglyLinkedListNode> stack =  new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        return stack;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode first = new SinglyLinkedListNode(1);
        first.next = new SinglyLinkedListNode(2);

        SinglyLinkedListNode second = new SinglyLinkedListNode(1);

        SinglyLinkedListNode common = new SinglyLinkedListNode(3);

        first.next.next = common;
        second.next = common;

        int value = findMergeNode(first, second);
        System.out.println(value);

    }
}