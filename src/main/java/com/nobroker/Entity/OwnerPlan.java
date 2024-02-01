package com.nobroker.Entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlan {
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
