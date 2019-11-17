package com.company;

public class Main {

    public static void main(String[] args) {
        final int K = 5;
        final double ALPHA = 0.2 * K;

        double[][] matrixA = {{8.30, 2.62 + ALPHA, 4.10, 1.90},
                {3.92, 8.45, 7.78 - ALPHA, 2.46},
                {3.77, 7.21 + ALPHA, 8.04, 2.28},
                {2.21, 3.65 - ALPHA, 1.69, 6.99}};

        double[][] vectorF = {{-10.65 + ALPHA},
                {12.21},
                {15.45 - ALPHA},
                {-8.35}};

        double eps = 0.00001;

        //мтарица с диагональным преобладанием
        double[][] matrixADiag = {{12.68, -1.21, 1.42, 1.34},
                {4.67, 9.65, 0.48, 3.36},
                {-0.15, -0.24, 1.26, -0.18},
                {2.21, 2.65, 1.69, 6.99}};

        double[][] vectorFDiag = {{-31.51},
                {1.01},
                {2.24},
                {-8.35}};

        System.out.println("Matrix A:");
        Matrix.print(matrixA);

        System.out.println("Vector f:");
        Matrix.print(vectorF);



        System.out.println("\t\tMETHOD OF SIMPLE ITERATION: \n");

        System.out.println("Matrix B:");
        double[][] matrixB = SimpleIteration.matrixB(matrixA);
        Matrix.print(matrixB);

        System.out.println("Vector b:");
        double[][] vectorB = SimpleIteration.vectorB(matrixA, vectorF);
        Matrix.print(vectorB);

        System.out.println("Vector x:");
        double[][] vectorX = SimpleIteration.solution(matrixB, vectorB, matrixA, vectorF, eps);
        Matrix.print(vectorX);



        System.out.println("\t\tJACOBI METHOD: \n");

        System.out.println("Matrix B:");
        double[][] matrixB1 = Jacobi.matrixB(matrixADiag);
        Matrix.print(matrixB1);

        System.out.println("Vector b:");
        double[][] vectorB1 = Jacobi.vectorB(matrixADiag, vectorFDiag);
        Matrix.print(vectorB1);

        System.out.println("Matrix B norm = " + Matrix.matrixNorm(matrixB1));

        System.out.println("\nVector b norm = " + Matrix.vectorNorm(vectorB1));

        System.out.println("\nPriori estimate = " + Jacobi.prioriEstimate(matrixB1, vectorB1, eps));

        System.out.println("\nVector x:");
        double[][] vectorX1 = Jacobi.solution(matrixB1, vectorB1, eps);
        Matrix.print(vectorX1);

        System.out.println("Vector of residuals: ");
        Matrix.print(Matrix.difference(Matrix.multiply(matrixA, vectorX1), vectorF));



        System.out.println("\t\tGAUSS-SEIDEL METHOD: \n");

        System.out.println("\nVector x:");
        double[][] vectorX2 = Gauss_Seidel.solution(matrixB1, matrixADiag, vectorFDiag, vectorB1, eps);
        Matrix.print(vectorX2);
    }
}