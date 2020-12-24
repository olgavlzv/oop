package ru.olgavlzv.lab6;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    public int numIterations(double x, double y) {
        double zX = x;
        double zY = y;
        int count = 0;
        while ((count < MAX_ITERATIONS)) {
            count++;
            double remX = zX * zX - zY * zY + x;
            zY = Math.abs(2 * zX * zY) + y;
            zX = remX;
            if ((zX * zX + zY * zY) > 4) {
                break;
            }
        }
        if (count == MAX_ITERATIONS) {
            return -1;
        }
        return count;
    }
    public String toString() {
        return "Burning Ship";
    }
}