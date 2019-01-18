package com.company;

import java.util.ArrayList;

import static com.company.Main.*;

public class FordFalkerson {
    public static int[][] flow;//Массив потоков, изначально он заполнен 0

    public static void Run (){
        flow = new int[count_of_All_Vertex][count_of_All_Vertex];
        boolean stoper = true;
        while (stoper){
            ArrayList used = new ArrayList();//использованные, строится финальный путь из этого
            ArrayList badUsed = new ArrayList();//тупиковые вершины, никуда из неё идти нельзя
            int presentVertex = source-1;//
            while (presentVertex != stock - 1){
                boolean checIn = true;
                for (int i = 0; i < matrix[presentVertex].length; i++){
                    if (VertexCheck(i,presentVertex,used,badUsed))
                    {
                        used.add(presentVertex);//если всё хорошо, добавляем
                        presentVertex = i;// текущая верщина становится предыдущей
                        checIn = false;
                        break;
                    }
                }
                if (checIn) {
                    if (used.isEmpty()){
                        stoper = false;
                        break;
                    }
                    else {
                        if (!badUsed.contains(presentVertex)) {
                            System.out.println(presentVertex+"<=");
                            badUsed.add(presentVertex);
                        }
                        presentVertex = (int) used.get(used.size() - 1);
                        used.remove(used.size() - 1);

                    }
                }
            }
            used.add(presentVertex);
            for (int i = 0; i < used.size(); i++){
                System.out.println(used.get(i));
            }
            ProcessMatrix(used);
        }
    }

    private static boolean VertexCheck(int i, int presentVertex, ArrayList used, ArrayList badUsed) {
        System.out.println("________");
        System.out.println(presentVertex + " " + i);
        System.out.println(matrix[presentVertex][i] +" "+!used.contains(presentVertex) +" "+flow[presentVertex][i] + "_"+ flow[i][presentVertex] + " " + !badUsed.contains(presentVertex));
        if ( ((matrix[presentVertex][i] > 0)//есть ли путь из 1й вершины в другую
                && (!used.contains(i))//были ли мы в этой вершине уже
                && (matrix[presentVertex][i] > flow[presentVertex][i])//поток дб меньше пропускной способности
                && (!badUsed.contains(i)))//не содержит
                || ( (matrix[presentVertex][i] == -1)//обратная дуга и условия ниже для неё
                && (!used.contains(i))
                && (flow[i][presentVertex] > 0)// поток больше нуля
                && (!badUsed.contains(i))) ){
            System.out.println("True");
            System.out.println("________");
            return true;
        }
        else {
            System.out.println("False");
            System.out.println("________");
            return false;
        }
    }

    public static void ProcessMatrix(ArrayList used){
        int min = findMinCapacity(used);
        System.out.println("=>>>>>"+min);
        for (int i = 0; i < used.size() - 1; i++){
            int from = (int) used.get(i);
            int in = (int) used.get(i+1);
            if (matrix[from][in] > 0){
                flow[from][in] = flow[from][in]+min;
            }
            if (matrix[from][in] < 0){
                flow[in][from] = flow[in][from]-min;
            }
        }
    }

    public static int findMinCapacity(ArrayList used) {
        int min = Integer.MAX_VALUE;
        int valreturn = 0;

        for (int i = 0; i < used.size() - 1; i++) {
            int from = (int) used.get(i);
            int in = (int) used.get(i + 1);
            if (matrix[from][in] < 0) {
                valreturn = flow[in][from];
                if (valreturn < min) {
                    min = valreturn;
                }
            }
            else {
                valreturn = matrix[from][in] - flow[from][in];
                if (valreturn < min) {
                    min = valreturn;
                }
            }

        }
        return min;
    }

    public static int findMaxFlow() {
        int maxFlow = 0;
        for (int i=0; i < count_of_All_Vertex;i++) {
            System.out.println(flow[source-1][i]);
            maxFlow += flow[source - 1][i];
        }
        return maxFlow;
    }
}
