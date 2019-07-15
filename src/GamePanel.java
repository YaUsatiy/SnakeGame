import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GamePanel extends JPanel implements ActionListener {

    final int DOT_SIZE = 16;
    final int SIZE = 320;
    final int ALL_DOTS = 400;
    private Image apple;
    private Image dot;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean inGame;
    private boolean left;
    private boolean right = true;
    private boolean down;
    private boolean up;
    private boolean paused = false;

    public void loadImages(){
        ImageIcon iconApple = new ImageIcon("apple.png");
        apple = iconApple.getImage();
        ImageIcon iconDot = new ImageIcon("dot.png");
        dot = iconDot.getImage();
    }

    public void initialize(){
        inGame = true;
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = DOT_SIZE*3 - DOT_SIZE*i;
            y[i] = DOT_SIZE*3;
        }
        timer = new Timer(150,this);
        timer.start();
        createApple();
    }

    public void createApple(){
        appleX = new Random().nextInt(19)*DOT_SIZE;
        appleY = new Random().nextInt(19)*DOT_SIZE;
    }


    public GamePanel(){
        setBackground(Color.BLACK);
        setSize(SIZE,SIZE);
        loadImages();
        initialize();
        addKeyListener(new PanelKeyListener());
        setFocusable(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame){
            checkApple();
            checkCollisions();
            checkBounds();
            move();
        }
        repaint();
    }

    public void checkApple(){
        if ( (x[0] == appleX) && (y[0] == appleY) ) {
            if (AuxiliaryWindow.medium) {
                dots++;
                createApple();
                if (timer.getDelay() > 100)
                    timer.setDelay(timer.getDelay() - 10);
                else if (timer.getDelay() > 84)
                    timer.setDelay(timer.getDelay() - 4);
                else timer.setDelay(timer.getDelay() - 2);
            }
            else if (AuxiliaryWindow.easy){
                dots++;
                createApple();
            }
            else if (AuxiliaryWindow.difficult){
                dots++;
                createApple();
                timer.setDelay(timer.getDelay()-10);
            }
        }
    }

    public void checkCollisions(){
        if ( (dots > 4) ){
            for (int i = 1; i < dots; i++) {
                if ((x[i] == x[0]) && (y[i] == y[0])){
                    inGame = false;
                    return;
                }

            }
        }
    }

    public void checkBounds(){
        if ( (x[0] > SIZE) ||  (y[0] > SIZE || (x[0] < 0) || (y[0] < 0))){
            inGame = false;
        }
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left){
            x[0] -= DOT_SIZE;
        }
        else if (right){
            x[0] += DOT_SIZE;
        }
        else if (up){
            y[0] -= DOT_SIZE;
        }
        else if (down){
            y[0] += DOT_SIZE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        else {
            /*String str = "Game over!!!";
            Font font = new Font("Monotype Corsiva",Font.BOLD, 18);
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString(str,SIZE/2 - 45,SIZE/2 - 9);*/

            timer.stop();

            JFrame repeatFrame = new JFrame("Would you like to repeat game?");
            repeatFrame.setSize(400,200);
            JPanel panel = new JPanel();
            panel.setLayout(null);

            JButton yesBtn = new JButton("Of course I want!!!");
            yesBtn.setBounds(50,30,180,100);
            JButton noBtn = new JButton("NO");
            noBtn.setBounds(250,30,100,100);
            panel.add(yesBtn);
            panel.add(noBtn);

            repeatFrame.add(panel);
            repeatFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            repeatFrame.setResizable(false);
            repeatFrame.setLocationRelativeTo(this);
            repeatFrame.setVisible(true);

            yesBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    regame();
                    initialize();
                    repeatFrame.dispose();
                }
            });

            noBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
    }

    public void regame(){
        for (int i = 0; i <= dots; i++) {
            x[i] = 0;
        }
        for (int i = 0; i <= dots; i++) {
            y[i] = 0;
        }
        right = true;
        left = false;
        down = false;
        up = false;
    }

    class PanelKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if ( (key == KeyEvent.VK_RIGHT) && (!left) ){
                right = true;
                up = false;
                down = false;
            }
            else if ( (key == KeyEvent.VK_LEFT) && (!right) ){
                left = true;
                up = false;
                down = false;
            }
            else if ( (key == KeyEvent.VK_UP) && (!down) ){
                up = true;
                left = false;
                right = false;
            }
            else if ( (key == KeyEvent.VK_DOWN) && (!up) ){
                down = true;
                left = false;
                right = false;
            }
            else if ( (key == KeyEvent.VK_SPACE) &&(!paused) ){
                timer.stop();
                paused = true;
            }
            else if ( (key == KeyEvent.VK_SPACE) &&(paused) ){
                timer.start();
                paused = false;
            }
        }
    }
}
