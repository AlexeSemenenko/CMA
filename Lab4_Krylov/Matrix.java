package com.company;

public class Matrix{

    //вывод квадратной матрицы
    public static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //умножение матриц
    public static double[][] multiply(double[][] first, double[][] second) {

        double[][] result = new double[first.length][second[0].length];

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < second[0].length; j++) {
                for (int k = 0; k < second.length; k++) {
                    result[i][j] += first[i][k] * second[k][j];
                }
            }
        }

        return result;
    }

    //копирование матриц
    public static void copy(double[][] first, double[][] second){
        for(int i = 0; i < first.length; i++){
            for(int j = 0; j < first[1].length; j++){
                first[i][j] = second[i][j];
            }
        }
    }
}
