package org.example;

import java.util.*;

public class LongSequence {

    public static void main(String[] args) {
        int[] array = {7, 7, 8, 2, 1, 5, 1, 1, 3, 4, 2, 2, 81, 82, 83, 100, 1, 3, 4, 4, 1, 4, 9, 10, 11, 12, 13, 14, 15};
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] numbers = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        System.out.println(longest(array));
        System.out.println(longest(nums));
        System.out.println(longest(numbers));
    }

    static int longest(int[] array) {
        TreeSet<Integer> numbers = new TreeSet<>();
        for (Integer i : array) {
            numbers.add(i);
        }
        int length = 0;
        int x = 1;

        for (Integer i : numbers) {
            if (numbers.contains(i + 1)) {
                x++;
            } else {
                x = 1;
            }
            length = x > length ? x : length;
        }
        System.out.println(numbers);
        return length;
    }
}
