package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task5 {

    public static void main(String[] args) {
        System.out.println("Задача 5");
        /*Напишите функцию, которая принимает 3 числа и возвращает, что нужно сделать с
        a и b: они должны быть сложены, вычитаны, умножены или разделены, чтобы
        получить N. Если ни одна из операций не может дать N, верните "none".
        3 числа – это N, a и b.*/
        System.out.println("Введите числа N, a и b");
        Scanner t = new Scanner(System.in);
        int N = t.nextInt();
        int a = t.nextInt();
        int b = t.nextInt();
        if (a * b == N) {
            System.out.println("умножение");
        }
        else if (a / b == N || b / a == N){
            System.out.println("деление");
        }
        else if (a + b == N){
            System.out.println("сложение");
        }
        else if (a - b == N || b - a == N){
            System.out.println("вычитание");
        }
        else {
            System.out.println("none");
        }
    }
}
