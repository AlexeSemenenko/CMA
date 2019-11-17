package com.company;

public class SimpleIteration {

    //создание матрицы В
    public static double[][] matrixB(double[][] matrixA){
        double[][] matrixE = new double[matrixA.length][matrixA.length];
        for(int i = 0; i < matrixE.length; i++){
            matrixE[i][i] = 1;
        }

        double[][] matrixB;
        double[][] matrixAT = Matrix.transpose(matrixA);
        double[][] matrixATA = Matrix.multiply(matrixAT, matrixA);
        double norm = Matrix.matrixNorm(matrixATA);
        double[][] matrixFinish = new double[matrixA.length][matrixA.length]; //это матрица А(транспон)*А/норма(А(трансп)*А))

        Matrix.copy(matrixFinish, matrixATA);

        for(int i = 0; i < matrixFinish.length; i++){
            for(int j = 0; j < matrixFinish.length; j++){
                matrixFinish[i][j] /= norm;
            }
        }

        matrixB = Matrix.difference(matrixE, matrixFinish);

        return matrixB;
    }

    //создание веткора b
    public static double[][] vectorB(double[][] matrixA, double[][] vectorF){
        double[][] matrixAT = Matrix.transpose(matrixA);
        double[][] matrixATA = Matrix.multiply(matrixAT, matrixA);
        double[][] matrixATF = Matrix.multiply(matrixAT, vectorF);

        double norm = Matrix.matrixNorm(matrixATA);

        double[][] vectorB = new double[matrixA.length][1];

        for(int i = 0; i < vectorB.length; i++){
            vectorB[i][0] = matrixATF[i][0] / norm;
        }

        return vectorB;
    }

    //сам метод простой итерации
    public static double[][] solution(double[][] matrixB, double[][] vectorB, double[][] matrixA, double[][] vectorF, double eps){
        double[][] vectorX = new double[matrixB.length][1];

        int counter = 0;

        Matrix.copy(vectorX, vectorB);

        while (Matrix.vectorNorm(Matrix.difference(Matrix.multiply(matrixA, vectorX), vectorF)) > eps){
            vectorX = Matrix.addition(Matrix.multiply(matrixB, vectorX), vectorB);
            counter++;
        }

        System.out.println("(number of iterations = " + counter + ")");

        return vectorX;
    }
}
