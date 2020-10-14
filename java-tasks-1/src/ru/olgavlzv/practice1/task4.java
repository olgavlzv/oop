package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task4 {

    public static void main(String[] args) {
        System.out.println("Задача 4");
        /*Создайте функцию, которая принимает три аргумента (prob, prize, pay) и
        возвращает true, если prob * prize > pay; в противном случае возвращает false.*/
        System.out.println("Введите аргументы prob, prize, pay");
        Scanner t = new Scanner(System.in);
        int prob = t.nextInt();
        int prize = t.nextInt();
        int pay = t.nextInt();
        boolean k;
        if (prob * prize > pay) {
            k = true;
        }
        else {
            k = false;
        }
        System.out.println("k = " + k);
    }
}
