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

        double[][] E = {{1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};

        double[][] matrixAClone = matrixA.clone();
        for (int i = 0; i < matrixA.length; i++) {
            matrixAClone[i] = matrixA[i].clone();
        }

        double[][] vectorFClone = vectorF.clone();
        for (int i = 0; i < vectorF.length; i++) {
            vectorFClone[i] = vectorF[i].clone();
        }

        System.out.println("Original system:");
        Gauss.print(matrixAClone, vectorFClone);

        Formatter frm = new Formatter();
        System.out.println("Determinant = " +
                frm.format("%3.4e", Gauss.completeSolution(matrixAClone, vectorFClone, E)));

        System.out.println("Solution(vector x):");
        Matrix.print(vectorFClone);

        System.out.println("Inverse matrix:");
        Matrix.print(E);

        System.out.println("Control(multiply the inverse and the original matrix):");
        Matrix.print(Matrix.multiply(matrixA, E));

        System.out.println("Vector of residuals:");
        Matrix.print(Matrix.difference(Matrix.multiply(matrixA, vectorFClone), vectorF));
    }
}

