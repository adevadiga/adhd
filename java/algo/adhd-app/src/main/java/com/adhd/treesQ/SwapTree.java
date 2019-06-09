package com.adhd.treesQ;

class Node {
    int val;
    Node left;
    Node right;
    Node(int x) { 
        val = x; 
    }
}
public class SwapTree {
    static void swap(int K, Node node, int depth) {
        if (node == null) return;

        swap(K, node.left, depth+1);
        swap(K, node.right, depth+1);

        if (K % depth == 0) {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }
}