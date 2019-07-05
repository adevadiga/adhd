package com.adhd.treesQ;

/**
 * BST = left.data <= current.data < right. data
 * 
 * We start with a range of (min = NULL, max = NULL), which the root obviously meets.
 * We then branch left, checking that these nodes are within the range (min = NULL, max = 20)
 */
public class CheckBSTUsingMinMax {

    boolean checkBST(Node root) {
        return checkBST(root, null, null);
    }

    boolean checkBST(Node root, Integer min, Integer max) {
        if (root == null) return true;
        
        //If current node value is lesser than min or greater than max, return false
        if (min != null && root.data <= min && max != null && root.data > max ) {
            return false;
        }

        //When branch left max should be updated with current node value.
        //When branch right min should be updated with current node value.
        if (!checkBST(root.left, min, root.data) || !checkBST(root.right, root.data, max)) {
            return false;
        }

        return true;
    }
}