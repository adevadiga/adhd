package com.adhd.treesQ;

public class CheckBST {

    /**
     * Use in-order traversal
     * @param root
     * @return
     */
    Integer lastPrinted = null;
    boolean checkBST(Node root) {
        if (root == null) return true;

        if(!checkBST(root.left)) return false;

        if (lastPrinted != null && root.data <= lastPrinted) {
            return false;
        }
        lastPrinted = root.data;

        if(!checkBST(root.right)) return false;

        return true;
    }
}