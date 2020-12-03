package com.company;

import static java.lang.Math.pow;

public class MatrixConstructor {

    public Matrix getGeneratorMatrixWithGivenM(int m) {
        int length = m + 1;
        int height =  (int) pow(2, m);
        int value;
        Matrix result = new Matrix(new int[height][length]);
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < height; j++) {
                if (i == 0) {
                    value = 1;
                }
                else {
                    value = (j / ((int) pow(2, i - 1))) % 2;
                }
                result.data[j][i] = value;
            }
        }
        return result;
    }

    public Matrix createHMatrix(int i, int m, Matrix h) {
        int firstIArgument = (int) pow(2, m - i);
        int secondIArgument = (int) pow(2, i - 1);

        Matrix middleResult = getIMultipliedByH(h, firstIArgument);
        return getHMultipliedByI(middleResult, secondIArgument);
    }

    public Matrix getHMultipliedByI(Matrix matrix, int I) {
        int height = I * matrix.height;
        int length = I * matrix.length;
        int[][] result = new int[height][length];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
                if(i%I == j%I) {
                    result[i][j] = matrix.data[i/I][j/I];
                }else {
                    result[i][j] = 0;
                }
            }
        }
        return new Matrix(result);
    }

    public Matrix getIMultipliedByH(Matrix h, int I) {
        int height = I * 2;
        int length = I * 2;
        int[][] result = new int[height][length];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < length; j++) {
                if(i/2 == j/2) {
                    result[i][j] = h.data[i%2][j%2];
                }
                else result[i][j] = 0;
            }
        }
        return new Matrix(result);
    }
}
