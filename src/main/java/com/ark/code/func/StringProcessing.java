package com.ark.code.func;

import com.ark.code.func.model.Olympian;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringProcessing {
    public static void main(String[] args) {
        List<Olympian> athletes = Arrays.asList(
            Olympian.builder().name("Katie Ledecky").city("New York").build(),
            Olympian.builder().name("Caleb Dressel").city("Orlando").build(),
            Olympian.builder().name("Ryan Murphy").city("Dallas").build(),
            Olympian.builder().name("Flickinger").city("Chicago").build(),
            Olympian.builder().name("Jacoby").city("Minneapolis").build());
        
        String names = athletes.stream()
            .map(a -> a.getName())
            .collect(Collectors.joining(",", "[", "]"));
    
        System.out.println(names);
    }
}
