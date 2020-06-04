package com.deestore.v1.webapp.services.jpaservices;

import com.deestore.v1.webapp.domains.security.Role;
import com.deestore.v1.webapp.repositories.RoleRepository;
import com.deestore.v1.webapp.repositories.UserRepository;
import com.deestore.v1.webapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
        private RoleRepository roleRepository;

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findAuthorityById(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteAuthorityById(id);
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
