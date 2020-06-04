package com.deestore.v1.webapp.converters;

import com.deestore.v1.webapp.commands.CustomerForm;
import com.deestore.v1.webapp.domains.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerForm implements Converter<Customer, CustomerForm> {
    @Override
    public CustomerForm convert(Customer customer) {

        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setEmail(customer.getEmail());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setPhoneNumber(customer.getCustomerPhone());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserName(customer.getUser().getUsername());

        return customerForm;
    }
}
