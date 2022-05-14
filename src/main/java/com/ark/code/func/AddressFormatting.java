package com.ark.code.func;

import com.google.common.base.Splitter;
import com.ark.code.func.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/*
100G PASIR PANJANG ROAD,#08-19, LOBBY 2 @ INTERLOCAL CENTRE,  SINGAPORE 118523Tel: (65) 6471-7588  Fax: (65) 6473-8991  Business Reg. No. 199603700R
[100G, PASIR, PANJANG, ROAD,#08-19,, LOBBY, 2, @, INTERLOCAL, CENTRE,, , SINGAPORE, 118523Tel:, (65), 6471-7588, , Fax:, (65), 6473-8991, , Business, Reg., No., 199603700R]
[100G, PASIR, PANJANG, ROAD,#08-19,, LOBBY, 2, @, INTERLOCAL, CENTRE,, SINGAPORE, 118523Tel:, (65), 6471-7588, Fax:, (65), 6473-8991, Business, Reg., No., 199603700R]
[100G, PASIR, PANJANG, ROAD,#08-19,, LOBBY, 2, @, INTERLOCAL, CENTRE,, , SINGAPORE, 118523Tel:, (65), 6471-7588, , Fax:, (65), 6473-8991, , Business, Reg., No., 199603700R]
 */

@Slf4j
public class AddressFormatting {
    
    public static final String REGEX_WHITESPACE = " ";
    public static final int UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH = 35;
    public static final int GENERAL_COMMENTS_MAX_FIELD_LENGTH = 512;
    
