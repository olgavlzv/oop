package ru.olgavlzv.practice3;

import java.util.*;
import java.lang.*;

public class Main {

    public static int solutions(int a, int b, int c) {
        /*Квадратное уравнение ax^2 + bx + c = 0 имеет либо 0, либо 1, либо 2 различных
        решения для действительных значений x. учитывая a, b и c, вы должны вернуть
        число решений в уравнение.*/
        int t;
        if (b * b - 4 * a * c < 0) {
            t = 0;
        }
        else if (b * b - 4 * a * c > 0) {
            t = 2;
        }
        else {
            t = 1;
        }
        return t;
    }

    public static int findZip(String test) {
        /*Напишите функцию, которая возвращает позицию второго вхождения "zip" в
        строку, или -1, если оно не происходит по крайней мере дважды. Ваш код должен
        быть достаточно общим, чтобы передать все возможные случаи, когда "zip" может
        произойти в строке.*/
        int c = test.indexOf("zip");
        if (c == -1) {
            return -1;
        }
        else {
            int d = test.indexOf("zip", c + 1);
            return d;
        }
    }

    public static boolean checkPerfect(int num) {
        /*Создайте функцию, которая проверяет, является ли целое число совершенным
        числом или нет. Совершенное число - это число, которое можно записать как
        сумму его множителей, исключая само число.*/
        int sum = 0;
        for (int i = 1; i < num; i++)
        {
            if (num % i == 0)
            {
                sum = sum + i;
            }
        }
        if (sum == num)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String flipEndChars(String old) {
        /*Создайте функцию, которая принимает строку и возвращает новую строку с
        заменой ее первого и последнего символов, за исключением трех условий:
        – Если длина строки меньше двух, верните "несовместимо".
        – Если первый и последний символы совпадают, верните "два-это пара."*/
        if (old.length() > 1) {
            char firstLetter = old.charAt(0);
            char lastLetter = old.charAt(old.length()-1);
            if (firstLetter == lastLetter) {
                return "Два - это пара.";
            }
            else {
                String restLine = old.substring(1, old.length()-1);
                return String.valueOf(lastLetter) + restLine + String.valueOf(firstLetter);
            }
        }
        else {
            return "Несовместимо.";
        }
    }

    public static boolean isValidHexCode(String code) {
        /*Создайте функцию, которая определяет, является ли строка допустимым
        шестнадцатеричным кодом.
        Шестнадцатеричный код должен начинаться с фунтового ключа # и иметь длину ровно 6
        символов. Каждый символ должен быть цифрой от 0-9 или буквенным символом от A-F.
        все буквенные символы могут быть прописными или строчными.*/
        boolean checking = true;
        if (code.length() == 7 && code.charAt(0) == '#') {
            for (int i = 1; i < 7; i++) {
                if (!((code.charAt(i) >= '0' && code.charAt(i) <= '9') || (code.charAt(i) >= 'A' && code.charAt(i)
                        <= 'F') || (code.charAt(i) >= 'a' && code.charAt(i) <= 'f'))) {
                    checking = false;
                    break;
                }
            }
            return checking;
        }
        else {
            return false;
        }
    }

    public static boolean same (int[] arr1, int[] arr2) {
        /*Напишите функцию, которая возвращает true, если два массива имеют одинаковое
        количество уникальных элементов, и false в противном случае.*/
        Random random = new Random();
        int k = 0;
        int c = 0;
        boolean exitWhile = false; // Creating random number with which we will remove repetitions from an array
        int notInArray = 0;
        while (!exitWhile) {
            boolean exitCycle = true;
            notInArray = random.nextInt();
            for (int i : arr1) {
                if (notInArray == i) {
                    exitCycle = false;
                    break;
                }
            }
            for (int i : arr2) {
                if (notInArray == i) {
                    exitCycle = false;
                    break;
                }
            }
            if (exitCycle) {
                exitWhile = true;
            }
        }
        for (int i = 0; i < arr1.length; i++){ // Replace repeating numbers in first array
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[i] == arr1[j] && i != j) {
                    arr1[j] = notInArray;
                }
            }
        }
        for (int i : arr1) { // Finding unique numbers in first array
            if (i != notInArray) {
                k += 1;
            }
        }
        for (int i = 0; i < arr2.length; i++) { // Replace repeating numbers in second array
            for (int j = 1; j < arr2.length; j++) {
                if (arr2[i] == arr2[j] && i != j) {
                    arr2[j] = notInArray;
                }
            }
        }
        for (int i : arr2) { // Finding unique numbers in second array
            if (i != notInArray) {
                c += 1;
            }
        }
        return c == k;
    }

    public static boolean isKaprekar (int num) {
        /*Число Капрекара-это положительное целое число, которое после возведения в
        квадрат и разбиения на две лексикографические части равно сумме двух
        полученных новых чисел:
        – Если количество цифр квадратного числа четное, то левая и правая части будут иметь
        одинаковую длину.
        – Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной
        половиной, а левая-самой маленькой или равной нулю, если количество цифр равно 1.
        – Учитывая положительное целое число n, реализуйте функцию, которая возвращает true,
        если это число Капрекара, и false, если это не так.*/
        int sqr = num * num;
        int a, b; //a - левое число, b - правое число
        String as = "", bs = "";
        int length = String.valueOf(Math.abs(sqr)).length(); //длина квадрата числа
        if (length == 1) {
            as += 0;
            bs += length;
        } else if (length % 2 == 0) {
            as += Integer.toString(sqr).substring(0, length / 2 - 1);
            bs += Integer.toString(sqr).substring(length / 2, length - 1);
        } else {
            as += Integer.toString(sqr).substring(0, length / 2);
            bs += Integer.toString(sqr).substring(length / 2 + 1, length);
        }
        a = Integer.parseInt(as);
        b = Integer.parseInt(bs);
        return a + b == num;
    }

    public static void main(String[] args) {
        System.out.println("Введите номер задания от 1 до 10");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        switch (s) {
            case 1 -> {
                System.out.println("Задание 1");
                System.out.println("Введите параметры a, b и c для квадратного уравнения");
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
                System.out.println("В уравнении " + solutions(a, b, c) + " решений");
            }
            case 2 -> {
                System.out.println("Задание 2");
                System.out.println("Введите строку");
                String str2 = in.nextLine();
                System.out.println("Второе вхождение \"zip\" в строку: " + findZip(str2));
            }
            case 3 -> {
                System.out.println("Задание 3");
                System.out.println("Введите число для проверки");
                int num = in.nextInt();
                System.out.println("Данное число является совершенным: " + checkPerfect(num));
            }
            case 4 -> {
                System.out.println("Задание 4");
                System.out.println("Введите строку");
                String old = in.nextLine();
                System.out.println(flipEndChars(old));
            }
            case 5 -> {
                Scanner sc = new Scanner(System.in);
                System.out.println("Задание 5");
                System.out.println("Введите число в 16-ричной система счисления");
                String code = in.nextLine();
                ;
                System.out.println("Это 16-ричное число: " + isValidHexCode(code));
            }
            case 6 -> {
                System.out.println("Задание 6");
                System.out.println("Введите количество элементов первого массива");
                int n1 = in.nextInt();
                System.out.println("Введите первоый массив");
                int[] arr1 = new int[n1];
                for (int i = 0; i < n1; i++) {
                    arr1[i] = in.nextInt();
                }
                System.out.println("Введите количество элементов второго массива");
                int n2 = in.nextInt();
                System.out.println("Введите второй массив");
                int[] arr2 = new int[n2];
                for (int j = 0; j < n2; j++) {
                    arr2[j] = in.nextInt();
                }
                System.out.println("В данных массивах одинаковое количество уникальных элементов: " + same(arr1, arr2));
            }
            case 7 -> {
                System.out.println("Задание 7");
                System.out.println("Введите целое число");
                int number = in.nextInt();
                System.out.println("Число является числом Капрекара: " + isKaprekar(number));
            }
            case 8 -> {
                System.out.println("Задание 8");
                System.out.println("Введите");
            }
            case 9 -> {
                System.out.println("Задание 9");
                System.out.println("Введите");
            }
            case 10 -> {
                System.out.println("Задание 10");
                System.out.println("");
            }
            default -> System.out.println("Вы ввели некорректный номер");
        }
    }
}
