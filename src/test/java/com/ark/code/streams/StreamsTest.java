package com.ark.code.streams;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsTest {
    @Test
    public void getDistinctCharactersFromWords_not_what_we_want(){
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> uniqueChars = words.stream()
            .map(s-> s.split(""))
            .distinct()
            .collect(Collectors.toList());
        assertThat(uniqueChars).isInstanceOf(List.class);
        assertThat(uniqueChars).hasOnlyElementsOfType(String[].class);
    }
    
    @Test
    public void getDistinctCharactersFromWords_usingFlatMap(){
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueChars = words.stream()
            .map(s-> s.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());
        assertThat(uniqueChars).isInstanceOf(List.class);
        assertThat(uniqueChars).hasOnlyElementsOfType(String.class);
        assertThat(uniqueChars).containsExactly("H", "e", "l", "o", "W", "r",  "d");
        
    }
}
