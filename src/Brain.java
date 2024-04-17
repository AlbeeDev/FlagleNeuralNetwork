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

class Brain{

}

