package com.deestore.v1.webapp.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// @Table(name = "shippingAddress")
@Setter @Getter
public class ShippingAddress {
    private static final long serialVersionUID = 7551999649936522523L;

    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;
//
//    @OneToOne(mappedBy = "shippingAddress")
//    private Customer customer;
}
