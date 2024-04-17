import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlappyBird extends JPanel implements ActionListener {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 950;
    public static final int PIPE_WIDTH = 50;
    public static final int PIPE_GAP = 250;
    public static final int NUM_PIPES = 5;
    public int score;

    private Bird bird;
    private Pipe[] pipes;
    private Timer timer;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);

        bird = new Bird();
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
                    bird.jump();
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

        bird.draw(g);

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.update();

        for (Pipe pipe : pipes) {
            pipe.move();

            if (pipe.intersects(bird.getBounds())) {
                gameOver();
            }

            if (pipe.isPassedBy(bird.getX())) {
                score++;
            }
        }

        if (bird.getBirdY() >= HEIGHT - Bird.BIRD_SIZE) {
            bird.setBirdY(HEIGHT - Bird.BIRD_SIZE);
        } else if (bird.getBirdY() <= 0) {
            bird.setBirdY(0);
        }

        repaint();
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
        System.exit(0);
    }
}
