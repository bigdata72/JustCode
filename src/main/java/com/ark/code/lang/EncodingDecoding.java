package com.ark.code.lang;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EncodingDecoding {
    public static void main(String[] args) throws Exception{
        
        byte[] input = { 'w' };
        InputStream inputStream = new ByteArrayInputStream(input);
        InputStreamReader reader = new InputStreamReader(inputStream);
        
        System.out.println("file.encoding from system property = "+System.getProperty("file.encoding"));
        System.out.println("Charset.defaultCharset() = "+Charset.defaultCharset());
        System.out.println("Default Charset display name = "+Charset.defaultCharset().displayName());
        System.out.println("Encoding from InputStreamReader = "+reader.getEncoding());
        System.out.println("----");
        
//        System.out.println("convertToBinary(\"T\", \"US-ASCII\")"+convertToBinary("T", "US-ASCII"));        
//        System.out.println(convertStringToBinary("T"));        
//        String iso88591="ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ";
        
        String iso88591 = loadStringFromFile("C:\\Users\\akundu\\projects\\JustCode\\src\\main\\resources\\inputfile.txt");
        
        System.out.println("iso88591 string="+iso88591);
        System.out.println(Arrays.toString(iso88591.getBytes(StandardCharsets.UTF_8)));
        
        // ISO_8859_1
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(iso88591);
        String iso88591Encoded = StandardCharsets.UTF_8.decode(byteBuffer).toString();
        
        System.out.println("Encoding in ISO_8859_1 and decoding in ISO_8859_1 ....");
        System.out.println("iso88591 after encoding and decoding in ISO_8859_1 ="+iso88591Encoded);
        System.out.println("------");
        
        // UTF_8
        System.out.println();
        System.out.println("Encoding in UTF_8 and decoding in UTF_8");
        
        ByteBuffer byteBuffer2 = StandardCharsets.UTF_8.encode(iso88591);
        String utfEncoded = StandardCharsets.UTF_8.decode(byteBuffer2).toString();
        System.out.println("iso88591 after encoding and decoding in UTF_8 = "+utfEncoded);
    }
    
    public static String loadStringFromFile(String filePath) {
        String str = "";
        try{
            str = new String(
                Files.readAllBytes(Paths.get(filePath)), StandardCharsets.ISO_8859_1);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return str;
    }
    
    public static String convertToBinary(String input, String encoding)
        throws UnsupportedEncodingException {
        byte[] encoded_input = Charset.forName(encoding)
            .encode(input)
            .array();
        System.out.println("Arrays.toString( \""+input+"\") ="+Arrays.toString(encoded_input));
        
        return IntStream.range(0, encoded_input.length)
            .map(i -> encoded_input[i])
            .mapToObj(e -> Integer.toBinaryString(e ^ 255))
            .map(e -> String.format("%1$" + Byte.SIZE + "s", e).replace(" ", "0"))
            .collect(Collectors.joining(" "));
    }
    
    public static String convertStringToBinary(String input) {
        
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                    .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();
        
    }
    
    /*
    @Test
    public void isNotISO8859_1(){
        String testString = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ";
        byte[] bytes = testString.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        String iso8859_1_str = new String(bytes, StandardCharsets.ISO_8859_1);
        System.out.println("iso8859_1_str is ="+iso8859_1_str);
        assertThat(iso8859_1_str).isNotEqualTo(testString);        
    }
    
    @Test
    public void isUTF8(){        
        String testString = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ";
        byte[] bytes = testString.getBytes();
        System.out.println(Arrays.toString(bytes));
        String utf8_str = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("utf8_str ="+utf8_str);
        assertThat(utf8_str).isEqualTo(testString);
    }
    
    @Test
    public void decodeTo8859_1(){
        String input = "UNB+UNOC:3+CU2100:ZZZ+INTTRANG2:ZZZ+230804:1044+RG000000000001'UNH+RG0000000000R1+IFTMBF:D:99B:UN:2.0'BGM+335+BK_ISO8859_1ChrSet_20SepR101+9'CTA+IC+:BOOKINGÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'COM+rupam.gogoi@e2open.com:EM'DTM+137:202305051040:203'TSR+27'FTX+AAI+++BKGComment-ISO-8859-1character set ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'GDS+11'LOC+7+NLRTM:181:6:ROTT1TERDAMÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß'DTM+63:20230503:102'LOC+88+HKHKG:181:6:NEWARK1'LOC+74+INBOM:181:6:ROTTERDAM+:::NETHERLANDS'LOC+75+HKHKC:181:6:ROTTERDAM+HK:162:5'LOC+197+USNYC:181:6:NEWYORK+US:162:5+:::NY'RFF+FF:0903BK3POSITIVE_R1'RFF+EX:ExportÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß'DTM+182:20230420:102'TDT+10++2'LOC+9+CANWP:139:6:àáâãäåæçèéêëìíîï+CA:162:5:ðñòóôõö÷øùúûüýþÿ+:::ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'DTM+133:20230301:102'LOC+11+TRCAN:139:6:Can+TR:162:5:TURKEY+:::Canakkale'DTM+132:20230302:102'TDT+20+VÀÁÂÃÄÅÆÇ+1+++++:::VÀÁÂÃÄÅÆÇ'LOC+9+INBOM:139:6:àáâãäåæçèéêëìíîï+IN:162:5:INDIA+:::àáâãäåæçèéêëìíîï'DTM+133:20230311:102'LOC+11+SGSIN:139:6:ðñòóôõö÷øùúûüýþÿ+SG:162:5:ðñòóôõö÷øùúûüýþÿ'DTM+132:20230312:102'TDT+30++38'LOC+9+USNY3:139:6:Hamden+US:162:5:UNITEDSTATES+:::NewYork'DTM+133:20230321:102'LOC+11+USNY5:139:6:NorthSalem+US:162:5:UNITEDSTATES+:::NewYork'DTM+132:20230322:102'NAD+CA+802435:160:192+PRFONàáâãäåæçèéêëìíîï'CTA+IC+:PARTYCONTACTNAME135characters'COM+12356789012345:TE'NAD+ZZZ+802442:160:192'CTA+IC+:BOOKINGPARTYCONTACT'COM+3024452239:TE'NAD+CZ+802443:160:192+NA1_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA2_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA3_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA4_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'CTA+IC+:SHIPPERCONTACT'COM+3024452239:TE'NAD+FW+802443:160:192'CTA+IC+:\u008A,\u009A,Ù,Ú,û,ü,×,÷,²,å,\u009C,Ü'COM+3024452239:TE'CPI+4++P'LOC+57+USNYC:181:6:ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ+US:162:5+:::ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'GID+1+11:1A1:67:54:ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ+22::::àáâãäåæçèéêëìíîï'PIA+5+222333:HS'FTX+AAA+++Cargo-Special characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'MEA+AAE+G+LBR:222'MEA+AAE+AAL+LBR:222.123'DGS+CFR+8:545:40-20+2683+01.0:CEL+2+F-ES-C++++3:6.1'FTX+AAC+++GeneralFTX_AAC_Special characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+HAZ+++HazardousplacardinformationSpecial characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+PKG++6HA1:183:ZZZ'FTX+AAC++WASTE:122:ZZZ'FTX+AAC++HOT:122:ZZZ'FTX+AAC++EHTIME:122:ZZZ+20231124:CCYYMMDD'FTX+AAC++EQ:122:ZZZ+E2'FTX+AAC++SEG:122:ZZZ+SGG1a'FTX+AAC++SEG:122:ZZZ+SGG2'FTX+AAC++SEG:122:ZZZ+SGG3'FTX+AAC++SEG:122:ZZZ+SGG16'FTX+AAC++RQ:122:ZZZ'FTX+AAC++ERG:122:ZZZ+99999'FTX+AEP+++RadioactiveGoodsInformationSpecial characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+AEB++SADT:122:ZZZ+222.11:CEL'FTX+AEB++ETMP:122:ZZZ+4444.33:CEL'FTX+AEB++CTMP:122:ZZZ+333.22:CEL'FTX+REG+++RegulatoryInformationSpecial characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+HAN++4:122:ZZZ'FTX+AAC++GAS:122:ZZZ'FTX+AAC++IHL:122:ZZZ+B'FTX+AAC++P:122:ZZZ'FTX+AAD++PSN:122:ZZZ+ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+AAD++TLQ:122:ZZZ'FTX+AAD++TN:122:ZZZ+TechnicalNameÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'CTA+HE+RR3RDDDDDDDDDDDD1:QAName1ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'COM+?+919999999999:TE'CTA+HE+RR3RDDDDDDDDDDDD2:QAName2ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß'COM+?+919999999998:TE'CTA+HE+RR3RDDDDDDDDDDDD3:QAName3àáâãäåæçèéêëìíîï'COM+?+919999999997:TE'MEA+AAE+AAF+KGM:123.123'MEA+AAE+AAX+MTQ:123.1234'MEA+AAE+AEO+MBQ:666.234'MEA+AAE+ZZZ+PIW:555.123'EQD+CN++20T5:102:5:20àáâãäåæçèéêëìíîï'EQN+1:2'TMD+3++CC'HAN+NAR'FTX+AGK+++ContainerFTX_AGK_Special characters ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ ÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞß àáâãäåæçèéêëìíîï ðñòóôõö÷øùúûüýþÿ'FTX+SSR++FMG:130:ZZZ+2222222222-ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ'NAD+CL++NA1_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA2_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA3_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA4_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA5_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ++ST1_àáâãäåæçèéêëìíîï:ST2_àáâãäåæçèéêëìíîï'DTM+396:202302010341:203'DTM+200:202302010342:203'CTA+AO+:CTAAO_àáâãäåæçèéêëìíîï'COM+qatest1@inttra.com:EM'NAD+EV++NameandAdreessNameandAdreesEV1:NameandAdreessNameandAdreesEV2:NameandAdreessNameandAdreessna:NameandAdreessNameandAdreessna:NameandAdreessNameandAdreessna'CTA+AO+:EQUIPMENTCONTACTAN35'COM+qatest1@inttra.com:EM'NAD+CK++NameandAdreessNameandAdreesCK1:NameandAdreessNameandAdreesCK2:NameandAdreessNameandAdreessna:NameandAdreessNameandAdreessna:NameandAdreessNameandAdreessna'DTM+393:202302010343:203'CTA+AO+:EQUIPMENTCONTACTAN35'COM+qatest1@inttra.com:EM'NAD+SF++SHIPTONAME1AN35:SHIPFROMNAME2AN35SHIPFROM:NAME3AN35SHIPFROMNAME4AN35:SHIPFROMNAME5AN35STREETNUMBER1:AN35STREETNUMBER2AN35+++++582037+US'DTM+181:202302010344:203'DTM+200:202302010346:203'CTA+AO+:EQUIPMENTCONTACTAN35'COM+9123456789012345:TE'NAD+ST++NA1_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA2_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA3_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA4_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ:NA5_ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏ+++++582037+US'DTM+2:202302010342:203'NAD+SU'DTM+530:202305301712:203'UNT+61+RG0000000000R1'UNZ+1+RG000000000001'";
        String decoded = new String(input.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1);
        
        System.out.println(decoded);
    }
     */
}
