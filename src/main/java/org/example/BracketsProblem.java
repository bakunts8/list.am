package org.example;

import java.util.Stack;

public class BracketsProblem {

    public static void main(String[] args) {
        String string = "{[()]{}}";
        System.out.println(isRight(string));
    }

    public static boolean isRight(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {

            char a = str.charAt(i);

            if (a == '{' || a == '[' || a == '(') {
                stack.push(a);
                continue;
            }

            switch (a) {
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
