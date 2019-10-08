import java.util.Formatter;

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

        double[][] augmented = {{8.30, 2.62 + ALPHA, 4.10, 1.90, -10.65 + ALPHA},
                {3.92, 8.45, 7.78 - ALPHA, 2.46, 12.21},
                {3.77, 7.21 + ALPHA, 8.04, 2.28, 15.45 - ALPHA},
                {2.21, 3.65 - ALPHA, 1.69, 6.99, -8.35}};

        int dim = matrixA.length;

        double[][] matrixForDet = matrixA.clone();
        for (int i = 0; i < matrixA.length; i++) {
            matrixForDet[i] = matrixA[i].clone();
        }

        double[][] matrixForUT = augmented.clone();
        for (int i = 0; i < matrixA.length; i++) {
            matrixForUT[i] = augmented[i].clone();
        }

        double[][] matrixForInverse = matrixA.clone();
        for (int i = 0; i < matrixA.length; i++) {
            matrixForInverse[i] = matrixA[i].clone();
        }

        System.out.println("Matrix A:");
        Matrix.print(matrixA);

        System.out.println("Vector f:");
        Matrix.print(vectorF);

        System.out.println("Augmented matrix:");
        Gauss.print(augmented, dim);

        Formatter frm = new Formatter();
        System.out.println("Determinant = " +
                frm.format("%3.4e", Gauss.determinant(matrixForDet, dim)));

        Gauss.getUpperTriangular(matrixForUT, dim);
        System.out.println("Upper triangular matrix:");
        Gauss.print(matrixForUT, dim);

        double[][] vectorX = Gauss.getResult(augmented, dim);
        System.out.println("Vector x:");
        Matrix.print(vectorX);

        System.out.println("Vector of residuals:");
        Matrix.print(Matrix.difference(Matrix.multiply(matrixA, vectorX), vectorF));

        double[][] inverse = Gauss.inverseMatrix(matrixForInverse, dim);
        System.out.println("Inverse matrix:");
        Matrix.print(inverse);

        System.out.println("Check inverse matrix:");
        Matrix.print(Matrix.multiply(inverse, matrixA));
    }
}