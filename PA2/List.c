//-----------------------------------------------------------------------------
// List.c
// Implementation file for List ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// structs --------------------------------------------------------------------

// private NodeObj type
typedef struct NodeObj{
   int data;
   struct NodeObj* next;
   struct NodeObj* prev;
} NodeObj;

// private Node type
typedef NodeObj* Node;

// private ListObj type
typedef struct ListObj{
   Node front;
   Node back;
   Node cursor;
   int cursor_index;
   int length;
} ListObj;


// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL; 
   N->prev = NULL; 
   return(N);
}


// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// newList()
// Returns reference to new empty List object.

List newList(void){
   List L;
   L = malloc(sizeof(ListObj));
   L->front = NULL;
   L->back = NULL;
   L->length = 0;
   L->cursor = NULL;
   L->cursor_index = -1;

   return(L);
}


// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NULL.S
void freeList(List* pL){
   if(pL!=NULL && *pL!=NULL) { 
      while( length(*pL)>0 ) { 
         deleteBack(*pL);	//changed 
      }
      free(*pL);
      *pL = NULL;
   }
}

// Access functions -----------------------------------------------------------

// length()
// Returns the length of L.
int length(List L){
   if( L == NULL ){
      printf("List Error: calling length() on NULL List reference\n");
      exit(1);
   }
   return(L->length);
}

//index()
//Returns the location of the cursor
int index(List L){
	if( L->cursor == NULL ){
		return -1;
   }else{
   		return L->cursor_index;
   }
}

