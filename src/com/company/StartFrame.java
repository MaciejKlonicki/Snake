package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {

    JButton buttonToOpenWindowGame;
    JButton buttonToOpenScoreboard;
    JButton buttonToExitTheGame;
    JLabel label = new JLabel();
    ImageIcon icon = new ImageIcon("baaak.jpg");


    StartFrame(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        label.setIcon(icon);

        buttonToOpenWindowGame = new JButton("Start");
        buttonToOpenScoreboard = new JButton("Scoreboard");
        buttonToExitTheGame = new JButton("Exit");

        buttonToOpenWindowGame.addActionListener(this);
        buttonToOpenScoreboard.addActionListener(this);
        buttonToExitTheGame.addActionListener(this);

        buttonToOpenScoreboard.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        buttonToOpenWindowGame.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        buttonToExitTheGame.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));

        buttonToExitTheGame.setIconTextGap(-10);
        buttonToOpenWindowGame.setIconTextGap(-10);
        buttonToOpenScoreboard.setIconTextGap(-10);

        buttonToOpenScoreboard.setBorder(BorderFactory.createEtchedBorder());
        buttonToOpenWindowGame.setBorder(BorderFactory.createEtchedBorder());
        buttonToExitTheGame.setBorder(BorderFactory.createEtchedBorder());

        buttonToOpenWindowGame.setBounds(250,350,110,40);
        buttonToOpenScoreboard.setBounds(250,400,110,40);
        buttonToExitTheGame.setBounds(250,450,110,40);

        this.add(buttonToOpenScoreboard);
        this.add(buttonToOpenWindowGame);
        this.add(buttonToExitTheGame);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonToOpenWindowGame) {
            MyFrame myFrame = new MyFrame();
            this.dispose();
        }
        if(e.getSource() == buttonToExitTheGame) {
            System.exit(0);
        }
    }

}
