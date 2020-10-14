package ru.olgavlzv.practice1;

import com.sun.tools.javac.Main;

public class Main2 {
    // переменная класса
    private static int staticVar;
    // переменная экземпляра класса
    private int var;

    public static void main(String[] args) {
	// write your code here
        // можно
        Main2.doSmthStatic();
        int a = Main2.staticVar;
        // нельзя
//        Main2.doSmth();
//        int a = Main2.var;

        // можно
        Main2 main2 = new Main2();
        int b = main2.var;
        System.out.println(main2.var);
        main2.doSmth();
        System.out.println(main2.var);

        Main2 main22 = new Main2(5);
        System.out.println(main22.var);
    }

    // Конструктор по умолчанию, всегда скрыт
    public Main2() {

    }

    // Конструктор с инициализацией
    public Main2(int var) {
        this.var = var;
    }

    // метод класса
    private static void doSmthStatic() {
        // можно
        staticVar = 1;
        // нельзя
//        var = 1;
    }

    // метод экземпляра класса
    private void doSmth() {
        // можно
        staticVar = 1;
        var = 1;
        // нельзя
    }
}
