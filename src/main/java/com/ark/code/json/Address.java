package com.ark.code.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    public static final String ERROR_999990 = "999990";
    
    @Size(max = 35, message = ERROR_999990)
    private String unstructuredAddress01;
    
    @Size(max = 35, message = ERROR_999990)
    private String unstructuredAddress02;
    
    @Size(max = 35, message = ERROR_999990)
    private String unstructuredAddress03;
    
    @Size(max = 35, message = ERROR_999990)
    private String unstructuredAddress04;
    
    @Size(max = 35, message = ERROR_999990)
    private String street01;
    
    @Size(max = 35, message = ERROR_999990)
    private String street02;
    
    @Size(max = 35, message = ERROR_999990)
    private String city;
    
    @Size(max = 35, message = ERROR_999990)
    private String state;
    
    @Size(max = 17, message = ERROR_999990)
    private String postalCode;
    
   
    // Below are some utility methods to indicate address line are complete or empty. This is needed for address line formatting in outbound
    
    public boolean addressHasCompleteAddressLines(){
        if((allLinesComplete()) ||
            // all 1st five address lines have complete words        
            (firstFiveAddressLinesComplete() && isAddressLine6Empty()) ||
            // all 1st four address lines have complete words
            (firstFourAddressLinesComplete() && isAddressLine5Empty() && isAddressLine6Empty()) ||
            // all 1st three address lines have complete words
            (firstThreeAddressLinesComplete() && lastThreeAddressLinesEmpty()) ||
            // only 1st two address lines have complete words
            (firstTwoAddressLinesComplete() && lastFourAddressLinesEmpty())
        ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean allLinesComplete(){
        return firstFiveAddressLinesComplete() && isAddressLine6Complete();
    }
    
    public boolean firstFiveAddressLinesComplete(){
        return firstFourAddressLinesComplete() && isAddressLine5Complete();
    }
    
    public boolean firstFourAddressLinesComplete(){
        return firstThreeAddressLinesComplete() && isAddressLine4Complete();
    }
    
    public boolean firstThreeAddressLinesComplete(){
        return firstTwoAddressLinesComplete() && isAddressLine3Complete();
    }
    
    public boolean firstTwoAddressLinesComplete(){
        return isAddressLine1Complete() && isAddressLine2Complete();
    }
    
    public boolean lastThreeAddressLinesEmpty(){
        return isAddressLine4Empty() && isAddressLine5Empty() && isAddressLine6Empty();
    }
    
    public boolean lastFourAddressLinesEmpty(){
        return isAddressLine3Empty() && lastThreeAddressLinesEmpty();
    }
    
    @JsonIgnore
    public boolean isAddressLine1Complete()
    {
        return StringUtils.isNotBlank(getUnstructuredAddress01()) && getUnstructuredAddress01().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine2Complete()
    {
        return StringUtils.isNotBlank(getUnstructuredAddress02()) && getUnstructuredAddress02().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine3Complete()
    {
        return StringUtils.isNotBlank(getUnstructuredAddress03()) && getUnstructuredAddress03().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine4Complete()
    {
        return StringUtils.isNotBlank(getUnstructuredAddress04()) && getUnstructuredAddress04().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine5Complete()
    {
        return StringUtils.isNotBlank(getStreet01()) && getStreet01().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine6Complete()
    {
        return StringUtils.isNotBlank(getStreet02()) && getStreet02().length() ==35;
    }
    
    @JsonIgnore
    public boolean isAddressLine3Empty(){
        return StringUtils.isEmpty(getUnstructuredAddress03());
    }
    
    @JsonIgnore
    public boolean isAddressLine4Empty(){
        return StringUtils.isEmpty(getUnstructuredAddress04());
    }
    
    @JsonIgnore
    public boolean isAddressLine5Empty(){
        return StringUtils.isEmpty(getStreet01());
    }
    
    @JsonIgnore
    public boolean isAddressLine6Empty(){
        return StringUtils.isEmpty(getStreet02());
    }
}

