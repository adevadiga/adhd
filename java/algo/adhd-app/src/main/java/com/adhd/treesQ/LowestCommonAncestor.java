package com.adhd.treesQ;

/**
 * Design an algorithm and write code to find the first common ancestor
    of two nodes in a binary tree. 
    Avoid storing additional nodes in a data structure. 
    NOTE: This is not necessarily a binary search tree.
 */

class TreeNode {
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    int data;
}
public class LowestCommonAncestor {

    //Approach-1
    static TreeNode commonAncestor(TreeNode p, TreeNode q) {
        int delta = depth(p) - depth(q);
        TreeNode first =  delta > 0 ? q : p; //shallower node
        TreeNode second = delta > 0 ? p : q; //farthest node
        second = moveUp(second, delta);

        //Find where paths intersect.
        while (first != second && first != null && second != null) {
            first = first.parent;
            second = second.parent;
        }

        return (first == null || second == null) ? null : first;
    }

    static int depth(TreeNode node) {
        int depth = 0;
        while (node != null){
            node = node.parent;
            depth++;
        }
        return depth;
    }

    static TreeNode moveUp(TreeNode node, int depth) {
        while (depth > 0 && node != null) {
            node = node.parent;
            depth--;
        }
        return node;
    }
}