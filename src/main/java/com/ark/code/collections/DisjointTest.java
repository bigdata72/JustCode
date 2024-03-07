package com.ark.code.collections;

import com.ark.code.stream.model.Condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisjointTest {
    public static void main(String[] args) {
        Set<String> c1 = set("802442");
        
        Condition condition = new Condition("802442", "802441");        

        System.out.println(Collections.disjoint(c1, condition.getValues()));
    }
    
    @SafeVarargs
    public static final <E> Set<E> set(E... es) {
        HashSet<E> s = new HashSet<E>();
        if (es != null) {
            Arrays.stream(es).filter(e -> e != null).forEach(e -> s.add(e));
        }
        return s;
    }
    
}
