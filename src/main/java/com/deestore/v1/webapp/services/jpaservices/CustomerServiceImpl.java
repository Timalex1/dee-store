package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.commands.CustomerForm;
import com.deestore.v1.webapp.converters.CustomerFormToCustomer;
import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.repositories.CustomerRepository;
import com.deestore.v1.webapp.repositories.UserRepository;
import com.deestore.v1.webapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerFormToCustomer customerFormToCustomer;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if (newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }
        return saveOrUpdate(newCustomer);
    }

    @Override
    public List<Customer> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add); // fun with Java 8
        return customers;
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findCustomerById(id);

        userRepository.delete(customer.getUser());
        customerRepository.delete(customer);
    }
}
