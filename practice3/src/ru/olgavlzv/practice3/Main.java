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
        return sum == num;
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
                if (!((code.charAt(i) >= '0' && code.charAt(i) <= '9') || (code.charAt(i) >= 'A' && code.charAt(i) <= 'F')
                        || (code.charAt(i) >= 'a' && code.charAt(i) <= 'f'))) {
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
        boolean exitWhile = false;
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
        for (int i = 0; i < arr1.length; i++){
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[i] == arr1[j] && i != j) {
                    arr1[j] = notInArray;
                }
            }
        }
        for (int i : arr1) { //уникальные числа в первом массиве
            if (i != notInArray) {
                k += 1;
            }
        }
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                if (arr2[i] == arr2[j] && i != j) {
                    arr2[j] = notInArray;
                }
            }
        }
        for (int i : arr2) { //уникальные числа во втором массиве
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
        String sqrs = Integer.toString(sqr);
        int a, b; //a - левое число, b - правое число
        String as = "", bs = "";
        int length = sqrs.length(); //длина квадрата числа
        if (length == 1) {
            as += 0;
            bs += 1;
        } else if (length % 2 == 0) {
            as += sqrs.substring(0, length / 2);
            bs += sqrs.substring(length / 2, length);
        } else {
            as += sqrs.substring(0, length / 2);
            bs += sqrs.substring(length / 2, length);
        }
        a = Integer.parseInt(as);
        b = Integer.parseInt(bs);
        return a + b == num;
    }

    public static String longestZero (String num) {
        /*Напишите функцию, которая возвращает самую длинную последовательность
        последовательных нулей в двоичной строке. */
        int k = 0, max = 0;
        String zeros = "";
        char[] strtochar = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            if (strtochar[i] == '0') {
                k++;
            } else {
                 if (k > max) {
                     max = k;
                 }
                 k = 0;
            }
        }
        for (int i = 0; i < max; i++) {
            zeros += "0";
        }
        return zeros;
    }

    public static int nextPrime (int num) {
        /*Если задано целое число, создайте функцию, которая возвращает следующее
        простое число. Если число простое, верните само число.*/
        int prime = num;
        if (num < 2) {
            return 2;
        } else {
            if (isPrime(num)) {
                return num;
            } else {
                while (!isPrime(prime)) {
                    prime++;
                }
                return prime;
            }
        }

    }

    public static boolean isPrime(int n) {
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                m++;
            }
        }
        if (m < 2) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean rightTriangle(int a, int b, int c) {
        /*Учитывая три числа, x, y и z, определите, являются ли они ребрами
        прямоугольного треугольника.*/
        int maxValue = 0;
        int firstValue = 0;
        int secondValue = 0;
        if (a > b && a > c) {
            maxValue = a;
            firstValue = b;
            secondValue = c;
        }
        else if (b > a && b > c) {
            maxValue = b;
            firstValue = a;
            secondValue = c;
        }
        else if (c > a && c > b) {
            maxValue = c;
            firstValue = a;
            secondValue = b;
        }
        else {
            return false;
        }
        return maxValue * maxValue == firstValue * firstValue + secondValue * secondValue;
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
                Scanner in2 = new Scanner(System.in);
                String str2 = in2.nextLine();
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
                Scanner sc = new Scanner(System.in);
                String old = sc.nextLine();
                System.out.println(flipEndChars(old));
            }
            case 5 -> {
                System.out.println("Задание 5");
                System.out.println("Введите число в 16-ричной системе счисления");
                Scanner sc1 = new Scanner(System.in);
                String code = sc1.nextLine();
                System.out.println("Это hex-код: " + isValidHexCode(code));
            }
            case 6 -> {
                System.out.println("Задание 6");
                System.out.println("Введите количество элементов первого массива");
                int n1 = in.nextInt();
                System.out.println("Введите первый массив");
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
                System.out.println("Введите число");
                Scanner instr = new Scanner(System.in);
                String str = instr.nextLine();
                System.out.println("Самая длинная последовательность нулей в строке: " + longestZero(str));
            }
            case 9 -> {
                System.out.println("Задание 9");
                System.out.println("Введите число");
                int num9 = in.nextInt();
                System.out.println("Следующее простое число: " + nextPrime(num9));
            }
            case 10 -> {
                System.out.println("Задание 10");
                System.out.println("Введите длины сторон треугольника");
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();
                System.out.println("Треугольник является прямоугольным: " + rightTriangle(x, y, z));
            }
            default -> System.out.println("Вы ввели некорректный номер");
        }
    }
}
