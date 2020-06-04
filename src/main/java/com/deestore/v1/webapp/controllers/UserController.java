package com.deestore.v1.webapp.controllers;


import com.deestore.v1.webapp.domains.User;
import com.deestore.v1.webapp.domains.security.Role;
import com.deestore.v1.webapp.services.RoleService;
import com.deestore.v1.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model) {

        model.addAttribute("users", userService.listAll());

        return "admin/user/user_list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.getById(id));

        return "user/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("user", userService.getById(id));

        return "admin/user/user_add";
    }

    @RequestMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "admin/user/user_add";
    }

    @RequestMapping(value = {"/", ""},  method = RequestMethod.POST)
    public String saveOrUpdate(User user) {
        Role role = roleService.findByRole("ADMIN");
        user.addRole(role);
        User savedUser = userService.saveOrUpdate(user);
//        return "redirect:/user/show/" + savedUser.getId();
        return "redirect:/admin/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        userService.delete(id);

        return "redirect:/user/list";
    }
}
