package com.company;


public class Converter {
    public static int[] convertAsciiIntToBinaryInt(int[] charIntArray) {
        boolean[] correctedVector = convertIntToBoolean(charIntArray);
        return convertBooleanToInt(correctedVector);
    }

    public static boolean[] convertIntToBoolean(int[] intArray) {
        int length = intArray.length;
        boolean[] booleanArray = new boolean[length];
        for (int i = 0; i < length; i++) {
            booleanArray[i] = intArray[i] % 2 != 0;
        }
        return booleanArray;
    }

    public static int[] convertBooleanToInt(boolean[] booleanArray) {
        int[] intArray = new int[booleanArray.length];
        for (int i = 0; i < booleanArray.length; i++) {
            if(booleanArray[i]) {
                intArray[i]=1;
            }else {
                intArray[i]=0;
            }
        }
        return intArray;
    }

    public static int[] convertStringToInt(String binaryString) {
        int[] vector = new int[binaryString.length()];
        for(int i = 0; i < binaryString.length(); i++) {
            vector[i] = binaryString.charAt(i);
        }
        return convertAsciiIntToBinaryInt(vector);
    }
}
