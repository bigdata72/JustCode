package com.ark.code.optional;

import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsingOptional {
    private ValidationContext context;
    public static void main(String[] args) {
        new UsingOptional().setup();
        
        
    }
    
    private void setup(){
        context = new ValidationContext();
        Map<PartyRole, TransactionParty> resolvedParties = context.getResolvedParties();
        Optional<Collection<TransactionParty>> parties = Optional.ofNullable(context.getResolvedParties().values());
        System.out.println(parties);
    }
    
    class ValidationContext {
        @Getter
        private Map<PartyRole, TransactionParty> resolvedParties = new HashMap<>();
        
        public ValidationContext(){
            
        }
    }
    
    enum PartyRole {
    
        Carrier("Carrier"),
        Forwarder("Freight Forwarder"),
        Booker("Booker"),
        Shipper("Shipper");
        private String description;
    
        PartyRole(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    }
    
    class TransactionParty{
        private String partyRole;
        private String partyINTTRACompanyId;
        public String getPartyId() {
            return partyINTTRACompanyId;
        }
    
        public void setRole(PartyRole partyRole){
            this.partyRole = partyRole.toString();
        }
    
        public void setPartyId(String id) {
            this.partyINTTRACompanyId = id;
        }
        
    }
}
