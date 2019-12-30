package com.adhd.treesQ;

/**
 * Design an algorithm and write code to find the first common ancestor
    of two nodes in a binary tree. 
    Avoid storing additional nodes in a data structure. 
    NOTE: This is not necessarily a binary search tree.
 */

class TreeNodeP {
    TreeNodeP left;
    TreeNodeP right;
    TreeNodeP parent;
    int data;
}
public class LowestCommonAncestor {

    //Approach-1
    static TreeNodeP commonAncestor(TreeNodeP p, TreeNodeP q) {
        int delta = depth(p) - depth(q);
        TreeNodeP first =  delta > 0 ? q : p; //shallower node
        TreeNodeP second = delta > 0 ? p : q; //farthest node
        second = moveUp(second, delta);

        //Find where paths intersect.
        while (first != second && first != null && second != null) {
            first = first.parent;
            second = second.parent;
        }

        return (first == null || second == null) ? null : first;
    }

    static int depth(TreeNodeP node) {
        int depth = 0;
        while (node != null){
            node = node.parent;
            depth++;
        }
        return depth;
    }

    static TreeNodeP moveUp(TreeNodeP node, int depth) {
        while (depth > 0 && node != null) {
            node = node.parent;
            depth--;
        }
        return node;
    }
}