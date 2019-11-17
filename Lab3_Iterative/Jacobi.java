package com.company;

public class Jacobi {

    //матрица B
    public static double[][] matrixB(double[][] matrixADiag){
        double[][] matrixB = new double[matrixADiag.length][matrixADiag.length];

        for(int i = 0; i < matrixB.length; i++){
            for(int j = 0; j < matrixB.length; j++){
                if(i == j){
                    continue;
                } else{
                    matrixB[i][j] = -matrixADiag[i][j] / matrixADiag[i][i];
                }
            }
        }

        return matrixB;
    }

    //вектор b
    public static double[][] vectorB(double[][] matrixADiag, double[][] vectorFDiag){
        double[][] vectorB = new double[matrixADiag.length][1];

        for(int i = 0;  i < vectorB.length; i++){
            vectorB[i][0] = vectorFDiag[i][0] / matrixADiag[i][i];
        }

        return vectorB;
    }

    //априорная оценка
    public static int prioriEstimate(double[][] matrixB, double[][] vectorB, double eps){
        return (int)(Math.log(eps*(1 - Matrix.matrixNorm(matrixB))/Matrix.vectorNorm(vectorB))/Math.log(Matrix.matrixNorm(matrixB))) + 1;
    }

    //метод Якоби
    public static double[][] solution(double[][] matrixB, double[][] vectorB, double eps){
        int counter = 0;
        double[][] currentX = new double[vectorB.length][1];

        Matrix.copy(currentX, vectorB);

        double[][] vectorX = Matrix.addition(Matrix.multiply(matrixB, currentX), vectorB);

        while (Matrix.vectorNorm(Matrix.difference(vectorX, currentX)) > eps){
            Matrix.copy(currentX, vectorX);
            vectorX = Matrix.addition(Matrix.multiply(matrixB, currentX), vectorB);
            counter++;
        }

        System.out.println("(number of iterations = " + counter + ")");

        return vectorX;
    }
}
