#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"


#define MAX_LEN 160

//List insertionSort(List L, char * list[]){

//}
int main(int argc, char * argv[]){

   int i = 0;
   int count=0;
   FILE *in, *out;
   char line[MAX_LEN];
   //char tokenlist[MAX_LEN];
   //char* token;
   List L = NULL;

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }

   // open files for reading and writing 
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");
   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }
   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }

   /* read each line of input file, then count and print tokens */
   while( fgets(line, MAX_LEN, in) != NULL)  {
      count++;
   }
   fclose(in);
   in = fopen(argv[1], "r");

   char *lineArray[count];

   while(fgets(line,MAX_LEN,in) != NULL){
   	lineArray[i] = malloc(strlen(line)+1);
   	strcpy(lineArray[i],line);
   	i++;
   }

   //Insertion Sort
   L = newList();

   if(count == 0){
   	printf("Empty Array\n");
   	exit(1);
   }

//INDIRECT SORT
   append(L, 0);
   for(int k = 1; k < count; k++){		//go through the word list
   	char * temp = lineArray[k];
   	moveBack(L);
   	if(strcmp(temp,lineArray[get(L)]) > 0){
   		append(L,k);
   	}else{
   		moveFront(L);
   		if(strcmp(temp,lineArray[get(L)]) < 0){
   			prepend(L,k);
   		}else{
   			while(strcmp(temp,lineArray[get(L)]) > 0){
   				moveNext(L);
   			}
   			insertBefore(L,k);
   		}
   	}
   }

   moveFront(L);
   
   //Prints to an outfile
   while(index(L) >= 0){
   	fprintf(out, lineArray[get(L)]);
   	moveNext(L);
   }

   	freeList(&L);
   //free the array holding the words
   for(int k = 0; k < count; k++){
	free(lineArray[k]);
   }
   fclose(in);
   fclose(out);

   return(0);
}
