package com.adhd.treesQ;

class TreeNode {
    int val;
    TreeNodeP left;
    TreeNodeP right;
    TreeNode(int x) { 
        val = x; 
    }
    
}
public class InvertTree {

    public static TreeNodeP invertTree(TreeNodeP root) {
        return invert(root);
    }

    static TreeNodeP invert(TreeNodeP root) {
        if (root != null) {
            swap(root);
            invert(root.left);
            invert(root.right);
        }
        return root;
    }

    static void swap(TreeNodeP node) {
        TreeNodeP leftSubTree = node.left;
        TreeNodeP rightSubTree = node.right;
        node.left = rightSubTree;
        node.right = leftSubTree;
    }

    static void traverse(TreeNodeP root) {
        if (root != null) {
            traverse(root.left);
            System.out.print(root.val + ", ");
            traverse(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNodeP root = new TreeNodeP(4);
        root.left = new TreeNodeP(2);
        root.right = new TreeNodeP(7);

        root.left.left = new TreeNodeP(1);
        root.left.right = new TreeNodeP(3);

        root.right.left = new TreeNodeP(6);
        root.right.right = new TreeNodeP(9);

        traverse(root);
        invertTree(root);
        System.out.println("---");
        traverse(root);
    }

}