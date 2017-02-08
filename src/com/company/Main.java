package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static Scanner scnr = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("0.000");
    static int numPlayers, numAtBats;

    public static int validateInt(String prompt) {

        System.out.println(prompt);
        while (!scnr.hasNextInt()) {
            System.out.println("Input error");
            System.out.println(prompt);
            scnr.nextLine();
        }
        return scnr.nextInt();
    }

    public static int validateInt(String prompt, int min, int max) {
        int num = validateInt(prompt);

        while (checkRange(num, min, max) == false) {
            System.out.println("Input error");
            scnr.nextLine();
            num = validateInt(prompt);
        }
        return num;
    }

    public static boolean checkRange(int num, int min, int max) {

        if (num < min || num > max) {
            return false;
        }
        return true;
    }

    public static double battingAverage(int[] stats) {
        double hits = 0;
        for (int atBat : stats) {
            if (atBat != 0) {
                hits++;
            }
        }
        double batAvg = hits / stats.length;
        return batAvg;
    }

    public static double sluggingPercentage(int[] stats) {
        double bases = 0;
        for (int i = 0; i < stats.length; i++) {
            bases += stats[i];
        }
        double slugPer = bases / stats.length;
        return slugPer;
    }

    public static int[][] populatStats(int numPlayers) {
        int[][] stats = new int[numPlayers][];
        for (int i = 0; i < stats.length; i++) {
            String prompt1 = String.format("Enter number of at bats for Player %d (0-100): ", i + 1);
            int numBats = validateInt(prompt1, 0, 100);
            stats[i] = new int[numBats];
            for (int j = 0; j < stats[i].length; j++) {
                String prompt2 = String.format("Enter number of bases earned for at bat #%d (0-4): \n0=Out 1=Single 2=Double 3=Triple 4=Home Run", j + 1);
                stats[i][j] = validateInt(prompt2, 0, 4);
            }
        }
        return stats;
    }

    public static void main(String[] args) {

        numPlayers = validateInt("Enter number of players (0-100): ", 0, 100);

        int[][] stats = populatStats(numPlayers);

        for (int i = 0; i < stats.length; i++) {
            System.out.printf("Player #%d Batting Average: ", i + 1);
            System.out.print(df.format(battingAverage(stats[i])));
            System.out.printf("\tPlayer #%d Slugging Percentage: ", i + 1);
            System.out.print(df.format(sluggingPercentage(stats[i])));
            System.out.println();
        }
    }
}






