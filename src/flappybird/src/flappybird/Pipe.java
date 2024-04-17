package flappybird;
import java.awt.*;

public class Pipe {
    private static final int PIPE_GAP = 250;
    private static final int PIPE_WIDTH = 50;
    private int x;
    private int y;

    public Pipe(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        if(x+PIPE_WIDTH<=0){
            x = FlappyBird.WIDTH;
        }
        else {
            x -= 5;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, 0, PIPE_WIDTH, y);
        g.fillRect(x, y + PIPE_GAP, PIPE_WIDTH, FlappyBird.HEIGHT - (y + PIPE_GAP));
    }

    public boolean intersects(Rectangle birdBounds) {
        return x < birdBounds.x + birdBounds.width &&
                x + PIPE_WIDTH > birdBounds.x &&
                (birdBounds.y < y || birdBounds.y + birdBounds.height > y + PIPE_GAP);
    }

    public boolean isPassedBy(int birdX) {
        return x + PIPE_WIDTH == birdX;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
}