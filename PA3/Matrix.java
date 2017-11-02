//Tejas Manjunath
//Matrix.java
//PA3
//tmanjuna
//1536271
//Matrix.java holds the Matrix ADT
class Matrix{

	//Create a private Entry class
	private static class Entry{
		int col_num;
		double val;

		Entry(int col_num, double val){
			this.col_num = col_num;
			this.val = val;
		}

		public String toString(){
			return "("+String.valueOf(col_num)+", "+String.valueOf(val)+")";
		}

		public boolean equals(Object x){
			boolean eq = false;
			Entry that;
			if(x instanceof Entry){
				that = (Entry) x;
				eq = (this.col_num==that.col_num && this.val == that.val);
			}
			return eq;
		}
	}

	int mat_size = 0;										//store the matrix size 
	List arr[];

	//Constructor to create the Matrix
	Matrix(int n){
		mat_size = n;
		arr = new List[n];
		for(int i = 0; i < n; i++){
			arr[i] = new List();
		}
	}

	//Returns the dimensions of the matrix to be created
	int getSize(){
		return mat_size;
	}

	//Returns the non zero count of every row in the array 
	int getNNZ(){
		int non_zero_count = 0;
		for(int i = 0 ; i < mat_size; i++){
			non_zero_count += arr[i].length();
		}
		return non_zero_count;
	}
	

	//Returns true or false if two matrices are equal
	public boolean equals(Object x){
		if(!(x instanceof Matrix)){
			throw new RuntimeException("Object is not a matrix");
		}

		Matrix M = (Matrix)x;
		if(getSize() != M.getSize()){								//checks if the array has the same length as 
			return false;
		}else{
			for(int i = 0 ; i < mat_size; i++){
				if(arr[i].length() != M.arr[i].length()){			// check if they are the same dimensions
					return false;
				}else{
					if(arr[i].equals(M.arr[i])){

					}else{
						return false;
					}
				}
			}
		}
		return true;
	}

	//Gets the matrix into its initial uninitialized state
	void makeZero(){
		for(int i = 0 ; i < mat_size; i++){
			arr[i].clear();
		}
	}

	//Copy a matrix into a matrix copy
	Matrix copy(){
		Matrix MatrixCopy = new Matrix(mat_size);
		Entry E;
		for(int i = 0; i < mat_size; i++){
			arr[i].moveFront();
			//E = (Entry)arr[i].front();
			while(arr[i].index() != -1){
				E = new Entry(((Entry)arr[i].get()).col_num,((Entry)arr[i].get()).val);
				MatrixCopy.arr[i].append(E);
				arr[i].moveNext();
			}
		}
		return MatrixCopy;
	}

	//Goes to the ith - 1 row and goes to the jth column and changes the value
	void changeEntry(int i, int j, double x){
		if(i < 1||i >getSize()||j < 1||j > getSize()){
			throw new RuntimeException(" row and column not valid");
		}

		Entry temp = new Entry(j,x);									//If the Entry needs to be added
		i = i -1;
		if(arr[i].length() == 0){										//When the row is empty add an entry
			if(x != 0){
				arr[i].append(temp);
			}else{
				return;
			}
		}else{
			arr[i].moveFront();
			if(temp.col_num < ((Entry)arr[i].get()).col_num){			//if the column number of the new Entry is smaller than the front of the List 
				if( x != 0 ){
					arr[i].prepend(temp);
				}else{
					return;
				}
			}else if(temp.col_num == ((Entry)arr[i].get()).col_num){	//if the column number of the new Entry is the same change the value
				if(x == 0){
					arr[i].delete();
				}else{
					((Entry)arr[i].get()).val = x;
				}
			}else{
				arr[i].moveBack();
				if(temp.col_num > ((Entry)arr[i].get()).col_num){
					if( x != 0 ){
						arr[i].append(temp);
					}else{
						return;
					}
				}else if(temp.col_num == ((Entry)arr[i].get()).col_num){
					if(x == 0){
						arr[i].delete();
					}else{
						((Entry)arr[i].get()).val = x;
					}
				}else{
					arr[i].moveFront();
					while(((Entry)arr[i].get()).col_num <j && x != 0){
						arr[i].moveNext();
					}
					if(temp.col_num == ((Entry)arr[i].get()).val){
						if(x == 0){
							arr[i].delete();
						}else{
							((Entry)arr[i].get()).val = x;
						}
					}else if( x != 0){
						arr[i].insertBefore(temp);
					}
				}
			}
		}
	}

