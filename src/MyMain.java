import javax.swing.*;

public class MyMain {
    public static void main(String[] args) {
        double[][] matrixA = {
                {1.0, 1.0, 1.0},
                {2.0, 2.0, 2.0},
                {3.0, 3.0, 3.0}
        };

        double[][] matrixB = {
                {1.0},
                {1.0},
                {1.0}
        };

        double[][] result = MatrixOperations.multiply(matrixA, matrixB);
       /* System.out.println("matirce:");
        MatrixOperations.printMatrix(result);
    */


        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
