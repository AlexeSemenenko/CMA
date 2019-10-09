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

        System.out.println("Matrix A:");
        Matrix.print(matrixA);

        System.out.println("Vector f:");
        Matrix.print(vectorF);

        double[][] matrixAt = Matrix.transpose(matrixA);

        double[][] newA = Matrix.multiply(matrixAt, matrixA);
        System.out.println("Symmetric matrix:");
        Matrix.print(newA);

        double[][] newF = Matrix.multiply(matrixAt, vectorF);
        System.out.println("New vector F:");
        Matrix.print(newF);

        double[][] matrixS = SquareRoot.getMatrixS(newA);
        System.out.println("Matrix S:");
        Matrix.print(matrixS);

        Formatter frm = new Formatter();
        double det = SquareRoot.determinant(matrixS);
        System.out.println("Determinant = " + frm.format("%3.4e", det) + "\n");

        double[][] vectorY = SquareRoot.getVectorY(matrixS, newF);
        System.out.println("Vector Y:");
        Matrix.print(vectorY);

        double[][] vectorX = SquareRoot.getVectorX(matrixS, vectorY);
        System.out.println("Vector X:");
        Matrix.print(vectorX);

        System.out.println("Vector of residuals:");
        Matrix.print(Matrix.difference(Matrix.multiply(matrixA, vectorX), vectorF));
    }
}
