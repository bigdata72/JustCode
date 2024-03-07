package com.ark.code.lang;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ISO88591SpecialChars {
    public static void main(String[] args) throws Exception{
        String isoSpecialChars = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ";
        String lineInput = "The façade pattern is a software design pattern";
        byte[] input = { 'w' };
        InputStream inputStream = new ByteArrayInputStream(input);
        InputStreamReader reader = new InputStreamReader(inputStream);
        
        System.out.println("file.encoding property is set in VM options by -Dfile.encoding= \"ISO-8859-1\" ");
            
        System.out.println("file.encoding from system property = "+System.getProperty("file.encoding"));
        System.out.println("Charset.defaultCharset() = "+Charset.defaultCharset());        
        System.out.println("Encoding from InputStreamReader = "+reader.getEncoding());
        
        System.out.println("-----------");
        System.out.println("set file encoding to ISO-8859-1");
        
        System.setProperty("file.encoding", "ISO-8859-1");
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
        
        System.out.println(isoSpecialChars);
        System.out.println(decodeText(lineInput, "ISO-8859-1"));
        System.out.println(decodeText(lineInput, "US-ASCII"));
        System.out.println(decodeText(lineInput, "UTF-8"));
        
        System.out.println("--------------------");
        System.out.println();
        System.out.println(convertToBinary("?", "Big5"));
        System.out.println(convertToBinary("T", "US-ASCII"));
        
    }
    
    public static String decodeText(String input, String encoding) throws IOException {
        return
            new BufferedReader(
                new InputStreamReader(
                    new ByteArrayInputStream(input.getBytes()),
                    Charset.forName(encoding)))
                .readLine();
    }
    
    public static String convertToBinary(String input, Charset charset)
        throws UnsupportedEncodingException {
        byte[] encoded_input = charset.encode(input)
            .array();
        return IntStream.range(0, encoded_input.length)
            .map(i -> encoded_input[i])
            .mapToObj(e -> Integer.toBinaryString(e ^ 255))
            .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
            .collect(Collectors.joining(" "));
    }
    
    public static String convertToBinary(String input, String encoding)
        throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
            .encode(input)
            .array();
        return IntStream.range(0, encoded_input.length)
            .map(i -> encoded_input[i])
            .mapToObj(e -> Integer.toBinaryString(e ^ 255))
            .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
            .collect(Collectors.joining(" "));
    }
}
