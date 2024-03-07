package com.ark.code.concurrency;

public class UnsafeSynchronization {
    private static int number;
    private static boolean ready = false;
    
    static class Reader extends Thread{

        public void run(){
            while(!ready){
                Thread.yield();
            }
            System.out.println("number ="+number);
        }
    }
    
    public static void main(String a[]){
        ready = true;
        number = 10;
        System.out.println("starting reader thread ..");
        new Reader().start();
    }
}
