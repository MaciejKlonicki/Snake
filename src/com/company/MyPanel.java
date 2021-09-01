package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyPanel extends JPanel implements ActionListener {

    Clip clip;
    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("mixkit-retro-arcade-game-over-470.wav"));
    JButton playAgain = new JButton("Play Again!");
    Border border = BorderFactory.createLineBorder(Color.BLACK,2);
    JButton exit = new JButton("Exit");
    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int uSize = 25;
    final int gUnits = (WIDTH*HEIGHT)/uSize;
    final int x[] = new int[gUnits];
    final int y[] = new int[gUnits];
    int bananaX;
    int bananaY;
    int appleX;
    int appleY;
    int bParts = 4;
    int delay = 90;
    int applesEaten;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    private Image ball;
    private Image apple;
    private Image head;
    private Image banana;
    private Image back;


    MyPanel() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        loadImages();

    }

    public void startGame () throws InterruptedException, LineUnavailableException, IOException {
        newApple();
        Events();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("body.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("rec.png");
        head = iih.getImage();

        ImageIcon iis = new ImageIcon("banana.png");
        banana = iis.getImage();

        ImageIcon iib = new ImageIcon("baaak.jpg");
        back = iib.getImage();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        try {
            draw(g);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics g) throws LineUnavailableException, IOException {
        if (running) {

            g.drawImage(banana,bananaX,bananaY,null);

            g.drawImage(apple,appleX,appleY,null);
            for (int i = 0 ; i < bParts ; i++) {
                if (i == 0) {
                    g.drawImage(head,x[0],y[0],null);
                } else {
                    g.drawImage(ball,x[i],y[i],null);
                }
            }
        }
        else {
            gameOver(g);
        }
    }

    public void Events () {

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                bananaX = random.nextInt((int) WIDTH / uSize) * uSize;
                bananaY = random.nextInt((int) HEIGHT / uSize) * uSize;

                for (int i = 0; i <= bParts - 1; i++) {
                    if ((x[i] == bananaX && y[i] == bananaY) || (bananaX == appleX && bananaY == appleY)) {
                        bananaX = random.nextInt((int) WIDTH / uSize) * uSize;
                        bananaY = random.nextInt((int) HEIGHT / uSize) * uSize;
                    }
                }
            }
        },0,12,TimeUnit.SECONDS);
    }

    public void newApple () {
                appleX = random.nextInt((int) WIDTH / uSize) * uSize;
                appleY = random.nextInt((int) HEIGHT / uSize) * uSize;

                for (int i = 0 ; i<=bParts-1;i++){
                    if (x[i]==appleX && y[i]==appleY) {
                        appleX = random.nextInt((int) WIDTH / uSize) * uSize;
                        appleY = random.nextInt((int) HEIGHT / uSize) * uSize;
                    }
                }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkBananas() throws InterruptedException {

        if ((x[0] == bananaX) && (y[0] == bananaY)) {
            applesEaten=applesEaten+2;
            Events();
        }
    }

    public void move () {
        for(int i = bParts ; i > 0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U' : y[0] = y [0] - uSize;
            break;
            case 'D' : y[0] = y [0] + uSize;
            break;
            case 'L' : x[0] = x [0] - uSize;
            break;
            case 'R' : x[0] = x [0] + uSize;
            break;
        }

    }

    public void checkCollisions () {
        for (int i = bParts ; i > 0 ; i--) {
            if ((x[0] == x[i])&&(y[0] == y[i])) {
                running = false;
            }
        }
        if (x[0]<0) {
            running = false;
        }
        if ((x[0]>WIDTH-uSize)){
            running = false;
        }
        if (y[0]<0){
            running = false;
        }
        if ((y[0]>HEIGHT-uSize)){
            running = false;
        }

        if (!running){

            timer.stop();
        }
    }

    public void gameOver (Graphics g) throws LineUnavailableException, IOException {

        g.drawImage(back, 0, 0, this);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:" + applesEaten, (WIDTH - metrics1.stringWidth("Score:" + applesEaten)) / 2, g.getFont().getSize());


        playAgain.setFocusable(false);
        playAgain.setBounds(240, 350, 100, 50);
        playAgain.addActionListener(this);
        playAgain.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        playAgain.setBorder(BorderFactory.createEtchedBorder());
        playAgain.setBackground(Color.GRAY);
        playAgain.setForeground(Color.BLACK);
        playAgain.setBorder(border);
        this.add(playAgain);

        exit.setFocusable(false);
        exit.setBounds(240, 450, 100, 50);
        exit.addActionListener(this);
        exit.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        exit.setBorder(BorderFactory.createEtchedBorder());
        exit.setBackground(Color.GRAY);
        exit.setForeground(Color.BLACK);
        exit.setBorder(border);
        this.add(exit);

        clip = AudioSystem.getClip();
        clip.open(inputStream);
        clip.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
            try {
                checkBananas();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        repaint();

        if (e.getSource() == playAgain) {
            StartFrame startFrame = new StartFrame();
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }

        if(e.getSource() == exit) {
            System.exit(0);
        }
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed (KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction !='L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction !='D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction !='U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
