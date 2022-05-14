package com.ark.code.stream.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingRequestContract {

    private List<String> generalComments = new ArrayList<String>();
    

}
