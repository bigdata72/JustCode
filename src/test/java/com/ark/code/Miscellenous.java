package com.ark.code;

import java.util.Random;

public class Miscellenous {
    public static void main(String[] args) {
        Random random = new Random();
        for (int attempt=1; attempt< 5; attempt++) {
            int cap = 10000;
            int base = 320;
            //int delay = random.nextInt(base * 2 * attempt * attempt);
            int delay = random.nextInt(Math.min(cap, base * 2 * attempt * attempt));
            System.out.println(base * 2 * attempt * attempt +" " +delay);
        }
    }
}
