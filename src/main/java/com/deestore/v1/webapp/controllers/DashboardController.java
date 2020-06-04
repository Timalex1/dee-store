package com.deestore.v1.webapp.controllers;

import com.deestore.v1.webapp.services.ProductService;
import com.deestore.v1.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/admin/dashboard","/admin","/admin/index"})
    public String adminDash(Model model){
        Map<String, Object> map = new HashMap<>();

        map.put("products", productService.listAll());
        model.addAllAttributes(map);

        return "admin/index";
    }

}
