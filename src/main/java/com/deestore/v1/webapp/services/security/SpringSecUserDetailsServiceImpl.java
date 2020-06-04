package com.deestore.v1.webapp.services.security;

import com.deestore.v1.webapp.domains.User;
import com.deestore.v1.webapp.repositories.UserRepository;
import com.deestore.v1.webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by donaldsmallidge on 4/13/17.
 */
@Service
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user != null){
            System.out.println(user.toString());
        }

        return userUserDetailsConverter.convert(userRepo.findByUsername(username));
    }

}
