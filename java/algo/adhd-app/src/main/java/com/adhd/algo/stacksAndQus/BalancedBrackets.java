package com.adhd.algo.stacksAndQus;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedBrackets {



    static boolean solve(String s) {
		Stack<Character> brackets = new Stack<Character>();
		for (char bracket : s.toCharArray()) {
			if (bracket == '(' || bracket == '[' || bracket == '{') {
				brackets.push(bracket);
			} else {
				if ((bracket == ')' && !(!brackets.empty() && brackets.peek() == '('))
						|| (bracket == ']' && !(!brackets.empty() && brackets.peek() == '['))
						|| (bracket == '}' && !(!brackets.empty() && brackets.peek() == '{'))) {
					return false;
				}
				brackets.pop();
			}
		}
		return brackets.empty();
    }
    
    //Not 100% correct solution.
    static String isBalanced(String s) {
        Map<Character, Character> brackets = new HashMap<>();
        brackets.put('{', '}');
        brackets.put('[', ']');
        brackets.put('(', ')');

        int n = s.length();
        if (n % 2 != 0) {
            return "NO";
        }

        char[] letters = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch: letters) {
            if (brackets.containsKey(ch)) {
                stack.push(ch);
            } else {
                //its ending. Stack should match
                if (stack.isEmpty()) {
                    return "NO";
                }
                char topOfStack = stack.pop();
                char expectedEndingBracket = brackets.get(topOfStack);
                if (expectedEndingBracket != ch) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    public static void main(String[] args) {
        String s = "[()][{}()][](){}([{}(())([[{}]])][])[]([][])(){}{{}{[](){}}}()[]({})[{}{{}([{}][])}]";
        System.out.println(isBalanced(s));
        System.out.println(isBalanced("{)[](}]}]}))}(())("));
        System.out.println(isBalanced("([[)"));
    }

}