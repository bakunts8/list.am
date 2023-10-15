package org.example;

public class RotateArray {
    public static void main(String[] args) {
        int[][] array = {
                {14, 19, 24, 29, 34},
                {13, 18, 23, 28, 33},
                {12, 17, 22, 27, 32},
                {11, 16, 21, 26, 31},
                {10, 15, 20, 25, 30}
        };
        int[][] array1 = {
                {12, 54, 78, 12, 54, 78, 7, 12, 54, 78, 12, 54, 78, 7},
                {14, 78, 45, 12, 54, 78, 78, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {12, 54, 78, 12, 54, 78, 7, 12, 54, 78, 12, 54, 78, 7},
                {14, 78, 45, 12, 54, 78, 78, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7},
                {78, 78, 5, 12, 54, 78, 45, 12, 54, 78, 12, 54, 78, 7}
        };
        rotateArray(array);
        rotateArray(array1);
    }

    static int[][] rotateArray(int[][] array) {
        int temp1;
        int temp2;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array[i].length - 1 - i; j++) {
                temp1 = array[i][j];
                array[i][j] = array[array.length - 1 - j][i];
                temp2 = array[j][array.length - 1 - i];
                array[j][array.length - 1 - i] = temp1;
                temp1 = array[array.length - 1 - i][array.length - 1 - j];
                array[array.length - 1 - i][array.length - 1 - j] = temp2;
                array[array.length - 1 - j][i] = temp1;
            }
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return array;
    }
}
