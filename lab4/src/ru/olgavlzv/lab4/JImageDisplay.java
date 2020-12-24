package ru.olgavlzv.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage bufferedImage;

    public JImageDisplay(int height, int width) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
    }

    public void clearImage() { // Set all pixel to black
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); i++) {
                bufferedImage.setRGB(i, j, 0);
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor) { // Set pixel to specific color
        bufferedImage.setRGB(x, y, rgbColor);
    }
}
