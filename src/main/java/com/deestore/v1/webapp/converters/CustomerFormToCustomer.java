package com.deestore.v1.webapp.converters;

import com.deestore.v1.webapp.commands.CustomerForm;
import com.deestore.v1.webapp.domains.BillingAddress;
import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.domains.ShippingAddress;
import com.deestore.v1.webapp.domains.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {
    @Override
    public Customer convert(CustomerForm customerForm) {

        Customer customer = new Customer();
        customer.setUser(new User());
        customer.setBillingAddress(new BillingAddress());
//        customer.setShippingAddress(new ShippingAddress());
        customer.getUser().setId(customerForm.getUserId());
        customer.setId(customerForm.getCustomerId());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPassword());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setCustomerPhone(customerForm.getPhoneNumber());

        return customer;

    }
}
