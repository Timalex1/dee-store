package com.deestore.v1.webapp.controllers;

import com.deestore.v1.webapp.commands.CustomerForm;
import com.deestore.v1.webapp.commands.validators.CustomerFormValidator;
import com.deestore.v1.webapp.converters.CustomerToCustomerForm;
import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 *
 * Create a controller. Implement the ability to
 * list,
 * show one customer,
 * add a customer,
 * update a customer, and
 * delete a customer by id.
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;
    private Validator customerFormValidator;
    private CustomerToCustomerForm customerToCustomerForm;

    @Autowired
    public void setCustomerService(CustomerService customerService) {

        this.customerService = customerService;
    }

    @Autowired
    @Qualifier("customerFormValidator")
    public void setCustomerFormValidator(CustomerFormValidator customerFormValidator) {

        this.customerFormValidator = customerFormValidator;
    }

    @Autowired
    public void setCustomerToCustomerForm(CustomerToCustomerForm customerToCustomerForm) {

        this.customerToCustomerForm = customerToCustomerForm;
    }

    @RequestMapping({"/list", "", "/"})
    public String listCustomers(Model model) {

        model.addAttribute("customers", customerService.listAll());

        return "user_list";
    }

    @RequestMapping("/show/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {

        model.addAttribute("customer", customerService.getById(id));

        return "customer/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Customer customer = customerService.getById(id);

        model.addAttribute("customerForm", customerToCustomerForm.convert(customer));

        return "admin/user/user_add";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        // Note: This method does NOT use customerService.
        model.addAttribute("customerForm", new CustomerForm());
        return "admin/user/user_add";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid CustomerForm customerForm, BindingResult bindingResult) {

        customerFormValidator.validate(customerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "admin/user/user_add";
        }
        // Note: This method returns a REDIRECT. And a POST.
        Customer newCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
//        return "redirect:admin/customer/show/" + newCustomer.getId();
        return "admin/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        // Note: This method returns a REDIRECT.
        customerService.delete(id);

        return "redirect:/customer/list";
    }

}
