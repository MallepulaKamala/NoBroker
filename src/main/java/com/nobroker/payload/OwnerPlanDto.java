package com.nobroker.payload;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlanDto {

    @Id
    private long planid;
    private String planname;
    private double price;
    private int planvalidity;
    private boolean relationshipmanager;
    private boolean rentalagreement;
    private boolean propertypromotion;
    private boolean guaranteedrenants;
    private boolean facebookmarketingproperty;

}
