package com.adhd.algo.stacksAndQus;

import java.util.Scanner;
import java.util.Stack;

class MyQueue<T> {
    Stack<T> leftStack = new Stack<>();
    Stack<T> rightStack = new Stack<>();

    void enqueue(T item) {
		leftStack.push(item);
    }
    
    T dequeue() {
        transferIfNeeded();
        return rightStack.pop();
    }

    T peek() {
        transferIfNeeded();
        return rightStack.peek();
    }

    void transferIfNeeded() {
        if (rightStack.empty()) {
            while (!leftStack.empty()) {
				rightStack.push(leftStack.pop());
			}
        }

    }
}


public class QueueUsing2Stacks {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}