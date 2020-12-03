package com.company;


public class Encoder {

    private MatrixConstructor matrixConstructor = new MatrixConstructor();

    public int[] encodeGivenVector(int[] vector, int m) {
        Matrix gMatrix = matrixConstructor.getGeneratorMatrixWithGivenM(m);

        Matrix vMatrix = new Matrix(new int[][]{vector});

        Matrix encodedVectorMatrix = gMatrix.multiply(vMatrix.transformMatrixLengthToHeight()).transformMatrixLengthToHeight();

        return Converter.convertAsciiIntToBinaryInt(encodedVectorMatrix.data[0]);
    }

}
