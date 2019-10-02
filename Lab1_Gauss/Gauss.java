import java.util.Formatter;

public class Gauss {

    public static void print(double[][] matrix, double[][] vector) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Formatter frm = new Formatter();
                System.out.print("  " + frm.format("%3.2e", matrix[i][j]) + "  ");
            }
            Formatter frm = new Formatter();
            System.out.println("    |    " + frm.format("%3.2e", vector[i][0]));
        }
        System.out.println();
    }

    public static double completeSolution(double[][] matrixA, double[][] vectorF, double[][] E) {
        double det = 1;

        for (int i = 0; i < matrixA[0].length; i++) {
            det *= Gauss.divisionByLeadEl(matrixA, vectorF, i, E);
            Gauss.differenceLinesF(matrixA, vectorF, i, E);
        }

        for (int i = matrixA[0].length - 1; i >= 0; i--) {
            Gauss.differenceLinesS(matrixA, vectorF, i, E);
        }
        return det;
    }

    private static double divisionByLeadEl(double[][] matrixA, double[][] vectorF, int nLine, double[][] E) {
        double leadEl = matrixA[nLine][nLine];

        vectorF[nLine][0] /= leadEl;

        for (int j = nLine + 1; j < matrixA[0].length; j++) {
            matrixA[nLine][j] /= leadEl;
        }

        for (int j = 0; j < E[0].length; j++) {
            E[nLine][j] /= leadEl;
        }

        return leadEl;
    }

    private static void differenceLinesF(double[][] matrixA, double[][] vectorF, int nLine, double[][] E) {
        for (int i = nLine + 1; i < matrixA.length; i++) {
            vectorF[i][0] -= vectorF[nLine][0] * matrixA[i][nLine];

            for (int j = nLine + 1; j < matrixA[0].length; j++) {
                matrixA[i][j] -= matrixA[nLine][j] * matrixA[i][nLine];
            }

            for (int j = 0; j < matrixA.length; j++) {
                E[i][j] -= E[nLine][j] * matrixA[i][nLine];
            }
        }
    }

    private static void differenceLinesS(double[][] matrixA, double[][] vectorF, int nLine, double[][] E) {
        for (int i = 0; i < nLine; i++) {
            vectorF[i][0] -= vectorF[nLine][0] * matrixA[i][nLine];

            for (int j = 0; j < matrixA[0].length; j++) {
                E[i][j] -= E[nLine][j] * matrixA[i][nLine];
            }

            matrixA[i][nLine] = 0;
        }
    }
}
