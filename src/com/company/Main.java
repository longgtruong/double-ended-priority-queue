package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private ArrayList<Integer> output;
    private ArrayList<Integer> input;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        DEPQ depq = new DEPQ();
        for (int i=0;i<20;i++) {
            depq.add(i);
        }
        depq.print();
        System.out.println("REMOVING MAX: " + depq.removeHigh());
        System.out.println("NUMBER OF ELEMENTS: " + depq.size());
        depq.print();
    }


    private ArrayList<Integer> generateRandomArray(int length) {
        ArrayList<Integer> array = new ArrayList<>();
        Random rand = new Random();
        for (int i=0;i<length;i++) {
            array.add(rand.nextInt(length+50));
        }
        return array;
    }
}
