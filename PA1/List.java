class List{
	
	//Create private Node class
	private class Node{
		int info;
		Node next;

		Node(int info){
			this.info = info;
			next = null;
		}

		public String toString(){
			return String.valueOf(info);
		}

		public boolean equals(Object x){
			boolean eq = false;
			Node that;
			if(x instanceof Node){
				that = (Node) x;
				eq = (this.data==that.data);
			}
			return eq;
		}
	}

	private Node front;
	private Node back;
	private int length;

	Queue(){
		front = back = null;
		length = 0;
	}

	boolean isEmpty() {
		return length==0;
	}

	int getLength(){
		return length;
	}

	int getFront(){
		if(this.isEmpty()){
			throw new RuntimeException("Queue Error: getFront() called on empty Queue")
		}
		return front.info;
	}

	void append(int info){
		Node temp = new Node(info);
		if(this.isEmpty()){
			front = back = N;
		}else{
			back.next = temp;
			back = temp;
		}
		length;
	}

	void prepend(int info){
		Node temp = new Node(info);
		if(this.isEmpty()){
			front = back = N;
		}else{
			temp.next = front;
		}
	}
}