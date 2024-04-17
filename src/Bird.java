import java.awt.*;

public class Bird {
    public static final int BIRD_SIZE = 40;
    public static final int BIRD_START_X = 100;
    public static final int GRAVITY = 1;
    public static final int JUMP_HEIGHT = -15;
    public int birdY;
    public int birdVelocity;
    public boolean isJumping;

    public Bird() {
        birdY = FlappyBird.HEIGHT / 2;
        birdVelocity = 0;
    }

    public void jump() {
        isJumping = true;
    }

    public void update() {
        birdVelocity += GRAVITY;
        birdY += birdVelocity;

        if (isJumping) {
            birdVelocity = JUMP_HEIGHT;
            isJumping = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(BIRD_START_X, birdY, BIRD_SIZE, BIRD_SIZE);
    }

    public Rectangle getBounds() {
        return new Rectangle(BIRD_START_X, birdY, BIRD_SIZE, BIRD_SIZE);
    }

    public int getX() {
        return BIRD_START_X;
    }

    public int getBirdY() {
        return birdY;
    }

    public void setBirdY(int birdY) {
        this.birdY = birdY;
    }

    public int getBirdVelocity() {
        return birdVelocity;
    }

    public void setBirdVelocity(int birdVelocity) {
        this.birdVelocity = birdVelocity;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

}
