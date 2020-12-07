package ru.olgavlzv.lab2;

import java.util.Scanner;

public class Lab1 {

    public static Double computeArea(Point3d point1, Point3d point2, Point3d point3){
        double a, b, c;
        a = point1.distanceTo(point2);
        b = point2.distanceTo(point3);
        c = point1.distanceTo(point3);
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Point3d point1 = new Point3d();
        Point3d point2 = new Point3d();
        Point3d point3 = new Point3d();
        System.out.println("Введите координаты первой точки: ");
        String s1 = in.nextLine();
        System.out.println("Введите координаты второй точки: ");
        String s2 = in.nextLine();
        System.out.println("Введите координаты третьей точки: ");
        String s3 = in.nextLine();
        String[] s11 = s1.split(" ");
        String[] s22 = s2.split(" ");
        String[] s33 = s3.split(" ");
        point1.setX(Double.parseDouble(s11[0]));
        point1.setY(Double.parseDouble(s11[1]));
        point1.setZ(Double.parseDouble(s11[2]));
        point2.setX(Double.parseDouble(s22[0]));
        point2.setY(Double.parseDouble(s22[1]));
        point2.setZ(Double.parseDouble(s22[2]));
        point3.setX(Double.parseDouble(s33[0]));
        point3.setY(Double.parseDouble(s33[1]));
        point3.setZ(Double.parseDouble(s33[2]));
        if (point1.comparison(point2) || point2.comparison(point3) || point1.comparison(point3)) {
            System.out.print("Вы ввели несколько идентичных точек");
        }
        else {
            double res = computeArea(point1, point2, point3);
            if (Double.isNaN(res) || res == 0.0d) {
                System.out.println("Такого треугольника не существует");
            }
            else {
                System.out.println("Площадь треугольника равна " + res);
            }
        }
    }
}
