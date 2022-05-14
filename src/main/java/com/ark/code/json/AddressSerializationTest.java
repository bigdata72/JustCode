package com.ark.code.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.print.Book;
import java.io.Writer;

public class AddressSerializationTest {
    public static void main(String[] args) throws Exception {
        Address addr = Address.builder()
            .unstructuredAddress01("14 Lovell Dr")
            .unstructuredAddress02("PLainsboro")
            .city("Plainsboro")
            .state("NJ")
            .postalCode("08536").build();
    
        ObjectMapper mapper = new ObjectMapper();
        
        
        String jsonWriter = mapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(addr);
        System.out.println(jsonWriter);
    }
}
