package com.adhd.treesQ;

class HuffmanNode {
    HuffmanNode left;
    HuffmanNode right;
    char data;
    int count;

    HuffmanNode(char data) {
        this.data = data;
    }

}
public class HuffmanDecoding {

    void decode(String s, HuffmanNode root) {
        char[] letters = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        
        HuffmanNode node = root;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
				node = node.left;
			} else {
				node = node.right;
			}

            boolean isNLeafNode = node.left == null && node.right == null;
            if (isNLeafNode) {
                builder.append(node.data);//We have arrived at a letter.
                node = root;
            }
        }

        System.out.println(builder.toString());
    }
}