package com.company;

import java.util.Random;

public class Channel {

    private int failuresCount = 0;
    private Random randomize = new Random();

    // Takes vector and error probability and imitates transmission through the channel
    // returns transmitted vector
    public int[] sendVectorThroughChannel(int[] vector, double chanceToFail) {
        int[] result = new int[vector.length];
        for(int i=0; i<vector.length;i++) {
            double randomNumber = randomize.nextDouble();
            if(randomNumber < chanceToFail) {
                if (vector[i] == 1) {
                    result[i] = 0;
                } else {
                    result[i] = 1;
                }
                failuresCount++;
            } else {
                result[i] = vector[i];
            }
        }
        return result;
    }

    //Takes vector before and after transmission and prints mistake count and positions
    public void findMistakes(int[] vector, int[] channeledVector) {
        if (failuresCount == 0) System.out.println("Klaidu nebuvo padaryta");
        else {
            System.out.println("Buvo padaryta klaidu - :  " + failuresCount);
            System.out.print("Pozicijos kur ivyko klaidos: ");
            if (failuresCount != 0)
                for(int i = 0; i < vector.length; i++) {
                    if (vector[i] != channeledVector[i]) {
                        System.out.print(i + 1 + " ");
                    }
                }
            System.out.println();
        }
    }
}
