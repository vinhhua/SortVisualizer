package com.VinhHua;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sort extends JPanel{
    private final int numsOfBoxes = 20;
    private final int shuffle = 500;
    private Random random = new Random();
    // to change colors
    private int current = -1;
    private int checking = -1;
    private int off = 0;
    private boolean sorting = false;
    private boolean shuffled = false;
    private int SIZE = 550;
    private int width = SIZE / numsOfBoxes;
    private int[] list;
    private MenuScreen menuScreen;

    public Sort() {
        shuffle();
    }


    public void start() {

    }

    // create a list of boxes to sort
    public void createList() {
        list = new int[numsOfBoxes];
        for (int i = 0; i < numsOfBoxes; i++) {
            list[i] = i + 1;
        }
    }

    public void resetState() {
        sorting = false;
        current = -1;
        checking = -1;
        off = 0;
        update();
    }

    public void update() {
        width = SIZE / numsOfBoxes;
        repaint();
    }

    // randomly shuffle the list 500 times
    public void shuffle() {
        createList();
        for (int i = 0; i < shuffle; i++) {
            for (int j = 0; j < numsOfBoxes; j++) {
                int rand = random.nextInt(numsOfBoxes);
                int temp = list[j];
                list[j] = list[rand];
                list[rand] = temp;
            }
        }
        sorting = false;
        shuffled = true;
    }

    // TO DO
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < numsOfBoxes; i++) {
            int HEIGHT = list[i] * width;
            g.setColor(Color.WHITE);
            if (current > -1 && current == i) {
                g.setColor(Color.GREEN);
            }
            if (current > -1 && current == checking) {
                g.setColor(Color.RED);
            }
            g.fillRect(i * width, SIZE - HEIGHT, width, HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(i * width, SIZE - HEIGHT, width, HEIGHT);
        }
    }
}
