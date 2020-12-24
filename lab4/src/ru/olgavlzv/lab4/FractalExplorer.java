package ru.olgavlzv.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private JImageDisplay image;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    private FractalExplorer(int size) {
        this.size = size;
        this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        fractal.getInitialRange(this.range);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Фракталы");
        JButton Button = new JButton("Сброс");

        image = new JImageDisplay(size, size);
        image.addMouseListener(new MouseListener());

        Button.addActionListener(new ActionHandler());

        frame.setLayout(new BorderLayout());
        frame.add(image, BorderLayout.CENTER);
        frame.add(Button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }

    public class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            double xCoord = FractalGenerator.getCoord(range.x,
                    range.x + range.width, size, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y,
                    range.y + range.width, size, e.getY());
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int count = fractal.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, size, x),
                        FractalGenerator.getCoord(range.y, range.y + range.width, size, y));
                if (count == -1) {
                    image.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    image.drawPixel(x, y, rgbColor);
                }
            }
        }
        image.repaint();

    }
    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(600);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}