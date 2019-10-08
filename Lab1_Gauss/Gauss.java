import java.util.Formatter;

public class Gauss {

    //вывод расширенной матрицы
    public static void print(double[][] matrix, int dim) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim + 1; j++) {
                Formatter frm = new Formatter();
                System.out.print("  " + frm.format("%3.2e", matrix[i][j]) + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //вычисление определителя
    public static double determinant(double[][] matrix, int dim){
        double det = 1;

        for(int i = 0; i < dim; i++){
            for(int j = i + 1; j < dim; j++){
                double e = matrix[j][i] / matrix[i][i];
                for(int k = i; k < dim; k++){
                    matrix[j][k] -= e * matrix[i][k];
                }
            }
        }

        for(int i = 0; i < dim; i++){
            det *= matrix[i][i];
        }

        return det;
    }

    //нахождение верхне-треугольной
    public static void getUpperTriangular(double[][] matrix, int dim){
        double temp;

        for(int i = 0; i < dim; i++){
            temp = matrix[i][i];

            for(int j = dim; j >= i; j--){
                matrix[i][j] /= temp;
            }
            for(int j = i + 1; j < dim; j++){
                temp = matrix[j][i];

                for(int k = dim; k >= i; k--){
                    matrix[j][k] -= temp * matrix[i][k];
                }
            }
        }
    }

    //нахождение обратной
    public static double[][] inverseMatrix(double[][] matrix, int dim){
        double temp;

        double[][] E = {{1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};

        for(int k = 0; k < dim; k++){
            temp = matrix[k][k];

            for(int j = 0; j < dim; j++){
                matrix[k][j] /= temp;
                E[k][j] /= temp;
            }

            for(int i = k + 1; i < dim; i++){
                temp = matrix[i][k];

                for(int j = 0; j < dim; j++){
                    matrix[i][j] -= temp * matrix[k][j];
                    E[i][j] -= temp * E[k][j];
                }
            }
        }

        for(int k = dim - 1; k > 0; k--){
            for (int i = k - 1; i >= 0; i--){
                temp = matrix[i][k];

                for(int j = 0; j < dim; j++){
                    matrix[i][j] -= temp * matrix[k][j];
                    E[i][j] -= temp * E[k][j];
                }
            }
        }
        return E;
    }

    //получение вектора Х
    public static double[][] getResult(double[][] matrix, int dim){
        double[][] result = new double[dim][1];

        getUpperTriangular(matrix, dim);

        result[dim - 1][0] = matrix[dim - 1][dim];

        for(int i = dim - 2; i >= 0; i--){
            result[i][0] = matrix[i][dim];

            for(int j = i + 1; j < dim; j++){
                result[i][0] -= matrix[i][j] * result[j][0];
            }
        }
        return result;
    }
}