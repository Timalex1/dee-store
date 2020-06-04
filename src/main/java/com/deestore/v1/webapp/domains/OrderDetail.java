package com.deestore.v1.webapp.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "orderStatus")
    private Order order;

    @OneToOne
    @JoinColumn(referencedColumnName = "name")
    private Product product;

    private Integer quantity;


}
