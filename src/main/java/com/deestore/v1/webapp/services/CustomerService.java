package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.commands.CustomerForm;
import com.deestore.v1.webapp.domains.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends CRUDService<Customer> {

    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);
}
