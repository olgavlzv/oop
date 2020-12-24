package ru.olgavlzv.lab5;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class FractalExplorer {
    private final int size;
    private JImageDisplay image;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    private JComboBox box;

    private FractalExplorer(int size) {
        this.size = size;
        this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        fractal.getInitialRange(this.range);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Фракталы");
        JButton button = new JButton("Сброс");
        JButton saveButton = new JButton("Сохранить");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel label = new JLabel("Выберите фрактал:");

        image = new JImageDisplay(size, size);
        image.addMouseListener(new MouseListener());

        box = new JComboBox();
        box.addItem(new Mandelbrot());
        box.addItem(new Tricorn());
        box.addItem(new BurningShip());
        box.addActionListener(new ActionHandler());

        button.setActionCommand("Сброс");
        button.addActionListener(new ActionHandler());
        saveButton.setActionCommand("Сохранить");
        saveButton.addActionListener(new ActionHandler());

        panel1.add(label, BorderLayout.CENTER);
        panel1.add(box, BorderLayout.CENTER);
        panel2.add(button, BorderLayout.CENTER);
        panel2.add(saveButton, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(image, BorderLayout.CENTER);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Сброс")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand().equals("Сохранить")) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int t = chooser.showSaveDialog(image);
                if (t == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(image.getBufferedImage(), "png", chooser.getSelectedFile() );
                    }
                    catch (NullPointerException | IOException e1) {
                        JOptionPane.showMessageDialog(image, e1.getMessage(), "Ошибка при сохранении файла",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else {
                fractal = (FractalGenerator) box.getSelectedItem();
                range = new Rectangle2D.Double(0, 0, 0, 0);
                fractal.getInitialRange(range);
                drawFractal();
            }
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
                int count = fractal.numIterations(FractalGenerator.getCoord(range.x, range.x
                                + range.width, size, x),
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