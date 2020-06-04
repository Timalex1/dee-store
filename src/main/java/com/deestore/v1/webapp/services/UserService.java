package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.domains.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CRUDService<User> {

    User findByUsername(String username);
}
