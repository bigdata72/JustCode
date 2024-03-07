package com.ark.code.stream.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Condition {
    
    public Condition(String... value){
        this.values = new ArrayList<>();
        if (value != null) {
            Arrays.stream(value).filter(e -> e != null).forEach(e -> values.add(e));
        }              
    }
    
    @NotEmpty
    private List<String> values;
    
}
