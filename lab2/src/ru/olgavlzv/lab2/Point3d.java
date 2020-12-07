package ru.olgavlzv.lab2;

public class Point3d extends Point2d {
    /* координата Z */
    private double zCoord;

    /* конструктор инициализации */
    public Point3d (double z) {
        zCoord = z;
    }

    /* Конструктор по умолчанию. */
    public Point3d () {
        this(0);
    }

    /* Возвращение координаты Z */
    public double getZ () {
        return zCoord;
    }

    /* Установка значения координаты Z. */
    public void setZ (double val) {
        zCoord = val;
    }

    /* Сравнивание */
    public boolean comparison(Point3d point){
        return this.getX() == point.getX() && this.getY() == point.getY() && this.getZ() == point.getZ();
    }

    /* Подсчёт расстояния между двумя точками */
    public Double distanceTo(Point3d point){
        double distance = Math.sqrt(Math.pow(this.getX() - point.getX(), 2) +
                Math.pow(this.getY() - point.getY(), 2) + Math.pow(this.getZ() - point.getZ(), 2));
        return (double) Math.round(distance * 100) / 100;
    }
}
