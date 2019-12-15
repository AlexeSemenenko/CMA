package com.company;

public class SystemSolution {

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
