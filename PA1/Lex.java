//Tejas Manjunath
//Lex.java
//PA1
//tmanjuna
//1536271
// Lex.java sorts a linked list using a insertion sort type algorithim to use as a reference to sort an array
import java.util.Scanner;
import java.io.*;

public class Lex {
    
    public static List insertionSort(List L, String[] T) {
        int n = T.length;
        int i;
        String temp;
        int j;
        
        if (n == 0){
            throw new RuntimeException ("EmptyArray");
        }
        L.append(0);
        for (i = 1; i < n; i++){
            L.moveBack();
            if (T[i].compareTo(T[L.get()]) > 0){        //Check if its greater than the greatest element
                L.append(i);
            } else {
                L.moveFront();
                if (T[i].compareTo(T[L.get()]) < 0){    //Check if its smaller than the element
                    L.prepend(i);
                } else {
                    while (T[i].compareTo(T[L.get()]) > 0){ //If its lower than an element then place it before the next highest element
                        L.moveNext();
                    }
                    L.insertBefore(i);
                }
            }
        }
        return L;
    }
    
    public static void main(String[] args) throws IOException {
        
        Scanner in = null;
        PrintWriter out = null;
        String line = "";
        String[] token = null;
        List list = null;
        String temp;
        int i, j, lineNumber = 0;
        
        if (args.length != 2) {
            System.err.println("Usage: Lex infile outfile");
            System.exit(1);
        }
        
        in = new Scanner(new File(args[0]));// Initialize in and out variables
        out = new PrintWriter(new FileWriter(args[1]));

        while (in.hasNextLine()) {	//Count lines and populate array
            lineNumber++;
            line += in.nextLine() + " ";
        }
        
        in = new Scanner(new File(args[0]));        //populate the array
        token = new String[lineNumber];

        for (i = 0; i < token.length; i++){
            token[i] = in.nextLine();
        }
        
        // Create new list
        list = new List();
        list = insertionSort(list, token);
        
            
        list.moveFront();
        while (list.index() >= 0) {
            out.println(token[list.get()]);
            list.moveNext();
        }
        
        in.close();
        out.close();
    }
}
