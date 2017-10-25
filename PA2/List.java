//Tejas Manjunath
//List.java
//PA1
//tmanjuna
//1536271
//List.java has a bunch of methods to manipulate a Linked List
class List{
	
	//Create private Node class
	private class Node{
		int info;
		Node next;
		Node prev;

		Node(int info){
			this.info = info;
			next = null;
			prev = null;
		}

		public String toString(){
			return String.valueOf(info);
		}

		public boolean equals(Object x){
			boolean eq = false;
			Node that;
			if(x instanceof Node){
				that = (Node) x;
				eq = (this.info==that.info);
			}
			return eq;
		}
	}

	private  Node front;
	private  Node back;
	private  Node cursor;
	private  int length;
	private  int cursor_index;

	public List(){
		front = back = cursor = null;
		length = 0;
		cursor_index = 0;
	}

	//Length of the Linked List
	int length(){
		return length;
	}

	//Location of the cursor
	int index(){
		if(cursor != null){
			return cursor_index;
		}else{
			return -1;
		}
	}

	//Move the cursor to the front of the List
	int front(){
		if(length()!= 0){
			return front.info;
		}else{
			throw new RuntimeException("length is not greater than 0");
		}
	}

	//Move the cursor to the end of the List
	int back(){
		if(length() != 0){
			return back.info;
		}else{
			throw new RuntimeException("length is not greater than 0");
		}
	}

	//Get the data of the node where the cursor is
	int get(){
		if(length() != 0 && index() >=0 ){
			return cursor.info;
		}else{
			throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
		}
	}

	//Checks to see if two Lists are the same
	boolean equals(List L){
		if(length() != L.length()){
			return false;
		}
		cursor = front;
		cursor_index = 0;
		boolean flag = true;
		Node x = cursor;

		L.cursor = L.front;
		L.cursor_index = 0;
		Node y = L.cursor;
		while(x != null && y != null){
			if(x.info != y.info){
				return false;
			}
			x = x.next;
			y = y.next;
		}
		return flag;
	}

	//Clears the entire list
	void clear(){
		if(length() == 0){
			front = null;
			back = null;
			cursor = null;
			cursor_index = -1;
		}else{
		
		front.next = null;
		back.prev = null;
		front = null;
		back = null;
		cursor = null;
		cursor_index = -1;
		length=0;
		}
	}

	//Checks if the List is empty
	boolean isEmpty() {
		return length==0;
	}

	//Checks the length of the list
	int getLength(){
		return length;
	}

	//Checks the front node data
	int getFront(){
		if(this.isEmpty()){
			throw new RuntimeException("Queue Error: getFront() called on empty Queue");
		}
		return front.info;
	}

	//Move the cursor to the front
	 void moveFront(){
		if(length != 0){
		cursor = front;
		cursor_index = 0;
		}
	}

	//Move the cursor to the back
	 void moveBack(){
	 	if(length != 0){
	 		cursor = back;
	 		cursor_index = length -1;
	 	}
	}

	//Move the cursor to the previous node
	 void movePrev(){
		if(cursor != null && cursor != front){
			cursor = cursor.prev;
			cursor_index --;
		}else if(cursor != null && cursor == front){
			cursor = null;
			cursor_index= -1;

		}
	}

	//Move the cursor to the next node
	 void moveNext(){
		if(cursor != null && cursor != back){
			cursor = cursor.next;
			cursor_index++;
		}else if(cursor != null && cursor == back){
			cursor = null;
			cursor_index = -1;
		}
	}

	//Adds a node to the beginning of the list
	 void prepend(int info){
		Node temp = new Node(info);
		if(length == 0){
			front = back = temp;
			length++;
			cursor_index++;
		}else{
			temp.next = front;
			front.prev = temp;
			front = temp;
			length++;
			cursor_index++;
		}
	}

	//Adds a node to the end of a list
	void append(int info){
		 Node temp = new Node(info);
		if(length == 0){
			front = back = temp;
			length++;
		}else{
			this.back.next = temp;
			temp.prev = this.back;
			this.back = temp;
			length++;
		}
	}

	//Insert a node before the cursor
	 void insertBefore(int info){
	 	if(length() == 0 && index() < 0){
	 		throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
	 	}
		Node temp = new Node(info);
		if(cursor == front){
		prepend(info);
		}else{
			temp.prev = cursor.prev;
			temp.next = cursor;	
			cursor.prev.next = temp;
			temp.next = cursor;
			cursor.prev = temp;
			length++;
			cursor_index++;
		}
	}

	//Insert a node after a cursor
	 void insertAfter(int info){
		if(length() == 0 && index() < 0){
	 		throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
	 	}
		Node temp = new Node(info);
		if(cursor == back){
			cursor.next = temp;
			temp.prev = cursor;
			temp.next = null;
			back = temp;
			length++;
			
		}else{
			(cursor.next).prev = temp;
			temp.prev = cursor;
			temp.next = cursor.next;
			cursor.next = temp;
			length++;
		}
	}

	//Delete the front of the list node
	 void deleteFront(){
	 	if(length() <= 0){
	 		throw new RuntimeException("length is not greater than 0 ");
	 	}
	 	if(length() == 1){
	 		front = null;
	 		back = null;
			length--;
			cursor_index = -1;
			return;
	 	}else{
		Node temp = front;
		front = front.next;
		temp.next = null;
		length--;
		cursor_index--;
		}
			
	}

	//Delete the back node of the list
	 void deleteBack(){
	 	if(length() <= 0){
	 		throw new RuntimeException("length is not greater than 0 ");
	 	}
		if(length() == 1){
			front = null;
			back = null;
			length--;
		}else{
			if(cursor == back){
				cursor = null;
				cursor_index = -1;
			}
		back = back.prev;
		back.next = null;
		length--;
		}

	}

	//Deletes the cursor node
	 void delete(){
		if(cursor == front){
			front = cursor.next;
			front.prev = null;
			cursor = null;
			cursor_index = -1;
			length--;
			return;
		}else if(cursor == back){
			back = cursor.prev;
			back.next = null;
			cursor = null;
			cursor_index = -1;
			length--;
			return; 
		}
		cursor.prev.next = cursor.next;
		cursor.next.prev = cursor.prev;
		cursor = null;
		cursor_index = -1;
		length--;
	}

	//Prints out the entire list
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Node N = front; N != null; N = N.next){
			if(N.next == null){
			sb.append(N.toString());
			}else{
			sb.append(N.toString());
			sb.append(" ");
			}
		}
		return new String(sb);
	}

	//Creates a copy of the list
	 List copy(){
		List listCopy = new List();
		Node N = this.front;
		while(N != null){
			listCopy.append(N.info);
			N = N.next;
		}
		return listCopy;
	}	
}