// front()
// Returns the value at the front of L.
// Pre: !isEmpty(L)
int front(List L){
   if( L==NULL ){
      printf("List Error: calling front() on NULL List reference\n");
      exit(1);
   }
   if( length(L) == 0 ){
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
   return(L->front->data);
}

// back()
// Returns the value at the back of L.
// Pre: !isEmpty(L)
int back(List L){
   if( L==NULL ){
      printf("List Error: calling front() on NULL List reference\n");
      exit(1);
   }
   if( length(L) == 0 ){
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
   return(L->back->data);
}

int get(List L){
	if(length(L) != 0 && index(L) >= -1){
		return L->cursor->data;
	}else{
		printf("length is not greater than 0 and index is not greater than 0\n");
		exit(1);
	}
}

// equals()
// returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B){
   int eq = 0;
   Node N = NULL;
   Node M = NULL;

   if( A==NULL || B==NULL ){
      printf("List Error: calling equals() on NULL List reference\n");
      exit(1);
   }

   eq = ( A->length == B->length );
   N = A->front;
   M = B->front;
   while( eq && N!=NULL){
      eq = (N->data==M->data);
      N = N->next;
      M = M->next;
   }
   return eq;
}

// clear()
// Frees all heap memory associated with List *pL, and sets *pL to NULL.S
void clear(List L){

	if(L == NULL){
		printf("List Error calling clear on Null List\n");
		exit(1);
	}
	while(L->back != NULL){
		deleteBack(L);
	}
	L->front = NULL;
	L->back = NULL;
	L->length = 0;
	L->cursor_index = -1;
	L->cursor = NULL;
	
}

//Move the cursor to the front
void moveFront(List L){
	if(length(L) != 0){
		L->cursor = L->front;
		L->cursor_index = 0;
	}
}

//Move the cursor to the back
void moveBack(List L){
	if(length(L) != 0){
		L->cursor = L->back;
		L->cursor_index = length(L) - 1;
	}
}

//Move the cursor to the previous node
void movePrev(List L){
	if(L->cursor != NULL && L->cursor != L->front){
		L->cursor = L->cursor->prev;
		L->cursor_index--;
	}else if(L->cursor != NULL && L->cursor == L->front){
		L->cursor = NULL;
		L->cursor_index= -1;
	}
}

//Move the cursor to the next node
void moveNext(List L){
	if(L->cursor != NULL && L->cursor != L->back){
		L->cursor = L->cursor->next;
		L->cursor_index++;
	}else if(L->cursor != NULL && L->cursor == L->back){
		L->cursor = NULL;
		L->cursor_index = -1;
	}
}

//Adds a node to the beginning of the list
void prepend(List L, int data){
	Node temp = newNode(data);
	if(length(L) == 0){
		L->front = temp;
		L->back = temp;
		L->length++;
		if(L->cursor !=NULL){
			L->cursor_index++;
		}
	}else{
		temp->next = L->front;
		L->front->prev = temp;
		L->front = temp;
		L->length++;
		if(L->cursor !=NULL){
			L->cursor_index++;
		}
	}
	//freeNode(&temp);	//CHANGED
	//temp = NULL;
}

//Adds a node to the end of a list
void append(List L, int data){
	Node temp = newNode(data);
	if(L->length == 0){
		L->front = temp;
		L->back  = temp;
		L->length++;
	}else{
		L->back->next = temp;
		temp->prev = L->back;
		L->back = temp;
		L->length++;
	}
	//temp = NULL;
	//freeNode(&temp);	//CHANGED
}

//Insert a node before the cursor
void insertBefore(List L, int data){
	if(length(L) == 0 || index(L) < 0){
		printf("length is not greater than 0 or index is less than 0\n");
	}
	Node temp = newNode(data);
	if(L->cursor == L->front){
		prepend(L, data);
	}else{
		temp->prev = L->cursor->prev;
		temp->next = L->cursor;
		L->cursor->prev->next = temp;
		temp->next = L->cursor;
		L->cursor->prev = temp;
		L->length++;
		L->cursor_index++;
	}
	//temp = NULL;
	//freeNode(&temp);	//CHANGED

}

//Insert a node after the cursor
void insertAfter(List L, int data){
	if(length(L) == 0 || index(L) < 0){
		printf("length is not greater than 0 or index is less than 0");
	}
	Node temp = newNode(data);
	if(L->cursor == L->back){
		L->cursor->next = temp;
		temp->prev = L->cursor;
		temp->next = NULL;
		L->back = temp;
		L->length++;
	}else{
		L->cursor->next->prev = temp;
		temp->prev = L->cursor;
		temp->next = L->cursor->next;
		L->cursor->next = temp;
		L->length++;
	}
	//temp = NULL;
	//freeNode(&temp);	//CHANGED
}

//Delete the front of the list node
void deleteFront(List L){
	if(length(L) <= 0){
		printf("length is not greater than 0");
		exit(1);
	}else if(length(L) == 1){
		L->back = NULL;
		L->length--;
		L->cursor = NULL;
		L->cursor_index = -1;
		freeNode(&L->front);
	}else{
		Node temp=L->front;
		L->front = L->front->next;
		L->length--;
		L->cursor_index--;
		freeNode(&temp);
	}
}

//Delete the back node of the list
void deleteBack(List L){
	if(length(L) <= 0){
		printf("length is not greater than 0\n");
		exit(1);
	}else if(length(L) == 1){
		L->front = NULL;
		L->cursor = NULL;
		L->length--;
		L->cursor_index = -1;
		freeNode(&L->back);
		
	}else{
		
		if(L->cursor == L->back){
			L->cursor = NULL;
			L->cursor_index = -1;
		}
		L->back = L->back->prev;
		L->length--;
		freeNode(&L->back->next);
		
	}
}
void delete(List L){
	if(L->length <= 0 || L->cursor_index < 0){
		printf("Empty list");
	}
	if(L->cursor == L->front){
		deleteFront(L);
	}else if (L->cursor == L->back){
		deleteBack(L);
	}else{
		Node temp = L->cursor; //CHANGED
		L->cursor->prev->next = L->cursor->next;
		L->cursor->next->prev = L->cursor->prev;
		freeNode(&temp);
		L->cursor = NULL; //CHANGED
		L->cursor_index = -1;
		L->length--;
		
	}
}

// isEmpty()
// Returns true (1) if Q is empty, otherwise returns false (0)
int isEmpty(List L){
   if( L==NULL ){
      printf("List Error: calling isEmpty() on NULL List reference\n");
      exit(1);
   }
   return(L->length==0);
}


//Creates a copy of the list
 List copyList(List L){
	List listCopy = newList();
	Node N = L->front;
	while(N != NULL){
		append(listCopy, N->data);
		N = N->next;
	}
		return listCopy;
}

// printQueue()
// Prints data elements in Q on a single line to stdout.
void printList(FILE* out , List L){
   Node N = NULL;

   if( L==NULL ){
      printf("List Error: calling printList() on NULL List reference\n");
      exit(1);
   }

   for(N = L->front; N != NULL; N = N->next){
      printf("%d ", N->data);
   }
   //printf("\n");
}
