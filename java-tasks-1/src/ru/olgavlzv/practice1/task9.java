package ru.olgavlzv.practice1;

import java.util.Scanner;
import java.lang.*;

public class task9 {

    public static void main(String[] args) {
        System.out.println("Задача 9");
        /*Создайте функцию, которая принимает массив чисел и возвращает сумму его
        кубов.*/
        Scanner in = new Scanner(System.in);
        System.out.println("Введите длину массива");
        int n = in.nextInt();
        int[] arr = new int[n];
        int k = 0;
        System.out.println("Введите массив");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
            k += Math.pow(arr[i], 3);
        }
        System.out.println("Сумма кубов массива равна " + k);
    }
}