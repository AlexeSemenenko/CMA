public class SquareRoot {

    //вычисление определителя через матрицу S
    public static double determinant(double[][] matrixS){
        double det = 1;

        for(int i = 0; i < matrixS.length; i++){
            det *= matrixS[i][i];
        }

        return det;
    }

    //получение верхне-треугольной мтарицы S
    public static double[][] getMatrixS(double[][] matrix){
        double[][] matrixS = new double[matrix.length][matrix.length];
        double temp;

        for(int i = 0; i < matrixS.length; i++){
            temp = 0;

            //вычисление элементов на главной диагонали
            for(int k = 0; k < i; k++){
                temp += matrixS[k][i] * matrixS[k][i];
            }
            matrixS[i][i] = Math.sqrt(matrix[i][i] - temp);

            //вычисление оставшихся элемнтов
            for(int j = i + 1; j < matrixS.length; j++){
                temp = 0;

                for(int k = 0; k < i; k++){
                    temp += matrixS[k][i] * matrixS[k][j];
                }
                matrixS[i][j] = (matrix[i][j] - temp) / matrixS[i][i];
            }
        }

        return matrixS;
    }

    //вычисление вектора y по формулам
    public static double[][] getVectorY(double[][] matrixS, double[][] vectorF){
        double[][] vectorY = new double[vectorF.length][1];
        double temp;

        vectorY[0][0] = vectorF[0][0] / matrixS[0][0];

        for(int i = 0; i < matrixS.length; i++){
            temp = 0;

            for(int j = 0; j < i; j++){
                temp += matrixS[j][i] * vectorY[j][0];
            }

            vectorY[i][0] = (vectorF[i][0] - temp) / matrixS[i][i];
        }

        return vectorY;
    }

    //вычисление вектора x по формулам
    public static double[][] getVectorX(double[][] matrixS, double[][] vectorY){
        double[][] vectorX = new double[vectorY.length][1];
        double temp;
        int dim = matrixS.length;

        vectorX[dim - 1][0] = vectorY[dim - 1][0] / matrixS[dim - 1][dim - 1];

        for(int i = dim - 2; i >= 0; i--){
            temp = 0;

            for(int j = i + 1; j < dim; j++){
                temp += matrixS[i][j] * vectorX[j][0];
            }

            vectorX[i][0] = (vectorY[i][0] - temp) / matrixS[i][i];
        }

        return vectorX;
    }
}
