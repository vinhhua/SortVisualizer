package com.VinhHua;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sort extends JPanel{
    private int numsOfBoxes = 100;
    private int delay = 50;
    private final static int SHUFFLE = 500;
    private Random random = new Random();
    private boolean sorting = false;
    private boolean shuffled = true;
    private int SIZE = 600;
    private int width = SIZE / numsOfBoxes;
    private int[] array;


    /**
     * Default constructor to start the sort panel and to add it to a frame.
     */
    public Sort() {
        shuffle();
    }


    /**
     * Creates a list with a default value of 100 arrays.
     * User can change the size of the array with the size sliders from UI.
     */
    public void createList() {
        array = new int[numsOfBoxes];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
    }


    /**
     * Set the delay of thread, to simulate the sorting process.
     */
    public void delay() {
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            System.out.println(e + "");
        }
    }


    /**
     * Resets the state of the array.
     */
    public void resetState() {
        sorting = false;
        update();
    }


    /**
     * To repaint and to also calculate the width, so its always within the given size.
     * Based on the default size and the number of arrays.
     */
    public void update() {
        width = SIZE / numsOfBoxes;
        repaint();
    }


    /**
     * Shuffle the list 500 times so its evenly spread out.
     * Place the temp into the array randomly from 0 to the number of arrays.
     */
    public void shuffle() {
        createList();
        for (int i = 0; i < SHUFFLE; i++) {
            for (int j = 0; j < array.length; j++) {
                int rand = random.nextInt(numsOfBoxes);
                int temp = array[j];
                array[j] = array[rand];
                array[rand] = temp;
            }
        }
        sorting = false;
        shuffled = true;
    }


    /**
     * Bubble sort.
     * TO DO: Write description of the sort for practice and deeper understanding.
     */
    public void bubbleSort() {
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


    /**
     * Insertion sort.
     * TO DO: Write description of the sort for practice and deeper understanding.
     */
    public void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > key) {
                array[j] = array[j - 1];
                j--;
                update();
                delay();
            }
            array[j] = key;
            update();
        }
    }


    /**
     * This method makes the calling it in menu much easier since I don't have to access the array with
     * another reference variable of sort and it is very confusing and always bugs out when I do that.
     */
    public void quickSortNoParameters() {
        int start = 0;
        int end = array.length;
        quickSort(array, start, end);
    }


    /**
     * This method makes the calling it in menu much easier since I don't have to access the array with
     * another reference variable of sort and it is very confusing and always bugs out when I do that.
     */
    public void mergeSortNoParameters() {
        int start = 0;
        int end = array.length - 1;
        mergeSort(array, start, end);
    }


    /**
     * Merge sort.
     * @param array the array that needs to be sorted.
     * @param start the starting index, usually 0.
     * @param end the ending index, usually the array.length.
     * TO DO: Write description of the sort for practice and deeper understanding.
     */
    public void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int m = (start+end)/2;
            mergeSort(array, start, m);
            mergeSort(array, m+1, end);
            merge(array, start, m, end);
        }
    }


    /**
     * Helper method for merge sort, this does the real work.
     * @param array the array that needs to be sorted and broken down into smaller arrays.
     * @param start the starting index.
     * @param mid the middle index.
     * @param end the ending index.
     */
    private void merge(int[] array, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(array, start, leftArr, 0, n1);
        System.arraycopy(array, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0;

        int k = start;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                array[k] = leftArr[i];
                i++;
            } else {
                array[k] = rightArr[j];
                j++;
            }
            update();
            delay();
            k++;
        }

        while (i < n1) {
            array[k] = leftArr[i];
            i++;
            k++;
            update();
            delay();
        }

        while (j < n2) {
            array[k] = rightArr[j];
            j++;
            k++;
            update();
            delay();
        }
    }


    /**
     * Selection sort.
     * TO DO: Write description of the sort for practice and deeper understanding.
     */
    public void selectionSort() {
        for (int i = array.length - 1; i > 0; i--) {
            int largest = 0;
            for (int j = 1; j <= i; j++) {
                if (array[j] > array[largest]) {
                    largest = j;
                    delay();
                    update();
                }
            }
            swap(largest, i);
            update();
        }
    }


    /**
     * Quick sort.
     * @param array the array that needs to be sorted.
     * @param start the starting index, usually 0.
     * @param end the ending index, usually the length of the array.
     * TO DO: Write description of the sort for practice and deeper understanding.
     */
    public void quickSort(int[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex);
        quickSort(array, pivotIndex + 1, end);
    }


    /**
     * The helper method, partition the array into parts, then sort it based on the pivot.
     * If the element in said array is less than the pivot then put it to the left, if bigger than the pivot then
     * put it to the right.
     * @param array the array.
     * @param start the starting index.
     * @param end the ending index.
     * @return the pivot.
     */
    private int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int i = start;
        int j = end;

        while (i < j) {
            // NOTE: empty loop body
            while (i < j && array[--j] >= pivot);
            if (i < j) {
                array[i] = array[j];
                update();
                delay();
            }
            // NOTE: empty loop body
            while (i < j && array[++i] <= pivot);
            if (i < j) {
                array[j] = array[i];
                update();
                delay();
            }
        }
        update();
        delay();
        array[j] = pivot;
        return j;
    }


    /**
     * Heap sort, this one is a little bit complicated.
     * TO-DO: will come back and write a more descriptive document.
     */
    public void heapSort() {
        heapify(numsOfBoxes);
        int end = numsOfBoxes - 1;
        while (end > 0) {
            swap(end, 0);
            end--;
            fixHeapBelow(0, end);
            update();
            delay();
        }
    }


    /**
     * Heapify, to get the to its appropriate heap position.
     * @param index the index.
     */
    private void heapify(int index) {
        int start = getParent(index - 1);
        while (start >= 0) {
            fixHeapBelow(start, index - 1);
            start--;
            update();
            delay();
        }
    }


    private void fixHeapBelow(int start, int end) {
        int root = start;

        while (getLeftChild(root) <= end) {
            int child = getLeftChild(root);
            int temp = root;
            if (array[temp] < array[child]) {
                temp = child;
            } if (child + 1 <= end && array[temp] < array[child + 1]) {
                temp = child + 1;
            } if (temp == root) {
                return;
            } else {
                swap(root, temp);
                root = temp;
            }
            update();
            delay();
        }
    }

    /**
     * Returns the parent node, which is always (i - 1) / 2 since heap is a special kind of binary tree. (do the math)
     * @param index the int index.
     * @return the parent index.
     */
    private int getParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Returns the left node.
     * @param index as an int index.
     * @return the left child node.
     */
    private int getLeftChild(int index) {
        return 2 * index + 1;
    }


    /**
     * The swap helper method for bubble sort and selection sort.
     * @param a the first element.
     * @param b the second element.
     */
    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }


    /**
     * Setter method to set the number of arrays. Used by the size slider.
     * @param val the value of the slider.
     */
    public void setNumsOfBoxes(int val) {
        this.numsOfBoxes = val;
    }


    /**
     * Change the status of sorting into its not current status.
     */
    public void changeSortingStatus() {
        this.sorting = !sorting;
    }


    /**
     * Setter method to set the delay of sorting. Used by the delay slider.
     * @param val the value of the slider.
     */
    public void setDelay(int val) {
        this.delay = val;
    }


    /**
     * To check if the array is shuffled.
     * @return true if shuffled, false if otherwise.
     */
    public boolean isShuffled() {
        return this.shuffled;
    }

    /**
     * Paint the background to light gray and draw the arrays/ boxes.
     * @param g as graphics.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);
        draw(g);
    }


    /**
     * Draws the rectangle that represents the elements in the arrays.
     * @param g as graphics.
     */
    private void draw(Graphics g) {
        for (int i = 0; i < array.length; i++) {
            int HEIGHT = array[i] * width;
            g.setColor(Color.WHITE);
            g.fillRect(i * width, SIZE - HEIGHT, width, HEIGHT);
            g.drawRect(i * width, SIZE - HEIGHT, width, HEIGHT);
        }
    }
}