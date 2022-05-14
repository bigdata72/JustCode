package com.ark.code.stream;

import com.ark.code.stream.model.BookingRequestContract;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class StreamDebugging {
    public static void main(String[] args) {
        List<String> comments = Arrays.asList("PLS BOOK15/02 SPLIT FROM CNB0167378",
            "WE DON'T PREFER ODD DEPOT",
            "TRANSIT TIME AS PER NESTLE CONTRACT 11");
    
        BookingRequestContract contract = new BookingRequestContract();
        contract.setGeneralComments(comments);
        printComments(comments);
        
        new StreamDebugging().debugStream(contract);
        
        printComments(contract.getGeneralComments());
        
    }
    
    static void printComments(List<String> strList){
        System.out.println(strList);
    }
    
    void debugStream(BookingRequestContract contract){
        List<String> comments = new ArrayList<>();
        contract.getGeneralComments().stream()
//            .filter(Objects::nonNull)
            .filter(s-> {
                System.out.println("Element="+s);
                return StringUtils.isNotBlank(s);
            })
            .forEach(comment ->
            {
                comments.add(formatGeneralComment(comment));
            });
    
        if (!comments.isEmpty()) {
            contract.setGeneralComments(comments);
        }
    }
    
    String formatGeneralComment(String str){
        System.out.println("str="+str);
        return str.toLowerCase();
    }
}
