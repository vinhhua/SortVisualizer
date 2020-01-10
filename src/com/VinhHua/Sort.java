package com.VinhHua;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sort extends JPanel{
    private int numsOfBoxes = 50;
    private int delay = 0;
    private final int shuffle = 500;
    private Random random = new Random();
    // to change colors
    private int current = -1;
    private int checking = -1;
    private int off = 0;
    private boolean sorting = false;
    private boolean shuffled = false;
    private int SIZE = 600;
    private int width = SIZE / numsOfBoxes;
    private int[] array;
    private MenuScreen menuScreen;

    public Sort() {
        shuffle();

    }


    public void start() {
        bubbleSort();
//        insertionSort();
    }

    // create a list of boxes to sort
    public void createList() {
        array = new int[numsOfBoxes];
        for (int i = 0; i < numsOfBoxes; i++) {
            array[i] = i + 1;
        }
    }

    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println(e + "");
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
                int temp = array[j];
                array[j] = array[rand];
                array[rand] = temp;
            }
        }
        sorting = false;
        shuffled = true;
    }

    private void bubbleSort() {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    update();
                }
            }
        }
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > key) {
                update();
                delay();
                array[j] = array[j - 1];
                j--;
            }
            array[j] = key;
        }
    }

    public void swap(int i1, int i2) {
        delay();
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;

    }

    public void setNumsOfBoxes(int val) {
        this.numsOfBoxes = val;
    }

    public void changeSortingStatus() {
        this.sorting = !sorting;
    }

    public void setDelay(int val) {
        this.delay = val;
    }

    // TO DO
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < array.length; i++) {
            int HEIGHT = array[i] * width;
            g.setColor(Color.WHITE);
            if (current > -1 && i == current) {
                g.setColor(Color.GREEN);
            }
            if (checking > -1 && i == checking) {
                g.setColor(Color.RED);
            }
            g.fillRect(i * width, SIZE - HEIGHT, width, HEIGHT);
            g.drawRect(i * width, SIZE - HEIGHT, width, HEIGHT);
        }
    }
}
