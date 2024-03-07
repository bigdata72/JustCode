package com.ark.code.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListOperation {
    public static void main(String[] args) {
        List<String> legs = new ArrayList<>();
        legs.add("Main1");
        legs.add("Main2");
        legs.add("Main3");
        legs.add("Main4");
        System.out.println("Elements of List legs :"+legs);
        Collection<String> c = Arrays.asList(legs.get(0), legs.get(legs.size() -1));
        legs.retainAll(c);
        System.out.println("Elements of List legs after removing first and last entries :"+legs);
    }
}
