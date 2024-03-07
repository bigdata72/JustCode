package com.ark.code.lang;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ImmutableTest {
    
    private String name1;
    private String name2;
    
    private String name;
    
    ImmutableTest(){
        this.name1 = "Arijit";
        this.name2 = "Kundu";
    }
    
    private String getName(){
        if(StringUtils.isBlank(name1) && StringUtils.isNotBlank(name2)){
            String temp = name2;
            name1 = temp;
            name2 = "";
        }
        if(StringUtils.isBlank(name)){
            this.name = String.join("", Arrays.asList(name1, name2).stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return this.name;
    }
    
    public void printName(){
        System.out.println("Name is ="+getName());
        System.out.println("Name1 is ="+getName1());
        System.out.println("Name2 is ="+getName2());
    }
    
    public static void main(String[] args) {
        ImmutableTest test = new ImmutableTest();
        test.setName1(" ");
        test.setName("");
        test.printName();
    }
}
