package com.VinhHua;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sort extends JPanel{
    private int numsOfBoxes = 50;
    private int delay = 1;
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

    // OK
    public void start() {
//        sorting();
        bubbleSort();
    }

    // OK
    // create a list of boxes to sort
    public void createList() {
        array = new int[numsOfBoxes];
        for (int i = 0; i < numsOfBoxes; i++) {
            array[i] = i + 1;
        }
    }

    // OK
    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }

    // SEMI-OK
    public void resetState() {
        sorting = false;
        current = -1;
        checking = -1;
        off = 0;
        update();
    }

    // OK
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
                }
                delay();
                update();
            }
        }
    }

    public void sorting() {
        if (sorting) {
            try {
                switch(menuScreen.getCurrentAlg()) {
                    case 0:
                        bubbleSort();
                        break;
                    case 1:
                        insertionSort();
                        break;
//                    case 2:
//                        selectionSort();
//                        break;
//                    case 3:
//                        mergeSort();
//                        break;
//                    case 4:
//                        quickSort();
//                        break;
                }
            } catch (IndexOutOfBoundsException e) {}
        }
        resetState();
        pause();
    }

    public void pause() {
        int i = 0;
        while (!sorting) {
            i++;
            if (i > 100) i = 0;
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
        }
        sorting();
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

    public boolean isSorting() {
        return this.sorting;
    }

    public boolean isShuffled() {
        return this.shuffled;
    }


    // SEEMS GOOD
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        draw(g);
    }

    // SEEMS GOOD
    private void draw(Graphics g) {
        for (int i = 0; i < array.length; i++) {
            int HEIGHT = array[i] * width;
            g.setColor(Color.WHITE);
            g.fillRect(i * width, SIZE - HEIGHT, width, HEIGHT);
            g.drawRect(i * width, SIZE - HEIGHT, width, HEIGHT);
        }

    }
}