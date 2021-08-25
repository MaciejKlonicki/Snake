package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {

    JButton buttonToOpenWindowGame;
    JButton buttonToOpenScoreboard;
    JButton buttonToExitTheGame;

    StartFrame(){
        this.setSize(600,600);
        this.setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        buttonToOpenWindowGame = new JButton("Start");
        buttonToOpenScoreboard = new JButton("Scoreboard");
        buttonToExitTheGame = new JButton("Exit");

        buttonToOpenWindowGame.addActionListener(this);
        buttonToOpenScoreboard.addActionListener(this);
        buttonToExitTheGame.addActionListener(this);

        buttonToOpenWindowGame.setBounds(230,100,150,60);
        buttonToOpenScoreboard.setBounds(230,230,150,60);
        buttonToExitTheGame.setBounds(230,360,150,60);

        this.add(buttonToOpenScoreboard);
        this.add(buttonToOpenWindowGame);
        this.add(buttonToExitTheGame);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonToOpenWindowGame) {
            MyFrame myFrame = new MyFrame();
        }
    }
}
