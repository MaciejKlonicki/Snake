package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartFrame extends JFrame implements ActionListener{

    JButton buttonToOpenWindowGame;
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
        buttonToExitTheGame = new JButton("Exit");

        buttonToOpenWindowGame.addActionListener(this);
        buttonToExitTheGame.addActionListener(this);

        buttonToOpenWindowGame.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));
        buttonToExitTheGame.setFont(new Font(Font.DIALOG,  Font.BOLD, 15));

        buttonToExitTheGame.setIconTextGap(-10);
        buttonToOpenWindowGame.setIconTextGap(-10);

        buttonToOpenWindowGame.setBorder(BorderFactory.createEtchedBorder());
        buttonToExitTheGame.setBorder(BorderFactory.createEtchedBorder());

        buttonToOpenWindowGame.setBounds(250,170,110,60);
        buttonToExitTheGame.setBounds(250,290,110,60);

        buttonToExitTheGame.setBackground(Color.GRAY);
        buttonToOpenWindowGame.setBackground(Color.GRAY);

        buttonToOpenWindowGame.setForeground(Color.BLACK);
        buttonToExitTheGame.setForeground(Color.BLACK);

        buttonToExitTheGame.setBorder(border);
        buttonToOpenWindowGame.setBorder(border);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage("sr.png"));

        this.add(buttonToOpenWindowGame);
        this.add(buttonToExitTheGame);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonToOpenWindowGame) {
            try {
                MyFrame myFrame = new MyFrame();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
            this.dispose();
        }
        if(e.getSource() == buttonToExitTheGame) {
            System.exit(0);
        }
    }

}
