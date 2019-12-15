package com.company;

public class Krylov {

    //получение векторов с(i)
    public static double[][] getMatrixC(int dim, double[][] matrixA){
        double[][] vectorC0 = new double[dim][1];
        vectorC0[0][0] = 1;

        double[][] matrixC = new double[dim][dim + 1];
        matrixC[0][dim - 1] = 1;

        double[][]  tmp = new double[dim][1];
        Matrix.copy(tmp, vectorC0);

        for(int i = dim - 2; i >= 0; i--){
            tmp = Matrix.multiply(matrixA, tmp);

            for(int j = 0; j < dim; j++){
                matrixC[j][i] = tmp[j][0];
            }
        }

        tmp = Matrix.multiply(matrixA, tmp);
        for(int i = 0; i < dim; i++){
            matrixC[i][dim] = tmp[i][0];
        }

        return matrixC;
    }

    //печать коэффицентов c(i)
    public static void printCoeff(double[][] matrixC, int dim){
        for(int i = 0; i < dim; i++){
            for(int j = dim - 1; j >= 0; j--){
                System.out.printf("%12.2f", matrixC[i][j], "\n");
            }
            System.out.printf("%12.2f", matrixC[i][dim]);
            System.out.println();
        }
    }

    public  static double[][] getEigenvector(double lambda, double[][] matrixC, int dim, double[][] vectorQ){
        double[] betta = new double[dim];
        betta[0] = 1;

        for(int i = 1; i < dim; i++){
            betta[i] = lambda * betta[i - 1] - vectorQ[i - 1][0];
        }

        double[][] vectorX = new double[dim][1];

        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++) {
                vectorX[i][0] += betta[j] * matrixC[i][j];
            }
        }

        return vectorX;
    }
}
