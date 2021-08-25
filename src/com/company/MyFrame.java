package com.company;

import javax.swing.*;

public class MyFrame extends JFrame {

    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new MyPanel());
        this.setSize(600,600);
        this.setTitle("Snake");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
