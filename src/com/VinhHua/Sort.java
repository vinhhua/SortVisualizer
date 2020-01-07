package com.VinhHua;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sort extends JPanel{
    private final int numsOfBoxes = 50;
    private final int shuffle = 500;
    private Random random = new Random();
    private boolean sorting = false;
    private boolean shuffled = false;
    private int[] list;

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

    // randomly shuffle the list 500 times
    public void shuffle() {
        for (int i = 0; i < shuffle; i++) {
            for (int j = 0; j < numsOfBoxes; j++) {
                int rand = random.nextInt(numsOfBoxes);
                int temp = list[i];
                list[i] = list[rand];
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
        for (int i = 0; i < numsOfBoxes; i++) {

        }
    }
}
