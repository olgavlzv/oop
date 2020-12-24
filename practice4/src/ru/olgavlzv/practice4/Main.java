package ru.olgavlzv.practice4;

import java.util.*;

public class Main {

    public static int isSpace(String a, String b) {
        /*Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк
        довольно плох, она решает напечатать эссе с помощью текстового процессора.
        Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет
        длину от 1 до 15 символов включительно и состоит только из прописных или
        строчных букв. Согласно инструкции к заданию, эссе должно быть
        отформатировано очень специфическим образом: каждая строка должна содержать
        не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый
        процессор Бесси может справиться с этим требованием, используя следующую
        стратегию:
        – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите
        его в эту строку. В противном случае поместите слово на следующую строку и
        продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке
        все равно должны быть разделены одним пробелом. В конце любой строки не должно
        быть места.
        – К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
        помогите ей правильно оформить свое эссе!*/

        String withSpaces = a + " " + b;
        int count = 0;
        for (int i = 0; i < withSpaces.length(); i++) {
            char c = withSpaces.charAt(i);
            if (c == ' ') {
                count++;
            }
        }
        return withSpaces.length() - count;
    }

    public static void essay(int n, int k, String str) {
        String[] arrStr = str.split(" ");
        for (int i = 1; i < n; i++) {
            if (isSpace(arrStr[i-1], arrStr[i]) <= k) {
                arrStr[i] = arrStr[i-1] + " " + arrStr[i];
                arrStr[i-1] = "";
            }
        }
        for (int i = 0; i < n; i++) {
            if (arrStr[i].length() > 0) {
                System.out.println(arrStr[i]);
            }
        }
    }

    public static String[] splitBkt(String str) {
        /*Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер
        должен быть сбалансирован.*/

        boolean checkBkt = true;
        char[] aArray = str.toCharArray();
        String[] resArray = new String[0];
        for (char i : aArray) {
            if (!(i == (int) '(' || i == (int) ')')) {
                checkBkt = false;
                break;
            }
        }
        if (checkBkt) {
            int count0 = 0, count1 = 0, preI = 0;
            for (int i = 0; i < aArray.length-1; i++) {
                if (aArray[i] == '(') count0++;
                if (aArray[i] == ')') count1++;
                if (count0 == count1) {
                    resArray = Arrays.copyOf(resArray, resArray.length + 1);
                    resArray[resArray.length - 1] = str.substring(preI, i+1);
                    preI = i + 1;
                }
            }
            resArray = Arrays.copyOf(resArray, resArray.length + 1);
            resArray[resArray.length - 1] = str.substring(preI);
        }
        return resArray;
    }

    public static String toCamelCase(String oldStr) { // 4.3
        /*Создайте две функции toCamelCase () и toSnakeCase (),
        каждая из которых берет одну строку и преобразует ее либо в camelCase, либо в snake_case.*/

        while (oldStr.contains("_")) {
            int find = oldStr.indexOf("_");
            if (find == 0) {
                oldStr = oldStr.substring(1);
            }
            else if (find < oldStr.length() - 2) {
                oldStr = oldStr.substring(0, find) + oldStr.substring(find+1, find + 2).toUpperCase() + oldStr.substring(find + 2);
            }
            else if (find < oldStr.length() - 1) {
                oldStr = oldStr.substring(0, find) + oldStr.substring(find+1, find + 2).toUpperCase();
            }
            else if (find < oldStr.length()) {
                oldStr = oldStr.substring(0, find);
            }
        }
        return oldStr;
    }

    public static String toSnakeCase(String oldStr) {
        for (int i = 0; i < oldStr.length(); i++) {
            if (oldStr.charAt(i) >= 'A' && oldStr.charAt(i) <= 'Z') {
                if (i == 0) {
                    oldStr = oldStr.substring(0, 1).toLowerCase() + oldStr.substring(1);
                }
                else if (i < oldStr.length() - 1) {
                    oldStr = oldStr.substring(0, i) + "_" + oldStr.substring(i, i + 1).toLowerCase() + oldStr.substring(i + 1);
                }
                else {
                    oldStr = oldStr.substring(0, i) + "_" + oldStr.substring(i).toLowerCase();
                }
            }
        }
        return oldStr;
    }

    public static String overTime(float[] array) {
        /*Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную с сверхурочной работой.

        Работа с 9 до 5: обычные часы работы
        После 5 вечера это сверхурочная работа
        Ваша функция получает массив с 4 значениями:
        – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
        – Конец рабочего дня. (Тот же формат)
        – Почасовая ставка
        – Множитель сверхурочных работ

        Ваша функция должна возвращать:
        $ + заработанные в тот день (округлены до ближайшей сотой)*/

        float overtimeMorning = 0.0f, overtime = 0.0f;
        if (array[0] < 9) {
            overtimeMorning = 9.0f - array[0];
        }
        if (array[1] > 17) {
            overtime = (array[1] - 17.0f) + overtimeMorning;
        }
        float result = (array[1] - array[0] - overtime) * array[2] + overtime * array[2] * array[3];
        String res = String.valueOf(result);
        res += "00";
        res = res.substring(0, res.indexOf(".") + 3);
        return "$" + res;
    }

