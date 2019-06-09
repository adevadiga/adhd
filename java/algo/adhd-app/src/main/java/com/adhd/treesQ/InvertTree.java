package com.adhd.treesQ;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { 
        val = x; 
    }
    
}
public class InvertTree {

    public static TreeNode invertTree(TreeNode root) {
        return invert(root);
    }

    static TreeNode invert(TreeNode root) {
        if (root != null) {
            swap(root);
            invert(root.left);
            invert(root.right);
        }
        return root;
    }

    static void swap(TreeNode node) {
        TreeNode leftSubTree = node.left;
        TreeNode rightSubTree = node.right;
        node.left = rightSubTree;
        node.right = leftSubTree;
    }

    static void traverse(TreeNode root) {
        if (root != null) {
            traverse(root.left);
            System.out.print(root.val + ", ");
            traverse(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        traverse(root);
        invertTree(root);
        System.out.println("---");
        traverse(root);
    }

}