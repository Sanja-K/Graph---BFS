package com.company;

import java.util.Scanner;
/**Вариант № 3 */
public class Main {


    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix;
        Scanner sc = new Scanner(System.in);
        int n = 0;// количество вершин графа


        try{
            System.out.println("Введите количество вершин графа");
            n = sc.nextInt();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (n < 2){
                System.out.println("Количество вершин графа должно быть больше 2");
                return;
            }
        }



        adjacencyMatrix = new AdjacencyMatrix(n);
        adjacencyMatrix.createMatrix();
    }
}
