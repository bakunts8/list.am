package org.example;

import java.util.Arrays;

public class StringInLines {

    public static void main(String[] args) {
        String str = "thisisastringlineproblem";
        System.out.println(toLines(str, 4));
    }

    static String toLines(String text, int lines) {
        String[] words = new String[lines];
        Arrays.fill(words, "");
        StringBuilder builder = new StringBuilder();
        int k = 0;
        int x = -1;

        for (int i = 0; i < text.length(); i++) {
            if (k == 0 || k == lines - 1) {
                x *= -1;
            }
            words[k] += builder.append(text.charAt(i));
            builder = new StringBuilder();
            k += x;
        }

        String result = "";
        StringBuilder s = new StringBuilder();
        for (String word : words) {
            result = String.valueOf(s.append(word));
        }
        return result;
    }
}
