package com.company;

public class Main {

    public static void main(String[] args) {
        final int K = 5;
        final double ALPHA = 0.2 * K;

        double[][] matrixA = {{8.30, 2.62 + ALPHA, 4.10, 1.90},
                {3.92, 8.45, 7.78 - ALPHA, 2.46},
                {3.77, 7.21 + ALPHA, 8.04, 2.28},
                {2.21, 3.65 - ALPHA, 1.69, 6.99}};

        int dim = matrixA.length;

        double[][] matrixC = Krylov.getMatrixC(dim, matrixA);
        System.out.println("Vectors C(i):");
        Krylov.printCoeff(matrixC, dim);

        double[][] matrixCCopy = new double[dim][dim + 1];
        Matrix.copy(matrixCCopy, matrixC);

        double[][] vectorQ = SystemSolution.getResult(matrixCCopy, dim);
        System.out.println("\nСoefficients of the polynomial:");
        Matrix.print(vectorQ);

        double[] lambda = {0.79, 5.30, 5.95, 19.73};
        System.out.println("Eigenvalue: ");
        for(int i = 0; i < dim; i++){
            System.out.print(lambda[i] + " ");
        }
        System.out.println("\n");

        for(int i = 0; i < dim; i++){
            System.out.printf("The value of %3.2f corresponds to a vector:\n", lambda[i]);
            Matrix.print(Krylov.getEigenvector(lambda[i], matrixC, dim, vectorQ));
        }
    }
}
