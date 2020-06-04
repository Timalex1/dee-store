package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
    Role findAuthorityById(Long Id);
    void deleteAuthorityById(Long id);
}
