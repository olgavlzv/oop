package ru.olgavlzv.practice5;

import java.util.Scanner;
import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static int[] encrypt(String str) {
        /*Пришло время отправлять и получать секретные сообщения.
        Создайте две функции, которые принимают строку и массив и возвращают закодированное
        или декодированное сообщение. Первая буква строки или первый элемент массива представляет
        собой символьный код этой буквы. Следующие элементы-это различия между символами:
        например, A +3 --> C или z -1 --> y. */

        int[] res = new int[str.length()];
        int lastChar = 0;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int charSym = charArray[i];
            res[i] = charSym - lastChar;
            lastChar = charSym;
        }
        return res;
    }

    public static String decrypt(int[] a) {
        String res = "";
        int lastCode = 0;
        for (int i = 0; i < a.length; i++) {
            char newAscii = (char) (lastCode + a[i]);
            lastCode = lastCode + a[i];
            res += String.valueOf(newAscii);
        }
        return res;
    }

    public static boolean canMove(String a, String bStr, String cStr) {
        /*Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
        Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не
        может этого сделать.
        Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король". */

        char[] b = bStr.toCharArray();
        char[] c = cStr.toCharArray();
        if ((b.length == 2 && c.length == 2) && ((b[0] >= 'A' && b[0] <= 'H') || (b[0] >= 'a' && b[0] <= 'h')) &&
                ((c[0] >= 'A' && c[0] <= 'H') || (c[0] >= 'a' && c[0] <= 'h')) && (b[1] >= '1' && b[1] <= '8')
                && (c[1] >= '1' && c[1] <= '8')) {
            if (a.trim().toLowerCase().equals("пешка")) {
                if (b[0] == c[0]) {
                    if (b[1] == '1') {
                        return false;
                    }
                    if (b[1] == '2' && c[1] == '4') {
                        return true;
                    }
                    int begin = b[1];
                    int end = c[1];
                    return begin + 1 == end;
                } else {
                    return false;
                }
            } else if (a.trim().toLowerCase().equals("конь")) {
                return ((Math.abs((b[1] - c[1])) == 2 && Math.abs(b[0] - c[0]) == 1) ||
                        (Math.abs((b[0] - c[0])) == 2 && Math.abs(b[1] - c[1]) == 1));
            } else if (a.trim().toLowerCase().equals("слон")) {
                return (Math.abs(b[0] - c[0]) == Math.abs(b[1] - c[1]));
            } else if (a.trim().toLowerCase().equals("ладья")) {
                return b[0] == c[0] || b[1] == c[1];
            } else if (a.trim().toLowerCase().equals("ферзь")) {
                return (b[0] == c[0] || b[1] == c[1]) || (Math.abs(b[0] - c[0]) == Math.abs(b[1] - c[1]));
            } else if (a.trim().toLowerCase().equals("король")) {
                return ((b[0] == c[0]) && (b[1] + 1 == c[1]) || (b[1] == c[1]) && (b[0] + 1 == c[0]) ||
                        (b[0] == c[0]) && (b[1] - 1 == c[1]) || (b[1] == c[1]) && (b[0] - 1 == c[0]));
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public static boolean canComplete(String a, String b) {
        /*Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие
        буквы не должны быть удалены, чтобы соответствовать слову. Кроме того, порядок букв во входной
        строке должен быть таким же, как и порядок букв в последнем слове.
        Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено. */

        for (char let: a.toCharArray()) {
            int curSymPos = b.indexOf(let);
            if ((curSymPos != -1)) {
                b = b.substring(curSymPos+1);
            }
            else return false;
        }
        return true;
    }

    //5.4
    public static int sumDigProd(int[] numbers){
        /*Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе и
        возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру. */

        int sum = 0;
        for (int num:numbers) {
            sum += num;
        }
        while (sum / 10 != 0){
            int allNumProded = 1;
            String temp = Integer.toString(sum);
            for (int i = 0; i < temp.length(); i++)
            {
                allNumProded = Character.getNumericValue(temp.charAt(i)) * allNumProded;
            }
            sum = allNumProded;
        }
        return sum;
    }

    public static ArrayList<String> sameVowelGroup(String[] str) {
        /*Напишите функцию, которая выбирает все слова, имеющие все те же гласные
        (в любом порядке и / или количестве), что и первое слово, включая первое слово.*/

        ArrayList<String> AnsWords = new ArrayList<>(Collections.singletonList(str[0]));
        ArrayList<Character> usedVowelsDefault = new ArrayList<>();
        for (char symFromMainWord: str[0].toLowerCase().toCharArray()) {
            if (!usedVowelsDefault.contains(symFromMainWord)) {
                switch (symFromMainWord) {
                    case 'a' -> usedVowelsDefault.add('a');
                    case 'e' -> usedVowelsDefault.add('e');
                    case 'i' -> usedVowelsDefault.add('i');
                    case 'o' -> usedVowelsDefault.add('o');
                    case 'u' -> usedVowelsDefault.add('u');
                }
            }
        }
        for (int i = 1; i < str.length; i++) {
            ArrayList<Character> usedVowelsCur = new ArrayList<>();
            for (char checkSym: str[i].toLowerCase().toCharArray()) {
                if (!usedVowelsCur.contains(checkSym)) {
                    switch (checkSym) {
                        case 'a' -> usedVowelsCur.add('a');
                        case 'e' -> usedVowelsCur.add('e');
                        case 'i' -> usedVowelsCur.add('i');
                        case 'o' -> usedVowelsCur.add('o');
                        case 'u' -> usedVowelsCur.add('u');
                    }
                }
            }
            if (usedVowelsDefault.containsAll(usedVowelsCur) && usedVowelsCur.containsAll(usedVowelsDefault)) AnsWords.add(str[i]);
        }
        return AnsWords;
    }

    public static boolean validateCard(long num) {
        /*Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если
        это число является действительным номером кредитной карты, а в противном случае-false.
        Номера кредитных карт должны быть длиной от 14 до 19 цифр и проходить тест Луна, описанный ниже:
        – Удалите последнюю цифру (это"контрольная цифра").
        – Переверните число.
        – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры,
        сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
        – Добавьте все цифры.
        – Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен контрольной цифре из Шага 1. */

        String cardNum = Long.toString(num);
        int cardNumLength = cardNum.length();
        if((cardNumLength > 13) && (cardNumLength < 20)) {
            int contralNum = cardNum.charAt(cardNumLength-1) - '0';
            int sumOfNums = 0;
            for (int i = cardNumLength-2; i > -1; i--) {
                int curNum;
                if (i % 2 == 0) {
                    curNum = (cardNum.charAt(i) - '0') * 2;
                    if (curNum > 9) sumOfNums += curNum - 9;
                    else sumOfNums += curNum;
                }
                else sumOfNums += cardNum.charAt(i) - '0';
            }
            return (10 - (sumOfNums % 10)) == contralNum;
        }
        else
            return false;
    }

    public static String numToEng(int a) { //eng
        String s = Integer.toString(a);
        if (s.length() == 1) {
            return oneNumber(a);
        }
        else if (s.length() == 2) {
            return twoNumber(a);
        }
        else {
            return threeNumber(a);
        }
    }

    public static String oneNumber(int a){
        return switch (a) {
            case 0 -> "zero";
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> " ";
        };
    }

    public static String twoNumber(int a){
        String res = " ";
        String juu = " ";
        if (a < 20){
            switch (a) {
                case 10 -> res = "ten";
                case 11 -> res = "eleven";
                case 12 -> res = "twelve";
                case 13 -> res = "thirteen";
                case 14 -> res = "fourteen";
                case 15 -> res = "fifteen";
                case 16 -> res = "sixteen";
                case 17 -> res = "seventeen";
                case 18 -> res = "eighteen";
                case 19 -> res = "nineteen";
            }
        }
        else {
            int b = a / 10;
            a %= 10;
            switch (b) {
                case 2 -> juu = "twenty ";
                case 3 -> juu = "thirty ";
                case 4 -> juu = "fourty ";
                case 5 -> juu = "fifty ";
                case 6 -> juu = "sixty ";
                case 7 -> juu = "seventy ";
                case 8 -> juu = "eighty ";
                case 9 -> juu = "ninety ";
            }
            res = juu + oneNumber(a);
            if (a == 0){
                res = juu;
            }
        }
        return res;
    }

    public static String threeNumber(int a){
        String res;
        int c = a % 100;
        a = a / 100;
        res = oneNumber(a) + " hundred " + twoNumber(c);
        return res;
    }

    public static String numToRus(int a) { //rus
        String s = Integer.toString(a);
        if (s.length()==1) {
            return oneNumberRus(a);
        }
        else if (s.length()==2) {
            return twoNumberRus(a);
        }
        else {
            return threeNumberRus(a);
        }
    }

    public static String oneNumberRus(int a) {
        return switch (a) {
            case 0 -> "ноль";
            case 1 -> "один";
            case 2 -> "два";
            case 3 -> "три";
            case 4 -> "четыре";
            case 5 -> "пять";
            case 6 -> "шесть";
            case 7 -> "семь";
            case 8 -> "восемь";
            case 9 -> "девять";
            default -> " ";
        };
    }
    public static String twoNumberRus(int a) {
        String res = " ";
        String juu = " ";
        if (a < 20) {
            switch (a) {
                case 10 -> res = "десять";
                case 11 -> res = "одиннадцать";
                case 12 -> res = "двенадцать";
                case 13 -> res = "тринадцать";
                case 14 -> res = "четырнадцать";
                case 15 -> res = "пятнадцать";
                case 16 -> res = "шестнадцать";
                case 17 -> res = "семнадцать";
                case 18 -> res = "восемнадцать";
                case 19 -> res = "девятнадцать";
            }
        }
        else {
            int b = a % 10;
            a /= 10;
            switch (a) {
                case 2 -> juu = "двадцать ";
                case 3 -> juu = "тридцать ";
                case 4 -> juu = "сорок ";
                case 5 -> juu = "пятьдесят ";
                case 6 -> juu = "шестьдесят ";
                case 7 -> juu = "семьдесят ";
                case 8 -> juu = "восемьдесят ";
                case 9 -> juu = "девяносто ";
            }
            res = juu + oneNumberRus(b);
            if (b == 0){
                res = juu;
            }
        }
        return res;
    }

    public static String threeNumberRus(int a) {
        String res;
        String sotnya =" ";
        int b = a % 100;
        a /= 100;
        switch (a) {
            case 1 -> sotnya = "сто ";
            case 2 -> sotnya = "двести ";
            case 3 -> sotnya = "триста ";
            case 4 -> sotnya = "четыреста ";
            case 5 -> sotnya = "пятьсот ";
            case 6 -> sotnya = "шестьсот ";
            case 7 -> sotnya = "семьсот ";
            case 8 -> sotnya = "восемьсот ";
            case 9 -> sotnya = "девятьсот ";
        }
        if (b == 0) {
            res = sotnya;
        }
        res = sotnya + twoNumberRus(b);
        return res;
    }

    public static String getSha256Hash(String str) {
        /*Хеш-алгоритмы легко сделать одним способом, но по существу невозможно сделать наоборот.
        Например, если вы хешируете что-то простое, например, password123, это даст вам длинный код,
        уникальный для этого слова или фразы. В идеале, нет способа сделать это в обратном порядке.
        Вы не можете взять хеш-код и вернуться к слову или фразе, с которых вы начали.
        Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки. Хеш должен быть
        отформатирован в виде шестнадцатеричной цифры.*/

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b: hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    public static String correctTitle(String str) {
        /*Напишите функцию, которая принимает строку и возвращает строку с правильным регистром
        для заголовков символов в серии "Игра престолов".
        Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый
        символ в верхнем регистре, а остальные-в Нижнем.*/

        String[] words = str.toLowerCase().split("\\s+");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < words.length; i++){
            if (words[i].equals("in") || words[i].equals("of") || words[i].equals("the") || words[i].equals("and"))
                ans.append(words[i]).append(" ");
            else {
                char[] numArr = words[i].toCharArray();
                ans.append(Character.toUpperCase(numArr[0])).append(words[i].substring(1)).append(" ");
            }
            if (i == words.length - 1)
                ans = new StringBuilder(ans.substring(0, ans.length() - 1));
        }
        return ans.toString();
    }

    public static String hexLattice(int num) {
        /*Как указано в онлайн-энциклопедии целочисленных последовательностей:
        Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка имеет 6 соседей.
        Центрированное шестиугольное число - это центрированное фигурное число, представляющее шестиугольник
        с точкой в центре и всеми другими точками, окружающими центральную точку в шестиугольной решетке.*/

        int pointInFirstLine = 0;
        int workedNum = num;
        int subNum = 1;
        while (workedNum > 0) {
            pointInFirstLine++;
            workedNum -= subNum;
            subNum = pointInFirstLine * 6;
        }
        if ((((num - 1) % 6 == 0) || (num == 1)) && (workedNum == 0)) {
            StringBuilder ans = new StringBuilder();
            int dotsInLine = pointInFirstLine;
            for (int i = pointInFirstLine; i > 0; i--){
                ans.append(" ".repeat(i - 1));
                ans.append(" o".repeat(Math.max(0, dotsInLine)));
                dotsInLine++;
                ans.append("\n");
            }
            for (int i = dotsInLine-1; i > pointInFirstLine; i--){
                ans.append(" ".repeat(Math.max(0, (dotsInLine) - i)));
                ans.append(" o".repeat(Math.max(0, i - 1)));
                ans.append("\n");
            }
            return ans.toString();
        }
        else
            return "Invalid";
    }

    public static void main(String[] args) {
        System.out.println("Введите номер задания от 1 до 10");
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        switch (s) {
            case 1 -> {
                System.out.println("Задание 1");
                System.out.println("Для шифрования введите 1, для дешифрования - 0");
                Scanner in1 = new Scanner(System.in);
                Scanner sc1 = new Scanner(System.in);
                short sh = in1.nextShort();
                if (sh == 1) {
                    System.out.println("Введите сообщение для шифорвания");
                    String message = sc1.nextLine();
                    int[] res = encrypt(message);
                    System.out.println("Результат шифрования: ");
                    for (int i: res) {
                        System.out.print(i + " ");
                    }
                } else if (sh == 0) {
                    System.out.println("Введите количество слов в сообщении для дешифорвания");
                    int n = in1.nextInt();
                    System.out.println("Введите сообщение для дешифорвания");
                    int[] messageArr = new int[n];
                    for (int i = 0; i < n; i++) {
                        messageArr[i] = sc1.nextInt();
                    }
                    System.out.println("Результат дешифрования: " + decrypt(messageArr));
                } else {
                    System.out.println("Введенное число не используется в условии");
                }
            }
            case 2 -> {
                System.out.println("Задание 2");
                System.out.println("Введите шахматную фигуру, ее положение и целевую позицию");
                Scanner sc2 = new Scanner(System.in);
                String figure = sc2.nextLine();
                String position = sc2.nextLine();
                String target = sc2.nextLine();
                System.out.println("Фигура может двигаться к цели: " + canMove(figure, position, target));
            }
            case 3 -> {
                System.out.println("Задание 3");
                System.out.println("Введите входную строку и последнее слово");
                Scanner sc3 = new Scanner(System.in);
                String inStr = sc3.nextLine();
                String outStr = sc3.nextLine();
                System.out.println("Слово может быть завершено: " + canComplete(inStr, outStr));
            }
            case 4 -> {
                System.out.println("Задание 4");
                System.out.println("Введите количество чисел");
                Scanner sc4 = new Scanner(System.in);
                int n = sc4.nextInt();
                int[] numbers = new int[n];
                for (int i = 0; i < n; i++) {
                    numbers[i] = sc4.nextInt();
                }
                System.out.println("Результат: " + sumDigProd(numbers));
            }
            case 5 -> {
                System.out.println("Задание 5");
                System.out.println("Введите количество слов");
                Scanner in5 = new Scanner(System.in);
                int n = in5.nextInt();
                Scanner sc5 = new Scanner(System.in);
                System.out.println("Введите слова для проверки на одинаковость гласных");
                String[] words = new String[n];
                for (int i = 0; i < n; i++) {
                    words[i] = sc5.nextLine();
                }
                System.out.println("Слова, имеющие одинаковые гласные: " + sameVowelGroup(words));
            }
            case 6 -> {
                System.out.println("Задание 6");
                System.out.println("Введите номер кредитной карты");
                Scanner in6 = new Scanner(System.in);
                long cardNo = in6.nextLong();
                System.out.println("Число является действительным номером кредитной карты: " + validateCard(cardNo));
            }
            case 7 -> {
                System.out.println("Задание 7");
                System.out.println("Введите число от 0 до 999");
                Scanner in7 = new Scanner(System.in);
                int number = in7.nextInt();
                System.out.println("Число на английском: " + numToEng(number));
                System.out.println("Число на русском: " + numToRus(number));
            }
            case 8 -> {
                System.out.println("Задание 8");
                System.out.println("Введите строку");
                Scanner sc8 = new Scanner(System.in);
                String str = sc8.nextLine();
                System.out.println("Безопасный хеш SHA-256 для данной строки: " + getSha256Hash(str));
            }
            case 9 -> {
                System.out.println("Задание 9");
                System.out.println("Введите строку");
                Scanner sc9 = new Scanner(System.in);
                String str = sc9.nextLine();
                System.out.println("Cтрока с правильным регистром для заголовков символов в " +
                        "серии \"Игра престолов\": " + correctTitle(str));
            }
            case 10 -> {
                System.out.println("Задание 10");
                System.out.println("Введите целое число");
                Scanner in10 = new Scanner(System.in);
                int number = in10.nextInt();
                System.out.println("Иллюстрация в виде многострочной прямоугольной строки:");
                System.out.println(hexLattice(number));
            }
            default -> System.out.println("Вы ввели некорректный номер");
        }
    }
}
