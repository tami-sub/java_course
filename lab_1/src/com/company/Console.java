package com.company;

import java.util.Scanner;

public class Console {
    private int amount = 0;
    public double[] start(int amount){
        System.out.println("Enter amount of points");
//        Scanner n = new Scanner(System.in);
//        int amount = n.nextInt();
        this.amount = amount;
        double [] array = new double[amount *2];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < amount *2; i++){
            double f = scan.nextDouble();
            array[i] = f;
        }
        return array;
    }
}
