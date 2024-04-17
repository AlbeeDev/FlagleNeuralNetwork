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
        Node []nodi = new Node[3];

        double []in= {400.0,50.0,300.0};
        double []normalin = normalize(in);
        for(int i=0; i<3; i++)
        	nodi[i]= new Node(in.length);
        
        Layer i = new  Layer(nodi, normalin);
        MatrixOperations.printMatrix(i.out());
        Node []nodi2 = new Node[5];
        
        double [][]out= i.out();
        double []in2= new double[3];
        for(int j=0; j<3; j++)
        	in2[j]=out[j][0];
        for(int j=0; j<5; j++)
        	nodi2[j]= new Node(in2.length);
        Layer l2= new Layer(nodi2,in2);
        
        
        System.out.println();
        MatrixOperations.printMatrix(l2.out());
        
        double [][]out2= l2.out();
        double []in3= new double[5];
        for(int j=0; j<5; j++)
        	in3[j]=out2[j][0];
        Node []nodi3 = {new Node(in3.length)};
        Layer l3= new Layer(nodi3,in3);
        System.out.println();
        MatrixOperations.printMatrix(l3.out());
        System.out.println(MatrixOperations.sigmoid(l3.out()[0][0]));
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static double[] normalize(double[] input) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        // Find the minimum and maximum values in the input array
        for (double value : input) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        // Normalize each input value to the range [0, 1]
        double[] normalizedInput = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            normalizedInput[i] = (input[i] - min) / (max - min);
        }

        return normalizedInput;
    }
}