package com.VinhHua;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JFrame{
    private JPanel panel;
    private JButton startButton;
    private JButton resetButton;

    public MenuScreen() {

    }

    /**
     * Customize the GridBayLayout for better UI, experiencing.
     * @param panel the panel that you want to add.
     * @param component the component,
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight
     * @param gridBagConstraints
     */
    private void addComp(JPanel panel, JComponent component, int gridx, int gridy, int gridwidth, int gridheight, GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.fill = GridBagConstraints.NONE;
    }

}
