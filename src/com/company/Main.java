package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static int matrix [][];
    public static int count_of_All_Vertex;
    public static ArrayList all_Bandwidths = new ArrayList();
    public static int stock;
    public static int source;

    public static void main(String[] args) throws IOException {
	    Reader.reader();
	    Reader.getSourceandStock();
	    makeMatrix();
	    modifyMatrix();
	    FordFalkerson.Run();
	    FordFalkerson.findMaxFlow();
	    Writer.writeResult();
	    System.out.println(count_of_All_Vertex);
	    System.out.println(all_Bandwidths);
	    System.out.println(source);
	    System.out.println(stock);
	    System.out.println(matrix);
	    for (int i=0; i<matrix.length;i++) {
	        System.out.println();
	        for (int j=0; j<matrix.length; j++) {
	            System.out.print(matrix[i][j]+" ");
            }
        }
        System.out.println("OLOLOLOLOLO");
        for (int i=0; i<FordFalkerson.flow.length;i++) {
            System.out.println();
            for (int j=0; j<FordFalkerson.flow.length; j++) {
                System.out.print(FordFalkerson.flow[i][j]+" ");
            }
        }
        System.out.println();
        System.out.println("MAXПОТОК"+FordFalkerson.findMaxFlow());
    }

    public static void makeMatrix() {
        matrix = new int[count_of_All_Vertex][count_of_All_Vertex];
        int count=0;
        for (int i=0; i<count_of_All_Vertex; i++) {
            for (int j=0; j<count_of_All_Vertex; j++) {
                matrix[i][j] = (int) all_Bandwidths.get(count);
                count++;
            }
        }
    }

    public static void modifyMatrix () {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0 + i; j < matrix[i].length; j++) {
                if (matrix[i][j] > 0) {
                    matrix[j][i] = -1;
                }
            }
        }
    }
}
