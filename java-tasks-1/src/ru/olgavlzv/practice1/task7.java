package ru.olgavlzv.practice1;

import java.io.IOException;
import java.util.Scanner;

public class task7 {

    public static void main(String[] args) throws IOException {
        System.out.println("Задача 7");
        /*Напишите функцию, которая берет последнее число из последовательного списка
        чисел и возвращает сумму всех чисел до него и включая его.*/

        System.out.println("Введите послденее число:");
        int num = System.in.read();
        System.out.println(addUpTo(num));
    }

    private static int addUpTo(int num) {
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i + 1;
        }
        return sum;
    }

    private int addUpTo1(int num) {
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += i + 1;
        }
        return sum;
    }
}
