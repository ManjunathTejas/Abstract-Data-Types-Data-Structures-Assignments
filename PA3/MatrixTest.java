class MatrixTest{
	public static void main (String[] args){
	Matrix A = new Matrix(10);
    Matrix B = new Matrix(10);
    Matrix C = new Matrix(10);
 		A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 3);
        A.changeEntry(2, 1, 4);
        A.changeEntry(2, 2, 5);
        A.changeEntry(2, 3, 6);
        A.changeEntry(3, 1, 7);
        A.changeEntry(3, 2, 8);
        A.changeEntry(3, 3, 9);
        B.changeEntry(1, 1, 1);
        B.changeEntry(2, 2, 1);
        C = A.mult(B);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
	}
}
