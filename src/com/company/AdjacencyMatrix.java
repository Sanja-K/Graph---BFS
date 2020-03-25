package com.company;

import java.util.ArrayDeque;
import java.util.HashSet;

public class AdjacencyMatrix {
    public static int[][] matrix; // матрица смежности
    static int n;
    private static final Integer INF = Integer.MAX_VALUE/2;
    private IncidentMatrix incidentMatrix = new IncidentMatrix();
    AdjacencyMatrix(int countVertex){
        this.n = countVertex;
    }

    public void createMatrix( ){
        int index = 0;
        double number = 0;
        matrix = new int[n][n];

        for(int i = 0; i<n; i++){
            for (int j  = index; j <n ; j++){
                number = Math.random(); // Рандомим случайное значение в диапазоне 0.0 - 1.0
                int resultNum = (int) Math.round (number); // округляем результат к ближайшему целому числу(0 или 1)

                if(i!=j){
                    matrix[i][j] = resultNum;
                    matrix[j][i] = resultNum;
                }else{
                    matrix[i][j] = resultNum; // у вершины может быть петля
                }
            }
            index++;
        }

        matrixOut(matrix);
        searchEccentr();
        degCalcVert(matrix);

        //Создаю матрицу инцендентности
        incidentMatrix.createMatrix(matrix, n);
    }

    //Поиск в ширину
    private int[] wideSearch(int start_vertex){

        int distance[] = new int[n];
        int el = 0;

        ArrayDeque<Integer> q_ver = new ArrayDeque<>();
        boolean used[] = new boolean[n];// хранит пройденные вершины
        q_ver.addLast(start_vertex);

        while (!q_ver.isEmpty()){

            el = q_ver.removeFirst();
            used[el] = true;

            for (int i = 0; i < n; i++) {
                /** находим смежные вершины, исключаем петли текущей,
                 *  проверяем была ли пройдена вершина*/
                if (matrix[el][i] == 1 && el!=i && !used[i]) {
                    q_ver.addLast(i);
                    used[i] = true;
                   // distance[i]++;
                    distance[i] = distance[el] +1;
                }
            }
        }

        //выводим расстояние от вершины el до всех остальных вершин
        for (int i = 0; i < n; i++){
            System.out.print(distance[i] + "\t");
        }
        System.out.println();
            return distance;
    }

    //Вычисление эксцентриситета
    private void searchEccentr( ){
        int[] eccentricity = new int[n];
        int distance[] ;

        System.out.println( "\n" + "Матрица расстояний ");
        for (int i = 0; i < n; i++) {
            distance = wideSearch(i);
            for (int j = 0; j < distance.length; j++) {
                if (distance[j] > eccentricity[i]) {
                    eccentricity[i] = distance[j];
                }
            }
        }
        vertexType(eccentricity);
    }

    //Нахождение радиуса диаметра
    public void vertexType(int eccentricity[] ){
        int rad  = INF;
        int diam = 0;

        for (int i = 0; i < n; i++) {
            if(rad > eccentricity[i]){
                rad = eccentricity[i];
            }

            if (diam < eccentricity[i]){
                diam = eccentricity[i];
            }
        }

        typeVert(eccentricity, rad, diam);

        System.out.println("Радиус - "+ rad);
        System.out.println("Диаметр - " + diam);
    }

    //Нахождение Периферийных и центральных вершин
    public void typeVert(int eccentricity[], int radm, int diam ){
        System.out.println("\n");
        if(radm != diam){
            for (int i = 0; i < n; i++) {
                if(eccentricity[i] == radm) {
                    System.out.println("Центральная вершина " + i);
                }

                if(eccentricity[i] == diam){
                    System.out.println("Переферийная вершина " + i);
                }
            }
        }else{
            System.out.println( "Все вершины графа переферийные ");
        }
    }

    // Подсчёт степени вершин
    public void degCalcVert(int matrix[][] ){
        int[] degree = new int[n]; // степень вершин
        int[] loops = new int[n]; // петли

        int index = 0;
        for (int i = 0; i<n; i++ ){
            for (int j = index; j <n; j++){
                if(matrix[i][j] > 0){
                    if (i != j){
                        degree[i] = degree[i] + matrix[i][j] ;
                        degree[j] = degree[j] + matrix[j][i] ;
                    }else{
                        loops[i] = matrix[i][j] * 2;// Петли считаются за 2 вхождения
                    }
                }
            }
            index++;
        }

        outTypeVertex(degree, loops);
    }


    public void matrixOut(int matrix[][]){

        System.out.println("\n" + "Матрица Смежности ");
        for (int i[]:matrix) {
            for (int j:i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }

    public static void outTypeVertex(int degree[], int loops[]) {
        System.out.println("\n");
        for (int i = 0; i < degree.length; i++) {
            switch (degree[i]){
                case 0:{
                    System.out.println("Вершина " + i + " степень - "
                            + (degree[i] + loops[i]) + " изолированная");
                    break;
                }
                case 1:{
                    System.out.println("Вершина " + i + " степень - "
                            + (degree[i] + loops[i]) + " концевая");
                    break;
                }
                default:{
                    if (degree[i] == n-1){
                        System.out.println("Вершина " + i + " степень - "
                                + (degree[i] + loops[i]) + " доминирующая");
                    }else{
                        System.out.println("Вершина " + i + " степень - "
                                + (degree[i] + loops[i]));
                    }
                }
            }
        }
    }
}