    public static void main(String[] args) {
/*        Address address = Address.builder()
            .unstructuredAddress01("100G PASIR PANJANG ROAD,#08-19, LOB")
            .unstructuredAddress02("BY 2 @ INTERLOCAL CENTRE,  SINGAPOR")
            .unstructuredAddress03("E 118523Tel: (65) 6471-7588  Fax: (")
            .unstructuredAddress04("65) 6473-8991  Business Reg. No. 19")
            .street01("9603700R")
            .build();*/
    
  /*      Address address = Address.builder()
            .unstructuredAddress01("100G CENTER 35 ROAD PASI DRIVES RIV")
            .unstructuredAddress02("ERDRIVE CENTER 95 FOXIRUNS LENGTH 3")            
            .unstructuredAddress03("5 KNIGHTDR AVENUE 65 NAMIBEEN DRIVE")            
            .unstructuredAddress04("S 73 ROAD TEST LENGTH SPRINGFILD LE")
            .street01("NGTH 76 ROADTEST DRIVES 42 KNIGHTDR")
            .street02(" LENGTH 35 ROAD STREET 35 SPRINGFLD")            
            .build();*/
    
        Address address = Address.builder()
            .unstructuredAddress01("TEST LENGTH 35 TEXT TEST LENGTH 35T")
            .unstructuredAddress02("EXTTEST LENGTH 35 TEXTTEST LENGTH 3")
            .unstructuredAddress03("5 TEXTTEST LENGTH 35 TEXTTEST LENGT")
            .unstructuredAddress04("H 35 TEXT TEST LENGTH 35TEXTTEST LE")
            .street01("NGTH 35 TEXTTEST LENGTH 35 TEXTTEST")
            .street02("LENGTH 35 TEXT LENGTH 35 TEXTTE210")
            .build();
        
        List<String> generalComments = new ArrayList<>();
        generalComments.add("General InformationGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information General Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information T1024");
        
        
        printGeneralComments(generalComments);
        
        printFieldLengths(address, "Before");
        
        String fullAddressLine = concatenateFields(address);
        
        List<String> addressFieldTokens = new ArrayList<>(Splitter.on(REGEX_WHITESPACE).trimResults().splitToList(fullAddressLine));
        log.debug("Token size ="+addressFieldTokens.size());
    
        if(StringUtils.isNotBlank(address.getUnstructuredAddress01())){
            address.setUnstructuredAddress01(getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }
        
        if(StringUtils.isNotBlank(address.getUnstructuredAddress02())){
            address.setUnstructuredAddress02(
                getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }    
    
        if(StringUtils.isNotBlank(address.getUnstructuredAddress03())){
            address.setUnstructuredAddress03(
                getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }
    
        if(StringUtils.isNotBlank(address.getUnstructuredAddress04())){
            address.setUnstructuredAddress04(
                getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }
    
        if(StringUtils.isNotBlank(address.getStreet01())){
            address.setStreet01(
                getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }
    
        if(StringUtils.isNotBlank(address.getStreet02())){
            address.setStreet02(
                getFormattedFieldComponent(addressFieldTokens, UNSTRUCTURED_ADDRESS01_MAX_FIELD_LENGTH));
        }
        
        printFieldLengths(address, "After");
        
        List<String> generalCommentsToken = new ArrayList<>(Splitter.on(REGEX_WHITESPACE).trimResults().splitToList(generalComments.get(0)));
        log.debug("General COmments token size = {}" ,generalCommentsToken.size());
        
        String field1 = getFormattedFieldComponent(generalCommentsToken, GENERAL_COMMENTS_MAX_FIELD_LENGTH);
        String field2 = getFormattedFieldComponent(generalCommentsToken, GENERAL_COMMENTS_MAX_FIELD_LENGTH);
        
        log.debug("Field1 after format = {}", field1);
        log.debug("Field2 after format = {}", field2);
        
    }
    
    /**
     * Any leading whitespace for address field compoenent will be trimmed
     * @param tokens
     * @param maxAddressFieldLength
     * @return
     */
    private static String getFormattedFieldComponent(List<String> tokens, int maxAddressFieldLength){
        StringBuilder bld = new StringBuilder(35);
        int tokenIndex = 0; 
        List<String> tokenCopy = new ArrayList<>(tokens);
        
        while(bld.length() <= maxAddressFieldLength && tokenIndex < tokenCopy.size()){
            
            if(isWithinTotalFieldLength(maxAddressFieldLength, bld.length(), 1)) {
                // only add whitespace if not at start and if the addition of next token does not go over max length
                    if(tokenIndex != 0 && 
                        isWithinTotalFieldLength(maxAddressFieldLength, bld.length(), tokenCopy.get(tokenIndex).length())) {
                            bld.append(REGEX_WHITESPACE);
                    }
            }else{
                // reached allowed max length for field already, cannot add whitespace                
                break;
            }
    
            if(isWithinTotalFieldLength(maxAddressFieldLength, bld.length(), tokenCopy.get(tokenIndex).length())){
                    bld.append(tokenCopy.get(tokenIndex));
                    tokens.remove(tokenCopy.get(tokenIndex));
            }else{
                // reached allowed max length for field already, cannot add any more tokens
                break;
            }
            tokenIndex++;
        }
        return bld.toString();
    }   

    
    static boolean isWithinTotalFieldLength(int maxLength, int currLength, int add){
        //log.debug("length will be="+(currLength + add));
        return currLength + add <= maxLength;
    }
    
    static void printFieldLengths(Address addr, String stage){
        if(addr.getUnstructuredAddress01() != null)
            log.debug("{}, Value={}, unstructAddr01 length={}", stage, addr.getUnstructuredAddress01(), addr.getUnstructuredAddress01().length());
        if(addr.getUnstructuredAddress02() != null)
            log.debug("{}, Value={}, unstructAddr02 length={}", stage, addr.getUnstructuredAddress02(), addr.getUnstructuredAddress02().length());
        if(addr.getUnstructuredAddress01() != null)
            log.debug("{}, Value={}, unstructAddr03 length={}", stage, addr.getUnstructuredAddress03(), addr.getUnstructuredAddress03().length());
        if(addr.getUnstructuredAddress04() != null)
            log.debug("{}, Value={}, unstrucAddr04 length={}", stage, addr.getUnstructuredAddress04(), addr.getUnstructuredAddress04().length());
        if(addr.getStreet01() != null)
            log.debug("{}, Value={}, street01 length={}", stage, addr.getStreet01(), addr.getStreet01().length());
        if(addr.getStreet02() != null)
            log.debug("{}, Value={}, street02 length={}", stage, addr.getStreet02(), addr.getStreet02().length());
    }
    
    static void printGeneralComments(List<String> comments){
        String field1 = null;
        String field2 = null;
        for(String comment : comments) {
            field1 = comment.substring(0, 512);
            field2 = comment.substring(512);
            System.out.println("field1=" + field1);
            System.out.println("field2=" + field2);
        }
        /* Before 
        field1=
        General InformationGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information General Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Informati
        field2=
        on TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information T1024
        
        After
        field1=
        General InformationGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information General Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral Information TextGeneral
         */
    }
    
    static String concatenateFields(Address addr){ // use this 
        StringBuilder bldr = new StringBuilder(235);
        bldr.append(addr.getUnstructuredAddress01() != null ? addr.getUnstructuredAddress01() : "")
            .append(addr.getUnstructuredAddress02() != null ? addr.getUnstructuredAddress02() : "")
            .append(addr.getUnstructuredAddress03() != null ? addr.getUnstructuredAddress03() : "")
            .append(addr.getUnstructuredAddress04() != null ? addr.getUnstructuredAddress04() : "")
            .append(addr.getStreet01() != null ? addr.getStreet01() : "")
            .append(addr.getStreet02() != null ? addr.getStreet02() : "")
            .append(addr.getCity() != null ? addr.getCity() : "");
        log.debug("\nConcatenated value ="+bldr);
        return bldr.toString();
        
    }
}
