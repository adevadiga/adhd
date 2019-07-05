package com.adhd.treesQ;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=trees
 * 
 * The height of a binary tree is the number of edges between the tree's root and its furthest leaf.
 */

 class Nodet {
    Nodet left;
    Nodet right;
    int data;

    Nodet(int data) {
        this.data = data;
    }
 }
public class HeightOfBinaryTree {

    static int height2(Nodet root) {
		if (root == null) {
			return -1;
		}
		return 1 + Math.max(height2(root.left), height2(root.right));
	}


    public static int height(Nodet root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = 0;
        if (root.left != null) {
            leftHeight = 1 + height(root.left);
        }
        
        int rightHeight = 0;
        if (root.right != null) {
            rightHeight = 1 + height(root.right);
        }
        
        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        Nodet root = new Nodet(3);
        insert(root, 2);
        insert(root, 5);
        insert(root, 1);
        insert(root, 4);
        insert(root, 6);
        insert(root, 7);

        System.out.println(height(root));
    }


    public static Nodet insert(Nodet root, int data) {
        if(root == null) {
            return new Nodet(data);
        } else {
            Nodet cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
}