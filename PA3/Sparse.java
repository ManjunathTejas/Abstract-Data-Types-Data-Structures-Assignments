//Tejas Manjunath
//Matrix.java
//PA3
//tmanjuna
//1536271
//Sparse.java holds the Matrix ADT
import java.util.Scanner;
import java.io.*;

class Sparse{
public static void main(String[] args) throws IOException{

	Scanner in = null;
	PrintWriter out = null;

	if(args.length != 2){
		System.err.println("Usage: Sparse infile outfile");
		System.exit(1);
	}

	in = new Scanner(new File(args[0]));		//Initialize in and out
	out = new PrintWriter(new FileWriter(args[1]));

	int dimensions = in.nextInt();

	Matrix A = new Matrix(dimensions);
	Matrix B = new Matrix(dimensions);

	int A_entries = in.nextInt();
	int B_entries = in.nextInt();

	for(int i = 0; i < A_entries; i++){
		int rowA = in.nextInt();
		int colA = in.nextInt();
		double valA = in.nextDouble();
		A.changeEntry(rowA, colA, valA);
	}

	for(int j = 0; j < B_entries; j++){
		int rowB = in.nextInt();
		int colB = in.nextInt();
		double valB = in.nextDouble();
		B.changeEntry(rowB, colB, valB);
	}

	//A NNZ
	out.println("A has " + A.getNNZ() + "non-zero entries:");
	out.println(A);
	out.println();

	//B NNZ
	out.println("B has " + B.getNNZ() + "non-zero entries:");
	out.println(B);
	out.println();

	//(1.5)*A
	out.println("(1.5)*A = ");
	out.println(A.scalarMult(1.5));
	out.println();

	//A+B
	out.println("A+B = ");
	out.println(A.add(B));
	out.println();

	//A+A
	out.println("A+A = ");
	out.println(A.add(A));
	out.println();

	//B-A
	out.println("B-A = ");
	out.println(B.sub(A));
	out.println();

	//A-A
	out.println("A-A = ");
	out.println(A.sub(A));
	out.println();

	//Transpose(A) = 
	out.println("Transpose(A) = ");
	out.println(A.transpose());
	out.println();

	//A*B
	out.println("A*B = ");
	out.println(A.mult(B));
	out.println();

	//B*B
	out.println("B*B = ");
	out.println(B.mult(B));
	out.println();

	in.close();
	out.close();
}
}