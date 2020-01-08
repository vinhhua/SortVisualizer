package com.VinhHua;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        SwingUtilities.invokeLater(MenuScreen:: new);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuScreen();
            }
        });
    }
}
