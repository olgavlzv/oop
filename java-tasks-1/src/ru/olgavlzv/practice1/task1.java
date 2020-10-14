package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task1 {

    public static void main(String[] args) {
        System.out.println("Задача 1");
        /*Два числа передаются в качестве параметров. Первый параметр,
        разделенный на второй параметр, будет иметь остаток, возможно, ноль. Верните
        это значение.*/
        System.out.println("Введите делимое и делитель");
        Scanner t = new Scanner(System.in);
        int a = t.nextInt();
        int b = t.nextInt();
        System.out.println("Остаток от деления " + a + " на "+ b + " равен " + a%b);
    }

}
