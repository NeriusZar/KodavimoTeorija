package com.company;


public class Decoder {
    public int[] decodeGivenMatrix(int[] vector, int m) {
        int[] vectorWithoutZeros = convertZerosToMinusOne(vector);
        Matrix w = new Matrix(new int[][]{vectorWithoutZeros});
        Matrix h = new Matrix(new int[][]{{1, 1}, {1, -1}});

        for (int i = 1; i <= m; i++) {
            Matrix hMatrix = matrixConstructor.createHMatrix(i, m, h);
            w = w.multiply(hMatrix);
        }

        int[] finalW = w.data[0];

        int min = finalW[0];
        int max = finalW[0];

        for (int i = 1; i < finalW.length; i++) {
            if (finalW[i] > max) max = finalW[i];
            if (finalW[i] < min) min = finalW[i];
        }

        int moduleMax;
        int firstBit;
        if (Math.abs(max) > Math.abs(min)) {
            moduleMax = max;
            firstBit = 1;
        } else {
            moduleMax = min;
            firstBit = 0;
        }

        int position = 0;

        for (int i = 0; i < finalW.length; i++) {
            if (finalW[i] == moduleMax) {
                position = i;
            }
        }

        int[] binaryPosition = getBinaryFromPositionAndReverse(position, m);
        int[] decodedData = new int[m+1];
        decodedData[0] = firstBit;
        System.arraycopy(binaryPosition, 0, decodedData, 1, binaryPosition.length);
        return decodedData;
    }

    private int[] getBinaryFromPositionAndReverse(int position, int length) {
        StringBuilder binaryNumber = new StringBuilder(Integer.toBinaryString(position));

        while (binaryNumber.length() != length) {
            binaryNumber.insert(0, "0");
        }

        int[] array = new int[length];
        for (int i = 0; i < binaryNumber.length(); i++) {
            array[length - 1 - i] = Character.getNumericValue(binaryNumber.charAt(i));
        }
        return array;
    }

    private int[] convertZerosToMinusOne(int[] integers) {
        int[] changed = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            if(integers[i] == 0) {
                changed[i] = -1;
            }else {
                changed[i] = 1;
            }
        }
        return changed;
    }

    private MatrixConstructor matrixConstructor = new MatrixConstructor();
}

