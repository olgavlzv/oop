package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task2 {

    public static void main(String[] args) {
        System.out.println("Задача 2");
        /*Напишите функцию, которая принимает основание и высоту треугольника и
        возвращает его площадь.*/
        System.out.println("Введите основание и высоту треугольника");
        Scanner t = new Scanner(System.in);
        int a = t.nextInt();
        int b = t.nextInt();
        System.out.println("Площадь треугольника равна " + a*b/2);
    }
}
