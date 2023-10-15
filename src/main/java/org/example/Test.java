package org.example;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] array = {7, 7, 8, 2, 1, 5, 1, 1, 3, 4, 2, 2, 81, 82, 83, 100, 1, 3, 4, 4, 1, 4, 9, 10, 11, 12, 13, 14, 15};

        longest(array);
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

    static void longest(int[] array) {
        TreeSet<Integer> numbers = new TreeSet<>();
        for (Integer i : array) {
            numbers.add(i);
        }

        int[] arr = new int[numbers.size()];
        int index = -1;
        for (Integer i : numbers) {
            index++;
            arr[index] = i;
        }

        List<Integer> sequenceEnds = new LinkedList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                sequenceEnds.add(i);
            }
        }

        int x = 0;
        int y = sequenceEnds.get(0);
        for (int i = 1; i < sequenceEnds.size(); i++) {
            if (sequenceEnds.get(i) - sequenceEnds.get(i - 1) > y - x) {
                x = sequenceEnds.get(i - 1);
                y = sequenceEnds.get(i);
            }
        }

        System.out.println(numbers);
        for (int i = x; i < y; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}