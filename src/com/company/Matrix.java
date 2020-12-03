package com.company;


public class Matrix {
    public int height, length;
    public int[][] data;

    public Matrix(int[][] data) {
        this.height = data.length;
        this.length = data[0].length;
        fillData(data);
    }

    private void fillData(int[][] data) {
        this.data = new int[height][length];
        for(int i = 0; i < height; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, length);
        }
    }

    public void printMatrix() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public Matrix transformMatrixLengthToHeight() {
        Matrix transformedMatrix = new Matrix(new int[length][height]);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                transformedMatrix.data[j][i] = this.data[i][j];
            }
        }
        return transformedMatrix;
    }

    public Matrix multiply(Matrix matrix) {
        Matrix multipliedMatrix = new Matrix(new int[height][matrix.length]);
        for (int i = 0; i < multipliedMatrix.height; i++) {
            for (int j = 0; j < multipliedMatrix.length; j++) {
                for (int a = 0; a < length; a++) {
                    multipliedMatrix.data[i][j] += (data[i][a] * matrix.data[a][j]);
                }
            }
        }
        return multipliedMatrix;
    }
}
