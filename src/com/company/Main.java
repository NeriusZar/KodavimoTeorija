package com.company;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.UK);

        boolean repeat = true;
        int  m=0, answer;
        double p=0;
        String word=null;


        System.out.println("***************************");
        System.out.println("** Si programa uzkoduoja duomenis Rydo-Miulerio kodu RM(1,m) **");
        System.out.println("** Turimas konkretus kunas Fq, kur q=2 **");
        System.out.println("** Programos argumentai yra m ir vektorius **");
        System.out.println("***************************");

        do {
            System.out.println("Iveskite kodo parametra m: ");
            try {
                m = scanner.nextInt();
                while (m < 1) {
                    System.out.println("Parametras m turi buti m>=1 !");
                    System.out.println("Iveskite kodo parametra m: ");
                    m = scanner.nextInt();
                }
                System.out.println("Iveskite klaidos tikimybe p: ");
                p = scanner.nextDouble();
                while (p > 1 || p < 0) {
                    System.out.println("Parametras p turi buti 0<p<1 !");
                    System.out.println("Iveskite klaidos tikimybe p: ");
                    p = scanner.nextDouble();
                }
                System.out.println("Iveskite informacijos vektoriu (simboliai be tarpu, nieko neatskirti), kurio ilgis m+1: ");
                word = scanner.next();
                while (!Pattern.matches("^[01]{" + (m + 1) + "}$", word)) {
                    System.out.println("Netinkamas vektorius!");
                    System.out.println("Iveskite informacijos vektoriu (simboliai be tarpu, nieko neatskirti), kurio ilgis m+1: ");
                    word = scanner.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Netinkamo tipo parametras!");
                System.exit(0);
            }
            System.out.println("***************************");

            int[] vector = Converter.convertStringToInt(word);
            System.out.println(String.format("Pateiktas vektorius:  %s", Arrays.toString(vector)));
            int[] codedVector = (new Encoder()).encodeGivenVector(vector, m);
            System.out.println(String.format("Uzkoduotas vektorius: %s", Arrays.toString(codedVector)));
            int[] channeledVector;
            Channel channel = new Channel();
            channeledVector = channel.sendVectorThroughChannel(codedVector, p);
            System.out.println(String.format("Persiustas vektorius: %s", Arrays.toString(channeledVector)));
            channel.findMistakes(codedVector, channeledVector);
            System.out.println("Ar norite pakeisti is kanalo isejusi vektoriu? 1 jei taip, 0 jei ne");
            answer = scanner.nextInt();
            if (answer == 1) {
                System.out.println("Iveskite pakeista vektoriu (simboliai be tarpu, nieko neatskirti): ");
                word = scanner.next();
                int length = (int) Math.pow(2, m);
                while (!Pattern.matches("^[01]{" + (length) + "}$", word)) {
                    System.out.println("Netinkamas vektorius! Ilgis turi buti 2^m");
                    word = scanner.next();
                }
                channeledVector = Converter.convertStringToInt(word);
                System.out.println(String.format("Pakeistas vektorius: %s", Arrays.toString(channeledVector)));
            }
            int[] decodedVector = (new Decoder()).decodeGivenMatrix(channeledVector, m);
            System.out.println(String.format("Dekoduotas vektorius: %s", Arrays.toString(decodedVector)));
            System.out.println("Ar norite paleisti programa is naujo? 1 jei taip, 0 jei ne");
            answer = scanner.nextInt();
        } while (answer != 1);
        scanner.close();
    }
}

