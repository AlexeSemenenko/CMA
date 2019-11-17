package com.company;

public class Matrix{

    //вывод квадратной матрицы
    public static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%8.8f", matrix[i][j]);
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

    //разность матриц
    public static double[][] difference(double[][] first, double[][] second) {
        double[][] result = new double[first.length][second[0].length];

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[0].length; j++) {
                result[i][j] = first[i][j] - second[i][j];
            }
        }

        return result;
    }

    //сумма матриц
    public static double[][] addition(double[][] first, double[][] second) {
        double[][] result = new double[first.length][second[0].length];

        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[0].length; j++) {
                result[i][j] = first[i][j] + second[i][j];
            }
        }

        return result;
    }

    //транспонирование
    public static double[][] transpose(double[][] matrix){
        double[][] tMatrix = new double[matrix.length][matrix.length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                tMatrix[i][j] = matrix[j][i];
            }
        }

        return tMatrix;
    }

    //копирование матриц
    public static void copy(double[][] first, double[][] second){
        for(int i = 0; i < first.length; i++){
            for(int j = 0; j < first[1].length; j++){
                first[i][j] = second[i][j];
            }
        }
    }

    //норма матрицы(кубическая - макс сумма по эл в строке)
    public static double matrixNorm(double[][] matrix){
        double norm = 0;
        double max = 0;

        for(int i = 0; i < matrix.length; i++){
            norm = 0;

            for(int j = 0; j < matrix.length; j++){
                norm += Math.abs(matrix[i][j]);
                if(norm > max){
                    max = norm;
                }
            }
        }

        return max;
    }

    //норма вектора(кубическая - макс эл вектора)
    public static double vectorNorm(double[][] vector){
        double norm = 0;
        for(int i = 0; i < vector.length; i++){
            if(Math.abs(vector[i][0]) >= norm){
                norm = Math.abs(vector[i][0]);
            }
        }

        return norm;
    }
}
