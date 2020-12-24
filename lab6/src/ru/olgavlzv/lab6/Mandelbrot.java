package ru.olgavlzv.lab6;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange (Rectangle2D.Double range){
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    public int numIterations(double x, double y) {
        int count = 0;
        double zX = x;
        double zY = y;
        while ((count < MAX_ITERATIONS)) {
            count++;
            double remX = zX * zX - zY * zY + x;
            zY = 2 * zX * zY + y;
            zX = remX;
            if ((zX * zX + zY * zY) > 4)
                break;
        }
        if (count == MAX_ITERATIONS) {
            return -1;
        }
        return count;
    }
    public String toString() {
        return "Mandelbrot";
    }
}
