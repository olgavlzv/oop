package ru.olgavlzv.practice1;

import java.util.Scanner;

public class task3 {

    public static void main(String[] args) {
        System.out.println("Задача 3");
        /*В этой задаче фермер просит вас сказать ему, сколько ног можно сосчитать среди
        всех его животных. Фермер разводит три вида:
        chickens = 2 legs
        cows = 4 legs
        pigs = 4 legs
        Фермер подсчитал своих животных, и он дает вам промежуточный итог для каждого вида.
        Вы должны реализовать функцию, которая возвращает общее количество ног всех животных.*/
        System.out.println("Введите количество кур, коров и свиней");
        Scanner t = new Scanner(System.in);
        int ch = t.nextInt();
        int c = t.nextInt();
        int p = t.nextInt();
        System.out.println("Общее количество ног всех животных равно " + (ch*2+c*4+p*4));
    }
}
