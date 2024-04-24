package flappybird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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

    //private Bird bird;
    private Pipe[] pipes;
    private Timer timer;

    ArrayList<Bird> list = new ArrayList<Bird>();
    
    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.CYAN);
        
        for(int i =0; i<50 ; i++)
        	list.add(new Bird(new Brain()));
        
        //Brain b = new Brain();
        //System.out.println(b.calcola(in));
       
        //bird = new Bird(b);
        pipes = new Pipe[NUM_PIPES];
        for (int i = 0; i < NUM_PIPES; i++) {
            int pipeX = WIDTH + i * 300;
            int pipeY = (int) (Math.random() * (HEIGHT - PIPE_GAP));
            pipes[i] = new Pipe(pipeX, pipeY);
        }

        timer = new Timer(20, this);
        timer.start();

     /*   addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                   // bird.jump();
                }
            }
        });*/
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        for(int i =0; i<50 ; i++)
        	if(list.get(i).isAlive()) list.get(i).draw(g);

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 20, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	for(int k=0;k<list.size();k++) {
    		double [] in = new double[3];
    		in[0]=pipes[list.get(k).getNextTube()].getX()-list.get(k).getX()/1000;
    		in[1]=//altezza tubo
    		in[2]= //altezza bird		
    		list.get(k).update();
    	}
    	
		
		for (int i=0; i<pipes.length;i++) {
            pipes[i].move();
            
            for(int k=0;k<list.size();k++) {
            	if (pipes[i].intersects(list.get(k).getBounds())) {
                	list.get(k).die();
                }
                	
                if (pipes[i].isPassedBy(list.get(k).getX())) {
                	list.get(k).nextTube(i);
                    score++;
                }
        	}
        }
		
		for(int k=0;k<list.size();k++) {
			if (list.get(k).getBirdY() >= HEIGHT - Bird.BIRD_SIZE) {
	        	list.get(k).setBirdY(HEIGHT - Bird.BIRD_SIZE);
	        } else if (list.get(k).getBirdY() <= 0) {
	        	list.get(k).setBirdY(0);
	        }
    	}
        
         	

        

        repaint();
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
        System.exit(0);
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
     
        
    }
    
}
