package com.deestore.v1.webapp.domains;

import javax.persistence.*;

@Entity
@Table(name = "query")
public class Queries {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;
    private String subject;
    private String message;
}
