package ru.olgavlzv.lab6;

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
    private int rowsRemaining;
    JButton button;
    JButton saveButton;

    private FractalExplorer(int size) {
        this.size = size;
        this.fractal = new Mandelbrot();
        this.range = new Rectangle2D.Double(0, 0, 0, 0);
        fractal.getInitialRange(this.range);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Фракталы");
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

        button = new JButton("Сброс");
        button.setActionCommand("Сброс");
        button.addActionListener(new ActionHandler());
        saveButton = new JButton("Сохранить");
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
            if (rowsRemaining == 0) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());
                fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
                drawFractal();
            }
        }
    }

    private void drawFractal() {
        enableUI(false);
        rowsRemaining = size;
        for (int i = 0; i < size; i++) {
            FractalWorker rowDrawer = new FractalWorker(i);
            rowDrawer.execute();
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object>{
        private final int yCoord;
        private int[] rgb;

        public FractalWorker(int yCoord) {
            this.yCoord = yCoord;
        }

        public Object doInBackground() {
            rgb = new int[size];
            for (int i = 0; i < size; i++) {
                int count = fractal.numIterations(FractalGenerator.getCoord(range.x, range.x +
                        range.width, size, i), FractalGenerator.getCoord(range.y, range.y + range.width,
                        size, yCoord));
                if (count == -1) {
                    rgb[i] = 0;
                }
                else {
                    double hue = 0.7f + (float) count / 200f;
                    int rgbColor = Color.HSBtoRGB((float) hue, 1f, 1f);
                    rgb[i]= rgbColor;
                }
            }
            return null;
        }

        public void done() {
            for (int i = 0; i < size; i++)
                image.drawPixel(i,yCoord,rgb[i]);
            image.repaint(0, 0, yCoord, size, 1);
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    public void enableUI(boolean val) {
        button.setEnabled(val);
        saveButton.setEnabled(val);
        box.setEnabled(val);
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(600);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}