    public static String BMI(String a, String b) { // 4.5
        /*Индекс массы тела (ИМТ) определяется путем измерения вашего веса в килограммах и деления на квадрат
        вашего роста в метрах. Категории ИМТ таковы:
        Недостаточный вес: <18,5
        Нормальный вес: 18.5-24.9
        Избыточный вес: 25 и более
        Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах, метрах или дюймах)
        и возвращать ИМТ и связанную с ним категорию. Округлите ИМТ до ближайшей десятой.*/

        a = a.trim();
        b = b.trim();
        int indexA = a.indexOf(' ');
        int indexB = b.indexOf(' ');
        String weightStr = a.substring(0, indexA);
        String weightStep = a.substring(indexA + 1);
        String heightStr = b.substring(0, indexB);
        String heightStep = b.substring(indexB + 1);
        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr);
        if (weightStep.contains("pounds")) {
            weight /= (2.205f);
        }
        if (heightStep.contains("inches")) {
            height /= (39.37f);
        }
        if (!(weightStep.contains("kilos") || weightStep.contains("pounds"))) {
            return "Некорректный ввод";
        }
        else if (!(heightStep.contains("meters") || heightStep.contains("inches"))) {
            return "Некорректный ввод";
        }
        else {
            String resConclusion;
            double bmi = weight / (height * height);
            if (bmi < 18.5) {
                resConclusion = " Недостаточный вес";
            }
            else if (bmi < 25) {
                resConclusion = " Нормальный вес";
            }
            else {
                resConclusion = " Избыточный вес";
            }
            String res = String.valueOf(bmi);
            res += "00";
            res = res.substring(0, res.indexOf(".") + 2);
            return res + resConclusion;
        }
    }

    public static int bugger(int num) {
        /*Создайте функцию, которая принимает число и возвращает его мультипликативное постоянство, которое
        представляет собой количество раз, которое вы должны умножать цифры в num, пока не достигнете одной цифры.*/

        int count = 0;
        while (num >= 10) {
            int mlt = 1, mod;
            while (num != 0) {
                mod = num % 10;
                mlt *= mod;
                num = num / 10;
            }
            num = mlt;
            count++;
        }
        return count;
    }

    public static String toStarShorthand(String str) { // 4.7
        /*Напишите функцию, которая преобразует строку в звездную стенографию.
        Если символ повторяется n раз, преобразуйте его в символ*n. */

        int i = 1, count = 1;
        String res = "";
        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
                if (str.length() - 1 == i){
                    res += str.charAt(i) + "*" + count;
                    break;
                }
                else
                    i++;
            }
            else {
                if (count == 1) {
                    res += str.charAt(i - 1);
                    if (i == str.length() - 1) {
                        res += str.charAt(i);
                    }
                }
                else {
                    res += str.charAt(i - 1) + "*" + count;
                }
                count = 1;
                i++;
            }
        }
        return res;
    }

    public static boolean doesRhyme(String s1, String s2) { // 4.8
        /*Создайте функцию, которая возвращает true, если две строки рифмуются, и false в противном случае.
        Для целей этого упражнения две строки рифмуются, если последнее слово из каждого предложения содержит
        одни и те же гласные. */

        String c = "";
        String k = "";
        int a = s1.lastIndexOf(" ");
        int b = s2.lastIndexOf(" ");
        String subs = s1.substring(a);
        String subs2 = s2.substring(b);
        for (char l:subs.toCharArray()) {
            if ((l=='a')||(l=='e')||(l=='i')||(l=='o')||(l=='u')||(l=='y')||(l=='A')||(l=='E')||(l=='I')||(l=='O')||(l=='U')||(l=='Y')){
                c += l;
            }
        }
        for (char l:subs2.toCharArray()){
            if ((l=='a')||(l=='e')||(l=='i')||(l=='o')||(l=='u')||(l=='y')||(l=='A')||(l=='E')||(l=='I')||(l=='O')||(l=='U')||(l=='Y')){
                k += l;
            }
        }
        return c.equalsIgnoreCase(k);
    }

    public static boolean trouble(String a, String b) { // 4.9
        /*Создайте функцию, которая принимает два целых числа и возвращает true, если число повторяется
        три раза подряд в любом месте в num1 и то же самое число повторяется два раза подряд в num2. */

        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        boolean result = false;
        int[] num1 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] num2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 1; i < aChar.length; i++) {
            if (aChar[i] == aChar[i-1]) {
                num1[Character.getNumericValue(aChar[i])] += 1;
            }
        }
        for (int i = 1; i < bChar.length; i++) {
            if (bChar[i] == bChar[i-1]) {
                num2[Character.getNumericValue(bChar[i])] += 1;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (num1[i] == 2 && num2[i] == 1) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static int countUniqueBooks(String a, String b) { // 4.10
        /*Предположим, что пара одинаковых символов служит концами книги для всех символов между ними.
        Напишите функцию, которая возвращает общее количество уникальных символов (книг, так сказать)
        между всеми парами концов книги.*/

        if (b.length() == 1) {
            String partA = a;
            String[] parts = new String[0];
            while (partA.length() != 0) {
                int beginPart = partA.indexOf(b);
                if (beginPart == -1) {
                    break;
                }
                partA = partA.substring(beginPart + 1);
                int endPart = partA.indexOf(b);
                parts = Arrays.copyOf(parts, parts.length + 1);
                parts[parts.length - 1] = partA.substring(0, endPart);
                partA = partA.substring(endPart + 1);
            }
            String resStr = "";
            for (String i: parts) {
                resStr += i;
            }
            String lowerRes = resStr.toLowerCase();
            boolean[] isItThere = new boolean[Character.MAX_VALUE];
            for (int i = 0; i < lowerRes.length(); i++) {
                isItThere[lowerRes.charAt(i)] = true;
            }
            int count = 0;
            for (boolean value: isItThere) {
                if (value) {
                    count++;
                }
            }
            return count;
        }
        else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Введите номер задания от 1 до 10");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        switch (s) {
            case 1 -> {
                System.out.println("Задание 1");
                System.out.println("Введите количество слов в строке, количество  новых строк," +
                        " и саму строку");
                Scanner in1 = new Scanner(System.in);
                Scanner sc1 = new Scanner(System.in);
                int n = in1.nextInt();
                int k = in1.nextInt();
                String str1 = sc1.nextLine();
                System.out.println("Правильно оформленный текст:");
                essay(n, k, str1);
            }
            case 2 -> {
                System.out.println("Задание 2");
                System.out.println("Введите строку, состоящей из круглых скобок");
                Scanner sc2 = new Scanner(System.in);
                String str2 = sc2.nextLine();
                String[] newkl = splitBkt(str2);
                System.out.println("Полученные кластеры из скобок: ");
                System.out.print("Результат: ");
                for (String str : newkl) {
                    System.out.print(Arrays.toString(splitBkt(str)));
                }
            }
            case 3 -> {
                System.out.println("Задание 3");
                System.out.println("Введите строку для преобразования");
                Scanner sc3 = new Scanner(System.in);
                String str3 = sc3.nextLine();
                System.out.println("camelCase: " + toCamelCase(str3));
                System.out.println("snake_case: " + toSnakeCase(str3));
            }
            case 4 -> {
                System.out.println("Задание 4");
                System.out.println("Введите четыре параметра: начало рабочего дня, конец рабочего дня, " +
                        "почасовая ставка и множитель сверхурочных работ");
                Scanner sc4 = new Scanner(System.in);
                float arr[];
                arr = new float [4];
                for (int i = 0; i < 4; i++) {
                    arr[i] = sc4.nextFloat();
                }
                System.out.println("Итоговая оплата: " + overTime(arr));
            }
            case 5 -> {
                System.out.println("Задание 5");
                System.out.println("Введите вес и рост (с единицами измерения), разделив красной строкой");
                Scanner sc5 = new Scanner(System.in);
                String w = sc5.nextLine();
                String h = sc5.nextLine();
                System.out.println("ИМТ и категория " + BMI(w, h));
            }
            case 6 -> {
                System.out.println("Задание 6");
                System.out.println("Введите число");
                Scanner sc6 = new Scanner(System.in);
                int num = sc6.nextInt();
                System.out.println("Мультипликативное постоянство числа: " + bugger(num));
            }
            case 7 -> {
                System.out.println("Задание 7");
                System.out.println("Введите строку");
                Scanner sc7 = new Scanner(System.in);
                String txt = sc7.nextLine();
                System.out.println("Звездная стенография строки: " + toStarShorthand(txt));
            }
            case 8 -> {
                System.out.println("Задание 8");
                System.out.println("Введите 2 строки через красную строку");
                Scanner sc8 = new Scanner(System.in);
                String txt1 = sc8.nextLine();
                String txt2 = sc8.nextLine();
                System.out.println("Строки ревнуются: " + doesRhyme(txt1, txt2));
            }
            case 9 -> {
                System.out.println("Задание 9");
                System.out.println("Введите 2 числа, разделив красной строкой");
                Scanner sc9 = new Scanner(System.in);
                String num1 = sc9.nextLine();
                String num2 = sc9.nextLine();
                System.out.println("Число повторяется три раза подряд в num1 и два раза подряд в num2: "
                        + trouble(num1, num2));
            }
            case 10 -> {
                System.out.println("Задание 10");
                System.out.println("Введите строку и символ-разделитель, разделив красной строкой");
                Scanner sc10 = new Scanner(System.in);
                String str = sc10.nextLine();
                String symb = sc10.nextLine();
                System.out.println("Уникальных символов в строке: " + countUniqueBooks(str, symb));
            }
            default -> System.out.println("Вы ввели некорректный номер");
        }
    }
}
