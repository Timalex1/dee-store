package com.deestore.v1.webapp.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
@Setter @Getter
public class Customer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String customerPhone;

    
    private String email;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "shippingAddressId")
//    private ShippingAddress shippingAddress;

    @Embedded
    private BillingAddress billingAddress;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

}