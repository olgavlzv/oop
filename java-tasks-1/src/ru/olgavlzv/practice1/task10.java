package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task10 {

    public static void main(String[] args) {
        System.out.println("Задача 10");
        /*Создайте функцию, которая будет для данного a, b, c выполнять следующие
        действия:
        – Добавьте A к себе B раз.
        – Проверьте, делится ли результат на C.*/
        System.out.println("Введите целые значения A, B и С");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        for (int i = 0; i < b; i++) {
            a = a + a;
        }
        System.out.println("Если прибавлять А к самой себе В раз, то получится число " + a);
        if (a % c == 0) {
            System.out.println("Полученное число делится на С");
        }
        else {
            System.out.println("Полученное число не делится на С");
        }
    }
}