	Matrix scalarMult(double x){
		Matrix scalarMatrix = new Matrix(mat_size);
		for(int i = 0; i < mat_size; i++){
			arr[i].moveFront();
			while(arr[i].index() != -1){
				int scal_col_num = ((Entry)arr[i].get()).col_num;
				double scal_val	= x *((Entry)arr[i].get()).val;
				Entry scalarEntry = new Entry(scal_col_num, scal_val);
				scalarMatrix.arr[i].append(scalarEntry);
				arr[i].moveNext();
			}
		}
		
		return scalarMatrix;
	}

	Matrix add(Matrix M){
		if(M.getSize()!= mat_size){
			throw new RuntimeException("Matrix sizes are not the same");
		}
		int a = 0;														//Initialize a to hold the first arrays column #
		int b = 0;														//Initialize b to hold the 2nd array column #
		Matrix T = copy();												//A copy of this Matrix to take care of the edge case that two matrices are equal to each other
		Matrix sumMat = new Matrix(mat_size);							//The new sum matrix 
		if(equals(M)){													//If two matrices are equal to each other, their columns are the same
			for(int i = 0 ; i < mat_size; i++){	
			arr[i].moveFront();
			T.arr[i].moveFront();
				for(int j = 1; j <= mat_size; j++){						//column number
					if(arr[i].index() != -1){
						a = ((Entry)arr[i].get()).col_num;				//column number for first matrix
					}else{
						a = 0;
					}

					if(T.arr[i].index() != -1){
						b = ((Entry)T.arr[i].get()).col_num;			//column number for second matrix
					}else{
						b = 0;
					}
					if(a == j && b == j){								//Both matrices have existing columns j
						double sum_val = ((Entry)arr[i].get()).val + ((Entry)T.arr[i].get()).val;
						if(sum_val != 0){
							Entry temp = new Entry(j,sum_val);
							sumMat.arr[i].append(temp);
						}
						arr[i].moveNext();
						T.arr[i].moveNext();
					}
				}
			}	
		}else{															//The else takes care of two matrices that are not equal
			for(int i = 0 ; i < mat_size; i++){
			arr[i].moveFront();
			M.arr[i].moveFront();
				for(int j = 1; j <= mat_size; j++){						
					if(arr[i].index() != -1){
						a = ((Entry)arr[i].get()).col_num;				//column number for first matrix
					
					}else{
						a = 0;
					}

					if(M.arr[i].index() != -1){
						b = ((Entry)M.arr[i].get()).col_num;			//column number for second matrix
					
					}else{
						b = 0;
					}

					if(a == j && b == j){								//Both matrices have existing columns j
						double sum_val = ((Entry)arr[i].get()).val + ((Entry)M.arr[i].get()).val;
						if(sum_val != 0){
							Entry temp = new Entry(j,sum_val);
							sumMat.arr[i].append(temp);
						}
						arr[i].moveNext();
						M.arr[i].moveNext();
					}else if (a == j && b != j){
						double sum_val = ((Entry)arr[i].get()).val;
						Entry temp = new Entry(j, sum_val);
						sumMat.arr[i].append(temp);
						arr[i].moveNext();
					}else if(a != j && b == j){
						double sum_val = ((Entry)M.arr[i].get()).val;
						Entry temp = new Entry(j, sum_val);
						sumMat.arr[i].append(temp);
						M.arr[i].moveNext();
					}else{

					}
				}
			}
		}
		return sumMat;
	}

