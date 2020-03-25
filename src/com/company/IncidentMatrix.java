package com.company;


import static com.company.AdjacencyMatrix.outTypeVertex;
public class IncidentMatrix {

    public void createMatrix( int adjacency_matrix[][], int n){
        int ribs = countRibs(adjacency_matrix, n);
        int matrix_incen [][] = new int[n][ribs];

        int ind = 0;
        int index = 0;

        for (int i = 0; i< n; i++){
            for (int j = index; j < n; j++ ){
                if (adjacency_matrix[i][j] > 0){
                    if(i == j){
                        for ( int k = 0; k < adjacency_matrix[i][j]; k++){
                            matrix_incen[i][ind] = 2;
                            ind++;
                        }
                    }else{
                        for ( int k = 0; k < adjacency_matrix[i][j]; k++){
                            matrix_incen[i][ind] = 1;
                            matrix_incen[j][ind] = 1;
                            ind++;
                        }
                    }
                }
            }
            index++;
        }

        matrixOut(matrix_incen);

        degreeCalculationVertices(matrix_incen, n, ribs);
    }

    public void matrixOut(int matrix[][]){
        System.out.println("\n" + this.getClass());
        for (int i[]:matrix) {
            for (int j:i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
    /** Подсчитываю количество рёбер у графа ( с учётом петель) */
    public  int countRibs(   int[][] adjacency_matrix , int n){
        int count_ribs = 0;
        int index = 0;

        for (int i = 0; i<n; i++) {
            for (int j = index; j < n; j++) {
                count_ribs = count_ribs + adjacency_matrix[i][j];
            }
            index++;
        }
        System.out.println("Количество рёбер - " + count_ribs);
        return count_ribs;
    }

    /** Подсчёт Степеней вершин по Матрице Инциндентности */
    public void degreeCalculationVertices(int[][] matrix, int n, int ribs ){

        int degree[] = new int[n]; // степени вершин без учёта петель
        int loops[] = new int[n]; // только степени петель вершин

        for (int i = 0; i<n; i++){
            for (int j = 0; j<ribs; j++){
                switch (matrix[i][j]){
                    case 1:{
                        degree[i] ++;
                        break;
                    }
                    case 2:{
                        loops[i] += 2;
                        break;
                    }
                }
            }
        }
        outTypeVertex(degree, loops);
    }

}
