package com.deestore.v1.webapp.controllers;

import com.deestore.v1.webapp.domains.Category;
import com.deestore.v1.webapp.domains.Product;
import com.deestore.v1.webapp.domains.User;
import com.deestore.v1.webapp.domains.security.Role;
import com.deestore.v1.webapp.services.ProductService;
import com.deestore.v1.webapp.services.RoleService;
import com.deestore.v1.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping({"", "/", "/index", "/home"})
    public String homePage(Model model) {
        List<Product> products = (List<Product>) productService.listAll();

        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/register")
    public String regUser(Model model){
        model.addAttribute("user", new User());

        return "register";
    }


    @PostMapping("/register")
    public String submitUser(User user){

        Role role = roleService.findByRole("CUSTOMER");

        user.addRole(role);

        userService.saveOrUpdate(user);

        return "redirect:/login";
    }

    public void recursiveTree(Category cat) {
        System.out.println(cat.getName());
        List<Product> products = (List<Product>) productService.listAll();

        if (cat.getChildren().size() > 0) {
            for (Category c : cat.getChildren()) {
                recursiveTree(c);
                if (c.getName().equalsIgnoreCase("child3")){
                    products.forEach( product -> {
//                        product.addProductToCategory(c);
//                        productService.saveOrUpdate(product);
                    });
                }
            }
        }

    }
}