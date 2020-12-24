package ru.olgavlzv.practice6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int bell(int num) {
        /*Число Белла - это количество способов, которыми массив из n элементов может быть
        разбит на непустые подмножества. Создайте функцию, которая принимает число n и возвращает
        соответствующее число Белла.*/

        int[][] bell = new int[num+1][num+1];
        bell[0][0] = 1;
        for (int i = 1; i <= num; i++) {
            bell[i][0] = bell[i-1][i-1];
            for (int j=1; j<=i; j++) {
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
            }
        }
        return bell[num][0];
    }

    public static String translateWord(String str) {
        /*В «поросячей латыни» (свинский латинский) есть два очень простых правила:
        – Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного
        до конца слова и добавьте «ay» в конец.
        have ➞ avehay
        cram ➞ amcray
        take ➞ aketay
        cat ➞ atcay
        shrimp ➞ impshray
        trebuchet ➞ ebuchettray
        Если слово начинается с гласной, добавьте "yay" в конце слова.
        ate ➞ ateyay
        apple ➞ appleyay
        oaken ➞ oakenyay
        eagle ➞ eagleyay
        Напишите две функции, чтобы сделать переводчик с английского на свинский латинский.
        Первая функция translateWord (word) получает слово на английском и возвращает это слово,
        переведенное на латинский язык. Вторая функция translateSentence (предложение) берет английское
        предложение и возвращает это предложение, переведенное на латинский язык.*/

        String res = "";
        boolean ifLetterUp = false;
        if (str.equals(" ") || str.equals("")) {
            return res;
        }
        char l = str.charAt(0);
        if (l >= 'A' && l <= 'Z')
            ifLetterUp = true;
        if ((l == 'a') || (l == 'e') || (l == 'i') || (l == 'o') || (l == 'u') || (l == 'y') ||
                (l == 'A') || (l == 'E') || (l == 'I') || (l == 'O') || (l == 'U') || (l == 'Y')) {
            res = str + "yay";
        }
        else {
            for (int i = 1; i <= str.length()-1; i++) {
                char r = str.charAt(i);
                if ((r=='a') || (r=='e') || (r=='i') || (r=='o') || (r=='u') || (r=='y')) {
                    String end = str.substring(0, i);
                    res = str.substring(i) + end + "ay";
                    break;
                }
            }
        }
        if (ifLetterUp)
            res = Character.toUpperCase(res.charAt(0)) + res.toLowerCase().substring(1, res.length());
        return res;
    }

    public static String translateSentence(String str) {
        StringBuilder res = new StringBuilder();
        new StringBuilder();
        StringBuilder letters;
        new StringBuilder();
        StringBuilder symbols;
        if (str.equals(" ")) {
            return res + " ";
        }
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            letters = new StringBuilder();
            symbols = new StringBuilder();
            for (char l : words[i].toCharArray()) {
                if (l >= 'A' && l <= 'z') {
                    letters.append(l);
                }
                else {
                    symbols.append(l);
                }
            }
            if (i == words.length - 1)
                res.append(translateWord(letters.toString())).append(symbols);
            else
                res.append(translateWord(letters.toString())).append(symbols).append(" ");
        }
        return res.toString();
    }

    public static boolean validColor(String str) {
        /*Учитывая параметры RGB (A) CSS, определите, является ли формат принимаемых
        значений допустимым или нет. Создайте функцию, которая принимает строку (например, " rgb(0, 0, 0)")
        и возвращает true, если ее формат правильный, в противном случае возвращает false.*/

        str = str.toLowerCase();
        if (str.contains("rgba")) {
            String[] arrOfRGBA = str.substring(5).split("[\\D&&[^.]]");
            if (arrOfRGBA.length == 4) {
                try {
                    for (int i = 0; i < 3; i++){
                        if (!(Integer.parseInt(arrOfRGBA[i]) >= 0 && Integer.parseInt(arrOfRGBA[i]) <= 255))
                            return false;
                    }
                    return Float.parseFloat(arrOfRGBA[3]) >= 0 && Float.parseFloat(arrOfRGBA[3]) <= 1;
                } catch (Exception e) {
                    return false;
                }
            }
            else return false;
        }
        else if (str.contains("rgb")) {
            String[] arrOfRGBA = str.substring(4).split("[\\D&&[^.]]");
            if (arrOfRGBA.length == 3) {
                try {
                    for (int i = 0; i < 3; i++) {
                        if (!(Integer.parseInt(arrOfRGBA[i]) >= 0 && Integer.parseInt(arrOfRGBA[i]) <= 255))
                            return false;
                    }
                    return true;
                }
                catch (Exception e){
                    return false;
                }
            }
            else return false;
        }
        else return false;
    }

    public static String stripUrlParams(String url, String ... argsToDell) {
        /*Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся параметры
        запроса и параметры, указанные во втором аргументе (который будет необязательным массивом).*/

        String[] args = url.substring(url.indexOf("?") + 1).split("&");
        StringBuilder finalArgs = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                if (args[i].charAt(0) == args[j].charAt(0)) {
                    args[i] = " ";
                    break;
                }
            }
            for (String s : argsToDell) {
                if (args[i].charAt(0) == s.charAt(0)) {
                    args[i] = " ";
                    break;
                }
            }
        }
        Arrays.sort(args);
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals(" "))
                if (i != args.length - 1)
                    finalArgs.append(args[i]).append("&");
                else
                    finalArgs.append(args[i]);
        }
        return url.substring(0, url.indexOf("?")+1) + finalArgs;
    }

    public static String[] getHashTags(String str) {
        /*Напишите функцию, которая извлекает три самых длинных слова из заголовка
        газеты и преобразует их в хэштеги. Если несколько слов одинаковой длины, найдите
        слово, которое встречается первым.*/

        String[] wordsArr = str.toLowerCase().split("[\\s,]+");
        int highLength = wordsArr[0].length();
        for (int i = 1; i < wordsArr.length; i++) {
            if (wordsArr[i].length() > highLength)
                highLength = wordsArr[i].length();
        }
        int tagsSize = 3;
        if (wordsArr.length < 3)
            tagsSize = wordsArr.length;
        String[] tagsArr = new String[tagsSize];
        int tagsLeft = tagsSize;
        int tagsPosCounter = 0;
        for (int i = 0; (tagsLeft > 0)&&(highLength > 0); i++) {
            if (wordsArr[i].length() == highLength) {
                tagsArr[tagsPosCounter] = "#" + wordsArr[i];
                tagsLeft--;
                tagsPosCounter++;
            }
            if (i == wordsArr.length-1){
                i = -1;
                highLength--;
            }
        }
        return tagsArr;
    }

    public static int ulam(int num) {
        /*Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.*/

        int[] ulamPeriod = new int[num];
        for (int i = 0; i < ulamPeriod.length; i++) {
            switch (i) {
                case 0 -> ulamPeriod[i] = 1;
                case 1 -> ulamPeriod[i] = 2;
                default -> {
                    int waysOfSolve = 0;
                    int rightNumber = ulamPeriod[i - 1] + 1;
                    while (waysOfSolve != 2) {
                        waysOfSolve = 0;
                        for (int j = 0; j < i; j++) {
                            for (int k = 0; k < i; k++) {
                                if ((ulamPeriod[j] != ulamPeriod[k]) && (ulamPeriod[j] + ulamPeriod[k] == rightNumber))
                                    waysOfSolve++;
                            }
                        }
                        if (waysOfSolve != 2)
                            rightNumber++;
                        else
                            ulamPeriod[i] = rightNumber;
                    }
                }
            }
        }
        return ulamPeriod[num-1];
    }

    public static String longestNonRepeatingSubstring(String str) {
        /*Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.*/

        Map<Character, Integer> visitedChars = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < str.length(); end++) {
            char currChar = str.charAt(end);
            if (visitedChars.containsKey(currChar))
                start = Math.max(visitedChars.get(currChar)+1, start);
            if (output.length() < end - start + 1)
                output = str.substring(start, end + 1);
            visitedChars.put(currChar, end);
        }
        return output;
    }

    public static String convertToRoman(int arnum){
        /*Создайте функцию, которая принимает арабское число и преобразует его в римское число.*/

        StringBuilder ronum = new StringBuilder();
        if (arnum / 1000 != 0) {
            for (int i = arnum; i / 1000 != 0; i -= 1000){
                ronum.append("M");
            }
            arnum %= 1000;
        }
        if (arnum / 100 != 0) {
            int hundred = arnum / 100;
            if (hundred <= 3)
                ronum.append("C".repeat(Math.max(0, hundred)));
            else if (hundred == 4)
                ronum.append("CD");
            else if (hundred <= 8) {
                ronum.append("D");
                ronum.append("C".repeat(hundred - 5));
            } else if (hundred == 9)
                ronum.append("CM");
            arnum %= 100;
        }
        if (arnum / 10 != 0) {
            int ten = arnum / 10;
            if (ten <= 3)
                ronum.append("X".repeat(Math.max(0, ten)));
            else if (ten == 4)
                ronum.append("XL");
            else if (ten <= 8) {
                ronum.append("L");
                ronum.append("X".repeat(ten - 5));
            } else if (ten == 9)
                ronum.append("XC");
            arnum %= 10;
        }
        if (arnum % 10 != 0){
            int num = arnum % 10;
            if (num <= 3)
                ronum.append("I".repeat(Math.max(0, num)));
            else if (num == 4)
                ronum.append("IV");
            else if (num <= 8) {
                ronum.append("V");
                ronum.append("I".repeat(num - 5));
            } else ronum.append("IX");
        }
        return ronum.toString();
    }

    public static boolean formula(String str) {
        /*Создайте функцию, которая принимает строку и возвращает true или false в
        зависимости от того, является ли формула правильной или нет.*/

        boolean res = false;
        int equalsPos = str.indexOf("=");
        if ((equalsPos > -1) && (str.lastIndexOf("=") == equalsPos)) {
            int mathAns = Integer.parseInt(str.substring(equalsPos+1).trim());
            String mathExpress = str.substring(0, equalsPos);
            if ((str.contains("+")) && (str.indexOf("+") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\+ ");
                if (Integer.parseInt(mathVars[0]) + Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((str.contains("*")) && (str.indexOf("*") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\* ");
                if (Integer.parseInt(mathVars[0]) * Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((str.contains("/")) && (str.indexOf("/") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\/ ");
                if (Integer.parseInt(mathVars[0]) / Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
            else if ((str.contains("-")) && (str.indexOf("-") < equalsPos)) {
                String[] mathVars = mathExpress.trim().split(" \\- ");
                if (Integer.parseInt(mathVars[0]) - Integer.parseInt(mathVars[1]) == mathAns)
                    res = true;
            }
        }
        return res;
    }

    public static boolean palindromeDescendant(int num) {
        /*Число может не быть палиндромом, но его потомком может быть. Прямой потомок числа
        создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.
        Например, 123312 – это не палиндром, а его следующий потомок 363, где: 3 = 1 + 2; 6 = 3 + 3; 3 = 1 + 2.
        Создайте функцию, которая возвращает значение true, если само число является палиндромом или
        любой из его потомков вплоть до 2 цифр (однозначное число - тривиально палиндром). */

        boolean res = false;
        int aLength = Integer.toString(num).length();
        String[] arrayOfNumbers = Integer.toString(num).split("");
        StringBuilder workNum = new StringBuilder(Integer.toString(num));
        while ((aLength > 1) && (Integer.parseInt(workNum.toString()) != numReverse(Integer.parseInt(workNum.toString())))) {
            workNum = new StringBuilder();
            for (int i = 0; i < arrayOfNumbers.length; i++) {
                if (i % 2 != 0)
                    workNum.append(Integer.parseInt(arrayOfNumbers[i - 1]) + Integer.parseInt(arrayOfNumbers[i]));
            }
            aLength = workNum.length();
            arrayOfNumbers = workNum.toString().split("");
        }
        if ((Integer.parseInt(workNum.toString()) == numReverse(Integer.parseInt(workNum.toString()))) && (aLength > 1))
            res = true;
        return res;
    }

    public static int numReverse(int num) {
        int res = 0;
        while (num != 0) {
            int digit = num % 10;
            res = res * 10 + digit;
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Введите номер задания от 1 до 10");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        switch (s) {
            case 1 -> {
                System.out.println("Задание 1");
                System.out.println("Введите число");
                Scanner in1 = new Scanner(System.in);
                int n = in1.nextInt();
                System.out.println("Число Белла: " + bell(n));
            }
            case 2 -> {
                System.out.println("Задание 2");
                System.out.println("Для слова введите 1, для предложение - 0");
                Scanner sc2 = new Scanner(System.in);
                Scanner in2 = new Scanner(System.in);
                short k = in2.nextShort();
                if (k == 1) {
                    System.out.println("Введите слово:");
                    String word = sc2.nextLine();
                    System.out.println("Слово на свинском латинском: " + translateWord(word));
                } else if (k == 0){
                    System.out.println("Введите предложение:");
                    String sentence = sc2.nextLine();
                    System.out.println("Предложение на свинском латинском: " + translateSentence(sentence));
                } else {
                    System.out.println("Введеное число не используется в задании");
                }
            }
            case 3 -> {
                System.out.println("Задание 3");
                System.out.println("Введите строку");
                Scanner sc3 = new Scanner(System.in);
                String color = sc3.nextLine();
                System.out.println("Формат цвета правильный: " + validColor(color));
            }
            case 4 -> {
                System.out.println("Задание 4");
                System.out.println("Введите URL");
                Scanner sc4 = new Scanner(System.in);
                String urlName = sc4.nextLine();
                System.out.println("Введите 1, если нужен второй параметр для удаления. Иначе - 0");
                int need = in.nextInt();
                if (need == 1) {
                    System.out.println("Введите второй параметр:");
                    String par = sc4.nextLine();
                    System.out.println("Результат: " + stripUrlParams(urlName, par));
                }
                else {
                    System.out.println("Результат: " + stripUrlParams(urlName));
                }
            }
            case 5 -> {
                System.out.println("Задание 5");
                System.out.println("Введите заголовок газеты");
                Scanner sc5 = new Scanner(System.in);
                String g = sc5.nextLine();
                String[] res5 = getHashTags(g);
                System.out.println("Результат: ");
                for (String i: res5) {
                    System.out.print(i + " ");
                }
            }
            case 6 -> {
                System.out.println("Задание 6");
                System.out.println("Введите n");
                Scanner in6 = new Scanner(System.in);
                int n = in6.nextInt();
                System.out.println("n-ое число в последовательности Улама: " + ulam(n));
            }
            case 7 -> {
                System.out.println("Задание 7");
                System.out.println("Введите строку");
                Scanner sc7 = new Scanner(System.in);
                String str = sc7.nextLine();
                System.out.println("Самая длинная неповторяющаяся подстрока: " + longestNonRepeatingSubstring(str));
            }
            case 8 -> {
                System.out.println("Задание 8");
                System.out.println("Введите арабское число");
                Scanner in8 = new Scanner(System.in);
                int number = in8.nextInt();
                System.out.println("Римское число: " + convertToRoman(number));
            }
            case 9 -> {
                System.out.println("Задание 9");
                System.out.println("Введите формулу");
                Scanner sc9 = new Scanner(System.in);
                String formula = sc9.nextLine();
                System.out.println("Формула верная: " + formula(formula));
            }
            case 10 -> {
                System.out.println("Задание 10");
                System.out.println("Введите целое число");
                Scanner in10 = new Scanner(System.in);
                int number = in10.nextInt();
                System.out.println("Число и/или его потомки являются палиндромом: " + palindromeDescendant(number));
            }
            default -> System.out.println("Вы ввели некорректный номер");
        }
    }
}
