package com.company;

public class Gauss_Seidel {

    //метод Гаусса-Зейделя
    public static double[][] solution(double[][] matrixB, double[][] matrixADiag, double[][] vectorFDiag, double[][] vectorB, double eps){
        double[][] currentX = new double[matrixB.length][1];
        double[][] tmp = new double[matrixB.length][1];

        int count = 0;

        Matrix.copy(tmp, vectorB);

        while(Matrix.vectorNorm(Matrix.difference(Matrix.multiply(matrixADiag, currentX), vectorFDiag)) > eps){

            for(int i = 0; i < tmp.length; i++){
                double sum1 = 0;
                double sum2 = 0;

                for(int j = 0; j < i; j++){
                    sum1 += matrixB[i][j] * currentX[j][0];
                }

                for(int j = i + 1; j < tmp.length; j++){
                    sum2 += matrixB[i][j] * tmp[j][0];
                }

                currentX[i][0] = sum1 + sum2 + vectorB[i][0];
            }

            count++;
            Matrix.copy(tmp, currentX);
        }

        System.out.println("(number of iterations = " + count + ")");

        return currentX;
    }
}
