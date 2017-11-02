//Tejas Manjunath
//List.java
//PA3
//tmanjuna
//1536271
//List.java has a bunch of methods to manipulate a Linked List
class List{
	
	//Create private Node class
	private class Node{
		Object data;
		Node next;
		Node prev;

		Node(Object data){
			this.data = data;
			next = null;
			prev = null;
		}

		public String toString(){
			return String.valueOf(data);
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
	Object front(){
		if(length()!= 0){
			return front.data;
		}else{
			throw new RuntimeException("length is not greater than 0");
		}
	}

	//Move the cursor to the end of the List
	Object back(){
		if(length() != 0){
			return back.data;
		}else{
			throw new RuntimeException("length is not greater than 0");
		}
	}

	//Get the data of the node where the cursor is
	Object get(){
		if(length() != 0 && index() >=0 ){
			return cursor.data;
		}else{
			throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
		}
	}

	//Checks to see if two Lists are the same
	public boolean equals(List L){
		/*
		boolean eq = false;
        Node N, M;
        List L = (List) x;
        N = this.front();
        M = (Node) L.front();
        if (L.length() == this.length()) {
            eq = true;
            while (eq && N != null) {
                eq = (N.data.equals(M.data));
                N = N.next;
                M = M.next;
            }
        }
        return eq;
		*/
		
		//List L = (List)x;
		if(length() != L.length()){
			return false;
		}
		cursor = front;
		cursor_index = 0;
		//boolean flag = true;
		Node a = cursor;

		L.cursor = L.front;
		L.cursor_index = 0;
		Node b = L.cursor;
		while(a != null && b != null){
			if(a.data.equals(b.data)){
				a = a.next;
				b = b.next;
			}else{
				return false;
			}
		}
		return true;
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
	Object getFront(){
		if(this.isEmpty()){
			throw new RuntimeException("Queue Error: getFront() called on empty Queue");
		}
		return front.data;
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
	 void prepend(Object data){
		Node temp = new Node(data);
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
	void append(Object data){
		 Node temp = new Node(data);
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
	 void insertBefore(Object data){
	 	if(length() == 0 && index() < 0){
	 		throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
	 	}
		Node temp = new Node(data);
		if(cursor == front){
		prepend(data);
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
	 void insertAfter(Object data){
		if(length() == 0 && index() < 0){
	 		throw new RuntimeException("length is not greater than 0 and index is not greater than 0");
	 	}
		Node temp = new Node(data);
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
	 	if(length() == 1){
	 		front = null;
	 		back = null;
	 		cursor = null;
	 		cursor_index = -1;
	 		length--;
	 		return;
	 	}
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
}
