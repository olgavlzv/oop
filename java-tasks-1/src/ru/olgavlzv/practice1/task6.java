package ru.olgavlzv.practice1;

import java.util.*;
import java.lang.*;

public class task6 {

    public static int ctoa(char a) {
        int ascii = (int) a;
        return ascii;
    }

    public static void main(String[] args) {
        System.out.println("Задача 6");
        /*Создайте функцию, которая возвращает значение ASCII переданного символа.*/
        System.out.println("Введите символ");
        Scanner in = new Scanner(System.in);
        String b = in.nextLine();
        char a = b.charAt(0);
        System.out.println(ctoa(a));
        //System.out.println("Значение ASCII переданного символа - "
              //  + Integer.toHexString(Character.toString(a).getBytes(StandardCharsets.US_ASCII)[0]));

    }
}