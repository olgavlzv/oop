package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task8 {

    public static void main(String[] args) {
        System.out.println("Задача 8");
        //ПАК ДВА - ПОМЕНЯТЬ ПОТОМ
        /*Пара строк образует странную пару, если оба из следующих условий истинны:
        – Первая буква 1-й строки = последняя буква 2-й строки.
        – Последняя буква 1-й строки = первая буква 2-й строки.
        – Создайте функцию, которая возвращает true, если пара строк представляет собой
        странную пару, и false в противном случае.*/

        /*System.out.println("Введите два слова. Разделите их красной строкой");
        Scanner str = new Scanner(System.in);
        String str1 = str.nextLine();
        String str2 = str.nextLine();
        int len1 = str1.length();
        int len2 = str2.length();
        if (str1.charAt(0) == str2.charAt(len2-1) && str1.charAt(len1-1) == str2.charAt(0)) {
            System.out.print("true");
        }
        else {
            System.out.print("false");
        } */

        /*Создайте функцию, которая находит максимальное значение третьего ребра
        треугольника, где длины сторон являются целыми числами.*/
        System.out.println("Введите длины двух сторон треугольника");
        Scanner y = new Scanner(System.in);
        int a = y.nextInt();
        int b = y.nextInt();
        int c = a + b - 1;
        System.out.println("Максимамальная длина третьей стороны треугольника равна " + c);
    }
}
