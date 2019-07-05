package com.adhd.treesQ;

import java.util.Objects;

/**
     * Similar to the earlier approach, we could trace p's path upwards and check if any of the nodes cover q.
       The first node that covers q (we already know that every node on this path will cover p) must be the first
       common ancestor.
     */

public class LowestCommonAncestor2 {

    TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /* Error check - one node is not in the tree. */
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }

        return commonAncestorHelper(root, p, q);
    }

    TreeNode commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.right, p);

        if (pIsOnLeft != qIsOnLeft) {
            return root;
        }

        TreeNode childSide = pIsOnLeft ? root.left : root.right;
        return commonAncestorHelper(childSide, p, q);
    }

    boolean covers(TreeNode root, TreeNode p) {
        if (root == null) return false;
        if (root == p) return true;

        return covers(root.left, p) || covers(root.right, p);
    }
}