	Matrix sub(Matrix M){
	
		if(M.getSize()!= mat_size){
			throw new RuntimeException("Matrix sizes are not the same");
		}
		int a = 0;														//Initialize a to hold the first arrays column #
		int b = 0;														//Initialize b to hold the 2nd array column #
		Matrix T = copy();												//A copy of this Matrix to take care of the edge case that two matrices are equal to each other
		Matrix sumMat = new Matrix(mat_size);							//The new sum matrix 
		if(equals(M)){													//If two matrices are equal to each other, their columns are the same
			sumMat.makeZero();
			return sumMat;
		}else{
			for(int i = 0 ; i < mat_size; i++){
			arr[i].moveFront();
			M.arr[i].moveFront();
				for(int j = 1; j <= mat_size; j++){						
					if(arr[i].index() != -1){
						a = ((Entry)arr[i].get()).col_num;				//column number for first matrix
					
					}else{
						a = 0;
					}

					if(M.arr[i].index() != -1){
						b = ((Entry)M.arr[i].get()).col_num;			//column number for second matrix
					
					}else{
						b = 0;
					}

					if(a == j && b == j){								//Both matrices have existing columns j
						double sum_val = ((Entry)arr[i].get()).val - ((Entry)M.arr[i].get()).val;
						if(sum_val != 0){
							Entry temp = new Entry(j,sum_val);
							sumMat.arr[i].append(temp);
						}
						arr[i].moveNext();
						M.arr[i].moveNext();
					}else if (a == j && b != j){
						double sum_val = ((Entry)arr[i].get()).val;
						Entry temp = new Entry(j, sum_val);
						sumMat.arr[i].append(temp);
						arr[i].moveNext();
					}else if(a != j && b == j){
						double sum_val = -((Entry)M.arr[i].get()).val;
						Entry temp = new Entry(j, sum_val);
						sumMat.arr[i].append(temp);
						M.arr[i].moveNext();
					}else{

					}
				}
			}
		}
		return sumMat;
	}

	Matrix transpose(){
		Matrix T = new Matrix(mat_size);
		for(int i = 0 ; i < mat_size; i++){
			for(arr[i].moveFront(); arr[i].index() != -1; arr[i].moveNext()){
				Entry temp = (Entry)arr[i].get();
				T.changeEntry(temp.col_num,i+1,temp.val); 
			}
		}
		return T;
	}

	static double dotProduct(List a, List b){
		double runningSum = 0;	//holds the running sum of the dot product of 2 lists
		int x = 0;
		int y = 0;
		int j = 1;	//column counter
		a.moveFront();
		b.moveFront();
		while(a.index() != -1 || b.index() != -1){						
			if(a.index() != -1){
				x = ((Entry)a.get()).col_num;				//column number for first matrix
				//System.out.println(x);		
			}else{
				x = 0;
			}

			if(b.index() != -1){
				y = ((Entry)b.get()).col_num;			//column number for second matrix
				//System.out.println(y);					
			}else{
				y = 0;
			}

			if(x == j && y == j){								//Both matrices have existing columns j
				runningSum += ((Entry)a.get()).val * ((Entry)b.get()).val;
				a.moveNext();
				b.moveNext();
			}else if (x == j && y != j){
				a.moveNext();
			}else if(x != j && y == j){
				b.moveNext();
			}else{

			}
			j++;
		}
		return runningSum;		
	}

	Matrix mult(Matrix M){
		double temp_val = 0;
		Matrix mult_Mat = new Matrix(mat_size);
		Matrix M_tran = M.transpose();
		for(int i = 0; i < mat_size; i++){
			for(int j = 0; j < mat_size; j++){
				temp_val = dotProduct(arr[i],M_tran.arr[j]);
				mult_Mat.changeEntry(i+1,j+1,temp_val);
			}
			//multMat.arr[i].a
		}
		return mult_Mat;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < mat_size; i++){
			if(arr[i].length() != 0){
				int j = i + 1;
				sb.append(j +": ");
				arr[i].moveFront();
				while(arr[i].index() != -1){
					if(arr[i].index() == arr[i].length() - 1){
						sb.append(arr[i].get() + "\n");
						arr[i].moveNext();
					}else{
						sb.append(arr[i].get() + " ");
						arr[i].moveNext();
					}
				}
			}
		}
		return new String(sb);
	}



}