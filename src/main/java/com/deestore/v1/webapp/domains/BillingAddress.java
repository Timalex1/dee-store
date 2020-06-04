package com.deestore.v1.webapp.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Embeddable
@Getter @Setter
public class BillingAddress {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;

//    @OneToOne(mappedBy = "billingAddress")
//    private Customer customer;


}
