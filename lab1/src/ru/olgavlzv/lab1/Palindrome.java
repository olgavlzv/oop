package ru.olgavlzv.lab1;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите слово");
        String s  = in.nextLine();
        System.out.println(isPalindrome(s, reverseString(s)));
    }

    public static String reverseString(String s) {
        //данный метод переворачивает слово (пример: слово => оволс)
        String newS = "";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            newS += s.charAt(n - i - 1);
        }
        return newS;
    }

    public static boolean isPalindrome(String s1, String s2) {
        //s1 - изначальное слово, s2 - реверсное слово
        return s1.equals(s2);
    }
}
