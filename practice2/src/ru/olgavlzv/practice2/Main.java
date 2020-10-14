package ru.olgavlzv.practice2;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static String task1(String a, int n) {
        /*Создайте функцию, которая повторяет каждый символ в строке n раз.*/
        char c;
        String strout = "";
        int k;
        for (int i = 0; i < a.length(); i++) {
            k = 0;
            while (k < n) {
                c = a.charAt(i);
                strout += Character.toString(c);
                k++;
            }
        }
        return strout;
    }

    public static String task2(int k) {
        /*Создайте функцию, которая принимает массив и возвращает разницу между
        самыми большими и самыми маленькими числами.*/
        String b = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Введите массив");
        int[] values = new int[k];
        for (int i = 0; i < k; i++) {
            values[i] = in.nextInt();
        }
        int max = 0;
        for (int i = 1; i < k; i++) {
            if (values[i] > max) max = values[i];
        }
        int min = 32676;
        for (int i = 1; i < k; i++) {
            if (values[i] < min) min = values[i];
        }
        b = "Максимальный элемент массива: " + Integer.toString(max) + "\nМинимальный элемент массива: " + Integer.toString(min);
        return b;
    }

    public static boolean task3(int l, int[] arr) {
        /*Создайте функцию, которая принимает массив в качестве аргумента и возвращает
         true или false в зависимости от того, является ли среднее значение всех элементов
         массива целым числом или нет.*/
        Scanner in = new Scanner(System.in);
        double avg = 0;
        for (int i = 0; i < l; i++) {
            avg += arr[i];
        }
        avg = avg / l;
        if (avg % 1 == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int[] task4(int l, int[] arr) {
        /*Создайте метод, который берет массив целых чисел и возвращает массив, в
        котором каждое целое число является суммой самого себя + всех предыдущих
        чисел в массиве.*/
        int[] arrsum = new int[l];
        arrsum[0] = arr[0];
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i - 1];
            arrsum[i] = arr[i] + sum;
        }
        return arrsum;
    }



    public static int task5(String str) {
        /*Создайте функцию, которая возвращает число десятичных знаков, которое имеет
        число (заданное в виде строки). Любые нули после десятичной точки
        отсчитываются в сторону количества десятичных знаков.*/
        String b = str.substring(str.indexOf("."), str.length() - 1);
        return b.length();
    }

    public static int task6(int num) {
        /*Создайте функцию, которая при заданном числе возвращает соответствующее
        число Фибоначчи.*/
        int f1 = 0, f2 = 1, t;
        for (int i = 1; i <= num; i++) {
            t = f2;
            f2 = f1 + f2;
            f1 = t;
        }
        return f2;
    }

    public static boolean task7(String num) {
        /*Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку,
        напишите функцию, чтобы определить, является ли вход действительным
        почтовым индексом. Действительный почтовый индекс выглядит следующим
        образом:
        – Должно содержать только цифры (не допускается использование нецифровых цифр).
        – Не должно содержать никаких пробелов.
        – Длина не должна превышать 5 цифр.*/
        if (num.length() > 5) {
            return false;
        }
        else {
            try {
                Integer.parseInt(num);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public static boolean task8(String s1, String s2) {
        /*Пара строк образует странную пару, если оба из следующих условий истинны:
        – Первая буква 1-й строки = последняя буква 2-й строки.
        – Последняя буква 1-й строки = первая буква 2-й строки.
        – Создайте функцию, которая возвращает true, если пара строк представляет собой
        странную пару, и false в противном случае.*/
        int len1 = s1.length();
        int len2 = s2.length();
        if (s1.charAt(0) == s2.charAt(len2-1) && s1.charAt(len1-1) == s2.charAt(0)) {
            return true; //System.out.print("true");
        }
        else {
            return false; //System.out.print("false");
        }
    }

    //TASK9
    public static boolean isSuffix(String s1, String s2) {
        /*Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
        – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
        – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
        – В противном случае верните false.*/
        s2 = s2.substring(0, s2.length()-1);
        if (s1.contains(s2)) {
            if (s1.indexOf(s2) == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public static boolean isPrefix(String s1, String s2) {
        /*Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
        – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
        – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
        – В противном случае верните false.*/
        s2 = s2.substring(1, s2.length());
        if (s1.contains(s2)) {
            if (s1.indexOf(s2) == s1.length()-s2.length()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public static int task10(int step) {
        /*Создайте функцию, которая принимает число (шаг) в качестве аргумента и
        возвращает количество полей на этом шаге последовательности.*/
        int t = 0;
        for (int i = 0; i < step; i++) {
            if (i % 2 == 1) {
                t += 3;
            }
            else {
                t -= 1;
            }
        }
        return t;
    }


    public static void main(String[] args) {
        System.out.println("Введите номер задания от 1 до 10");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        switch(s) {
            case 1:
                System.out.println("Задание 1");
                System.out.println("Введите строку");
                String str = in.nextLine();
                System.out.println("Введите n");
                int n = in.nextInt();
                System.out.println(task1(str, n));
                break;
            case 2:
                System.out.println("Задание 2");
                System.out.println("Введите размер массива");
                int k = in.nextInt();
                System.out.println(task2(k));
                break;
            case 3:
                System.out.println("Задание 3");
                System.out.println("Введите размер массива");
                int l = in.nextInt();
                System.out.println("Введите массив");
                int[] arr = new int[l];
                for (int i = 0; i < l; i++) {
                    arr[i] = in.nextInt();
                }
                System.out.println(task3(l, arr));
                break;
            case 4:
                System.out.println("Задание 4");
                System.out.println("Введите размер массива");
                int m = in.nextInt();
                System.out.println("Введите массив");
                int[] arrval = new int[m];
                for (int i = 0; i < m; i++) {
                    arrval[i] = in.nextInt();
                }
                for (int i = 0; i < task4(m, arrval).length; i++) {
                    System.out.print(task4(m, arrval)[i] + " ");
                }
                break;
            case 5:
                Scanner sc = new Scanner(System.in);
                System.out.println("Задание 5");
                System.out.println("Введите число с точкой");
                String str5 = sc.nextLine();
                System.out.println("Число цифр после запятой " + task5(str5));
                break;
            case 6:
                System.out.println("Задание 6");
                System.out.println("Введите число");
                int num = in.nextInt();
                System.out.println(task6(num));
                break;
            case 7:
                System.out.println("Задание 7");
                System.out.println("Введите почтовый интекс");
                String index = in.nextLine();
                System.out.println(task7(index));
                break;
            case 8:
                System.out.println("Задание 8");
                System.out.println("Введите два слова. Разделите их красной строкой");
                Scanner strok = new Scanner(System.in);
                String str1 = strok.nextLine();
                String str2 = strok.nextLine();
                System.out.println(task8(str1, str2));
                break;
            case 9:
                System.out.println("Задание 9");
                System.out.println("Введите слово и суффикс/префикс. Разделите их красной строкой");
                Scanner test = new Scanner(System.in);
                String word = test.nextLine();
                String sufpref = test.nextLine();
                System.out.println("Это суффикс: " + isSuffix(word, sufpref));
                System.out.println("Это префикс: " + isPrefix(word, sufpref));
                break;
            case 10:
                System.out.println("Задание 10");
                System.out.println("ШАГ 0: начните с 0\n ШАГ 1: добавьте 3\n ШАГ 2: вычтите 1");
                System.out.println("Введите номер шага");
                Scanner st = new Scanner(System.in);
                int step = st.nextInt();
                System.out.println(task10(step));
                break;
            default:
                System.out.println("Вы ввели некорректный номер");
        }
    }
}
