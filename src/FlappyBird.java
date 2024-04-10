import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FlappyBird extends JPanel implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int PIPE_WIDTH = 50;
    private static final int PIPE_GAP = 250;
    private static final int BIRD_SIZE = 40;
    private static final int BIRD_START_X = 100;
    private static final int GRAVITY = 1;
    private static final int JUMP_HEIGHT = -15;
    private static final int NUM_PIPES = 3;
    private int score;

    private int birdY;
    private int birdVelocity;
    private Pipe[] pipes;
    private Timer timer;
    private boolean isJumping;

    private Image birdImage;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);

        birdImage = null;
        //birdImage = ImageIO.read(new File("bird1.png"));

        birdY = HEIGHT / 2;
        birdVelocity = 0;

        pipes = new Pipe[NUM_PIPES];
        for (int i = 0; i < NUM_PIPES; i++) {
            int pipeX = WIDTH + i * 300;
            int pipeY = (int) (Math.random() * (HEIGHT - PIPE_GAP));
            pipes[i] = new Pipe(pipeX, pipeY);
        }

        timer = new Timer(20, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isJumping = true;
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        //g.drawImage(birdImage, BIRD_START_X, birdY, BIRD_SIZE, BIRD_SIZE, null);
        g.setColor(Color.BLACK);
        g.drawRect(BIRD_START_X, birdY,BIRD_SIZE, BIRD_SIZE);

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        birdVelocity += GRAVITY;
        birdY += birdVelocity;

        if (isJumping) {
            birdVelocity = JUMP_HEIGHT;
            isJumping = false;
        }

        for (Pipe pipe : pipes) {
            pipe.move();
        }

        // Check for passing pipes and update score
        for (Pipe pipe : pipes) {
            if (pipe.getX() + PIPE_WIDTH == BIRD_START_X) {
                score++;
            }
        }

        if (pipes[0].getX() + PIPE_WIDTH < 0) {
            // Move the first pipe to the end of the array and reset its position
            Pipe firstPipe = pipes[0];
            System.arraycopy(pipes, 1, pipes, 0, pipes.length - 1);
            pipes[pipes.length - 1] = firstPipe;
            firstPipe.reset(WIDTH + PIPE_WIDTH * (NUM_PIPES - 1), (int) (Math.random() * (HEIGHT - PIPE_GAP)));
        }

        if (birdY >= HEIGHT - BIRD_SIZE) {
            birdY = HEIGHT - BIRD_SIZE;
        } else if (birdY <= 0) {
            birdY = 0;
        }

        for (Pipe pipe : pipes) {
            if (pipe.intersects(BIRD_START_X, birdY, BIRD_SIZE, BIRD_SIZE)) {
                gameOver();
            }
        }

        repaint();
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
        System.exit(0);
    }



    private class Pipe {
        private int x;
        private int y;

        public Pipe(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void move() {
            x -= 5;
        }

        public void draw(Graphics g) {
            g.setColor(Color.GREEN);
            g.fillRect(x, 0, PIPE_WIDTH, y);
            g.fillRect(x, y + PIPE_GAP, PIPE_WIDTH, HEIGHT - (y + PIPE_GAP));
        }

        public boolean intersects(int birdX, int birdY, int birdWidth, int birdHeight) {
            return x < birdX + birdWidth && x + PIPE_WIDTH > birdX && (birdY < y || birdY + birdHeight > y + PIPE_GAP);
        }

        public void reset(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
