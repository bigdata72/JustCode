package com.ark.code.func.model;

import lombok.Builder;
import lombok.Data;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@Builder
public class Address {
    @Size(max = 35, message = "Max size is 35 characters")
    private String unstructuredAddress01;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String unstructuredAddress02;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String unstructuredAddress03;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String unstructuredAddress04;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String street01;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String street02;
    
    @Size(max = 35, message = "Max size is 35 characters")
    private String city;
}
