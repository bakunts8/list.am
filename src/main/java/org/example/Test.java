package org.example;

import java.util.*;

public class Test {
    public static void main(String[] args) {
    }

    static void print(int[] array) {
        int count;
        for (int i = 0; i < array.length; i++) {
            count = 0;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    count++;
                    array[j] = -200;
                }
            }
            if (count > 0 && array[i] != -200) {
                System.out.println(array[i] + "   " + count);
            }
        }
    }

    static void print(int n) {
        StringBuilder a = new StringBuilder();
        String s;
        for (int i = 0; i < 7; i++) {
            s = String.valueOf(a.append("*"));
            System.out.println(s);
        }
    }
}