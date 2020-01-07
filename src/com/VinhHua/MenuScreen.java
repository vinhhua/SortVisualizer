package com.VinhHua;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MenuScreen extends JFrame{

    private final static int WIDTH = 800;
    private final static int HEIGHT = 500;
    private final String[] sortAlgos = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort"};
    private final String[] runTimes = {"Best case: O(n^2)\nWorst case: O(n^2)", "Best case: O(n^2)\nWorst case: O(n^2)"};
    private Sort sort;


    public MenuScreen() {
        UISetUp();
        setFrameProperties();
    }

    private void UISetUp() {
        // Menu panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        Dimension dimension = new Dimension();
        dimension.width = 250;
        panel.setPreferredSize(dimension);

        // Labels
        JLabel algorithmsL = new JLabel("Sort Algorithms");
        JLabel delayL = new JLabel("Delay");
        JLabel sizeL = new JLabel("Size");

        // Sort algorithm's combo box
        JComboBox<String> sortAlgorithms = new JComboBox<>(sortAlgos);

        // Buttons
        JButton startBtn = new JButton("       Sort       ");
        JButton shuffleBtn = new JButton("     Shuffle     ");

        // Sliders
        JSlider sizeSlid = new JSlider(JSlider.HORIZONTAL, 25, 200, 25);
        JSlider delaySlid = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);

        // JTextArea
        JTextArea algorithmsTime = new JTextArea(runTimes[0]);

        // Border
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Sort Visualizer");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        panel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        // algorithm Label
        addComp(panel, algorithmsL, 0, 0, gridBagConstraints);

        // sort algorithm selection
        addComp(panel, sortAlgorithms, 0, 1, gridBagConstraints);

        // sort button
        addComp(panel, startBtn, 0, 2, gridBagConstraints);

        // shuffle button
        addComp(panel, shuffleBtn, 0, 3, gridBagConstraints);

        // delay label
        addComp(panel, delayL, 0, 4, gridBagConstraints);

        // delay slider
        addComp(panel, delaySlid, 0, 5, gridBagConstraints);
        Dictionary<Integer, JLabel> dict = new Hashtable<>();
        for (int i = 0; i <= 100; i += 20) {
            dict.put(i, new JLabel(i + ""));
        }

        delaySlid.setMajorTickSpacing(20);
        delaySlid.setLabelTable(dict);
        delaySlid.setPaintLabels(true);
        delaySlid.setPaintTicks(true);
        delaySlid.setSnapToTicks(true);

        // size of boxes label
        addComp(panel, sizeL, 0, 6, gridBagConstraints);

        // size slider selection
        addComp(panel, sizeSlid, 0, 7, gridBagConstraints);
        Dictionary<Integer, JLabel> dict2 = new Hashtable<>();
        for (int i = 50; i <= 200; i += 50) {
            dict2.put(i, new JLabel(i + ""));
        }

        sizeSlid.setMajorTickSpacing(50);
        sizeSlid.setLabelTable(dict2);
        sizeSlid.setPaintLabels(true);
        sizeSlid.setPaintTicks(true);
        sizeSlid.setSnapToTicks(true);

        // Comparisons done label
        JLabel comparison = new JLabel("Comparisons done: ");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(comparison, gridBagConstraints);

        // Array accessed label
        JLabel array = new JLabel("Arrays accessed: ");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(array, gridBagConstraints);

//        addComp(panel, algorithmsTime, 0, 10, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(algorithmsTime, gridBagConstraints);

        this.add(panel, BorderLayout.WEST);
    }

    private void setFrameProperties() {
        this.setTitle("Sort Visualizer");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    // custom gridbaglayout
    private void addComp(JPanel panel, JComponent component, int gridx, int gridy, GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        panel.add(component, gridBagConstraints);
    }

}
