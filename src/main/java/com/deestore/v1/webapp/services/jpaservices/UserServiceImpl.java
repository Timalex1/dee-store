package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.domains.Customer;
import com.deestore.v1.webapp.domains.User;
import com.deestore.v1.webapp.domains.security.Role;
import com.deestore.v1.webapp.repositories.CustomerRepository;
import com.deestore.v1.webapp.repositories.UserRepository;
import com.deestore.v1.webapp.services.UserService;
import com.deestore.v1.webapp.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return users;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(passwordEncoder.encode(domainObject.getPassword()));
        }

//        Customer c = customerRepository.save(domainObject);
        return userRepository.save(domainObject);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userRepository.findUserById(id);
        customerRepository.delete(user.getCustomer());
        userRepository.delete(user);
    }

}
