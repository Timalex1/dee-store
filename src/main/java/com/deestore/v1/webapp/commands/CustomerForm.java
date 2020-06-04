package com.deestore.v1.webapp.commands;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class CustomerForm {

    private Long userId;
    private Long customerId;

    @NotEmpty
    @Size(min=2,max=75)
    private String userName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConf;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    private String phoneNumber;
}

