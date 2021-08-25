package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame implements ActionListener {

    JButton buttonToOpenWindowGame;
    JButton buttonToOpenScoreboard;
    JButton buttonToExitTheGame;
    JLabel label = new JLabel();
    ImageIcon icon = new ImageIcon("baaak.jpg");
    Border border = BorderFactory.createLineBorder(Color.BLACK,2);


    StartFrame(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Snake");
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

        buttonToOpenWindowGame.setBounds(250,170,110,60);
        buttonToOpenScoreboard.setBounds(250,270,110,60);
        buttonToExitTheGame.setBounds(250,370,110,60);

        buttonToExitTheGame.setBackground(Color.GRAY);
        buttonToOpenScoreboard.setBackground(Color.GRAY);
        buttonToOpenWindowGame.setBackground(Color.GRAY);

        buttonToOpenWindowGame.setForeground(Color.BLACK);
        buttonToOpenScoreboard.setForeground(Color.BLACK);
        buttonToExitTheGame.setForeground(Color.BLACK);

        buttonToExitTheGame.setBorder(border);
        buttonToOpenScoreboard.setBorder(border);
        buttonToOpenWindowGame.setBorder(border);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage("sr.png"));


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
