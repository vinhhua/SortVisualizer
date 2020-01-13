package com.VinhHua;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class MenuScreen extends JFrame {
    private JSlider sizeSlid;
    private JSlider delaySlid;
    private JButton startBtn;
    private JButton shuffleBtn;
    private int currentAlg = 0;
    private JComboBox<String> sortAlgorithms;
    private JTextArea algorithmsTime;
    private final static int WIDTH = 850;
    private final static int HEIGHT = 625;
    private final String[] sortAlgos = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort"};
    private final String[] runTimes = {"Best case: O(n^2)\nWorst case: O(n^2)", "Best case: O(n^2)\nWorst case: O(n^2)",
            "Best case: O(n)\nWorst case: O(n^2)", "Best case: O(n log n)\nWorst case: O(n^2)",
            "Best case: O(n log n)\nWorst case: O(n log n)"};
    private Sort sort;


    /**
     * Default constructor for the Menu.
     */
    public MenuScreen() {
        UISetUp();
        setFrameProperties();
        buttonsListener();
        sort = new Sort();
        add(sort, BorderLayout.CENTER);
    }

    /**
     * Most of the components are here in this method.
     * It is a mess.
     */
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
        sortAlgorithms = new JComboBox<>(sortAlgos);

        // Buttons
        startBtn = new JButton("       Sort       ");
        shuffleBtn = new JButton("     Shuffle     ");

        // Sliders
        sizeSlid = new JSlider(JSlider.HORIZONTAL, 50, 300, 100);
        delaySlid = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        // JTextArea
        algorithmsTime = new JTextArea(runTimes[0]);

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

        delaySlid.setMajorTickSpacing(10);
        delaySlid.setLabelTable(dict);
        delaySlid.setPaintLabels(true);
        delaySlid.setPaintTicks(true);
        delaySlid.setSnapToTicks(true);

        // size of boxes label
        addComp(panel, sizeL, 0, 6, gridBagConstraints);

        // size slider selection
        addComp(panel, sizeSlid, 0, 7, gridBagConstraints);
        Dictionary<Integer, JLabel> dict2 = new Hashtable<>();
        for (int i = 50; i <= 300; i += 50) {
            dict2.put(i, new JLabel(i + ""));
        }

        // size slider labels, ticks, blah blah
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

        addComp(panel, algorithmsTime, 0, 10, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        panel.add(algorithmsTime, gridBagConstraints);

        this.setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
    }

    /**
     * Action listeners for buttons and sliders.
     */
    private void buttonsListener() {
        startBtn.addActionListener(e -> {
            backgroundThread();
        });

        shuffleBtn.addActionListener(e -> {
            sort.shuffle();
            sort.resetState();
        });

        sizeSlid.addChangeListener(e -> {
            int val = sizeSlid.getValue();
            sort.setNumsOfBoxes(val);
            sort.shuffle();
            sort.resetState();
        });

        delaySlid.addChangeListener(e -> {
            int val = delaySlid.getValue();
            sort.setDelay(val);
        });

        sortAlgorithms.addActionListener(e -> {
            this.currentAlg = sortAlgorithms.getSelectedIndex();
            algorithmsTime.setText(runTimes[currentAlg]);
            System.out.println("index at " + currentAlg);
        });

    }

    /**
     * SwingWorker, so I can run the sort in the background without the animation freezing up.
     */
    private void backgroundThread() {
        if (sort.isShuffled()) {
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    if (currentAlg == 0) sort.bubbleSort();
                    else if (currentAlg == 1) sort.insertionSort();
                    else if (currentAlg == 2) sort.selectionSort();
                    else if (currentAlg == 3) sort.quickSortNoParameters();
                    else sort.mergeSortNoParameters();
                    return null;
                }
            };
            worker.execute();
            sort.changeSortingStatus();
        }
    }

    /**
     * Set up the properties of the frame and it is cleaner this way.
     */
    private void setFrameProperties() {
        this.setTitle("Sort Visualizer");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Custom gridBagLayOut that is very useful to customize the position of all the components if GUI have to be done manually.
     *
     * @param panel              the JPanel that you want to add the component to.
     * @param component          the component that you want to add to the panel.
     * @param gridx              the x position.
     * @param gridy              the y position.
     * @param gridBagConstraints the layout that you want to use, gridbagConstraints in this case tho.
     */
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
