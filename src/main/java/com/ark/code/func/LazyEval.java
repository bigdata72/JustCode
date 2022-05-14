package com.ark.code.func;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LazyEval {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numbers.stream()
            .filter(LazyEval::isEven)
            .filter(LazyEval::isGreaterThan3)
            .mapToInt(LazyEval::doubleIt)
            .findFirst().getAsInt());
        
        System.out.println(processImperative(numbers));
    }
    
    public static boolean isGreaterThan3(int number){
        System.out.println("Greater than "+number);
        return number > 3;
    }
    
    public static boolean isEven(int number){
        System.out.println("Is Even "+number);
        return number % 2 == 0;
    }
    
    public static int doubleIt(int number){
        System.out.println("Double It "+number);
        return number *2;
    }
    

    
    public static int processImperative(List<Integer> numbers){
        List<Integer> evens = new ArrayList<>();
        List<Integer> gt3 = new ArrayList<>();
        int res = 0;
        for(int num : numbers){
            if(isEven(num))
                evens.add(num);                
        }
        System.out.println("Evens :"+evens);
        for(int num : evens){
            if(isGreaterThan3(num))
                gt3.add(num);
        }
        System.out.println("gt3 :"+gt3);
        for(int num : gt3){
            res = num * 2;
            break;
        }
        return res;
    }
}
