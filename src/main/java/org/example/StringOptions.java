package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class StringOptions {

    public static void main(String[] args) {
        String word = "samsung";
        ArrayList<String> options = toStrings(word, 2);
        System.out.println(options);
    }

    static ArrayList<String> toStrings(String word, int length) {
        ArrayList<String> options = new ArrayList<>();
        if (length == 1) {
            for (int i = 0; i < word.length(); i++) {
                options.add(String.valueOf(word.charAt(i)));
            }
            return options;
        }
        int[] index = new int[length];
        int[] reversed = new int[length];
        for (int i = 0; i < length; i++) {
            index[i] = i;
            reversed[i] = word.length() - 1 - i;
        }
        reversed[length - 1]++;

        StringBuilder a = new StringBuilder();
        String current = "";
        boolean isTime;

        ArrayList<Integer> aa = new ArrayList<>();
        while (!Arrays.equals(index, reversed)) {
            isTime = true;

            for (int j = 1; j < length; j++) {
                aa.add(index[j - 1]);
                while (aa.contains(index[j])) {
                    index[j]++;
                    int u = 0;
                    while (index[j - u] == word.length()) {
                        index[j - u] = 0;
                        u++;
                        index[j - u]++;
                        aa.set(j - u, index[j - u]);
                    }
                }
            }

            for (int i = 0; i < length; i++) {
                aa.clear();
                current = String.valueOf(a.append(word.charAt(index[i])));
            }

            if (index[length - 1] == word.length() - 1) {
                index[length - 1] = 0;
                index[length - 2]++;
                isTime = false;
            }
            a = new StringBuilder();

            if (isTime) {
                index[length - 1]++;
            }

            if (!options.contains(current)) {
                options.add(current);
                System.out.println(current);
            }
        }
        return options;
    }
}