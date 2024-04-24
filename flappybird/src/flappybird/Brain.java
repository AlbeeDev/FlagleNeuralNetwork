package flappybird;

class Node{
	double []pesi;
	public Node(int numInput) {
		super();
		pesi= new double[numInput];
		for(int i =0 ;i <numInput; i++)
			pesi[i]=Math.random();
	}
	
}

class Layer{
	int nNode;
	int nInput;
	double [][]mat;
	double[][]input;
	public Layer(Node[] nodes, double[] input) {
		super();
		this.nNode = nodes.length;
		this.nInput = input.length;
		mat = new double[nNode][nInput];
		this.input= new double[nInput][1];
		for(int i=0; i<input.length; i++)
			this.input[i][0]= input[i];
		for(int i =0 ; i<nNode; i++){
			for(int j =0; j<nInput; j++)
				mat[i][j]=nodes[i].pesi[j]; 
		}
	}
	public double[][] out() {
		return MatrixOperations.multiply2(mat, input);
		
	}
	
}

public class Brain {
	
	 public double calcola(double[] in){
		 Node []nodi = new Node[3];
	    
	      for(int i=0; i<3; i++)
	      	nodi[i]= new Node(in.length);
	      
	      Layer i = new  Layer(nodi, in);
	     // MatrixOperations.printMatrix(i.out());
	      Node []nodi2 = new Node[5];
	      
	      double [][]out= i.out();
	      double []in2= new double[3];
	      for(int j=0; j<3; j++)
	      	in2[j]=out[j][0];
	      for(int j=0; j<5; j++)
	      	nodi2[j]= new Node(in2.length);
	      Layer l2= new Layer(nodi2,in2);
	      
	      
	     // System.out.println();
	   //   MatrixOperations.printMatrix(l2.out());
	      
	      double [][]out2= l2.out();
	      double []in3= new double[5];
	      for(int j=0; j<5; j++)
	      	in3[j]=out2[j][0];
	      Node []nodi3 = {new Node(in3.length)};
	      Layer l3= new Layer(nodi3,in3);
	      System.out.println();
	     // MatrixOperations.printMatrix(l3.out());
	     // System.out.println(MatrixOperations.sigmoid(l3.out()[0][0]));
	      return l3.out()[0][0];
	 }
	 
	 boolean jump(double[] in) {
		 double x = calcola(in);
		 System.out.println(x);
		return (x>1.0)? true : false;
		 
	 }
}
