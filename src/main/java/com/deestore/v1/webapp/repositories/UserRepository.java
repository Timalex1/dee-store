package com.deestore.v1.webapp.repositories;

import com.deestore.v1.webapp.domains.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteUserById(Long id);
    User findUserById(Long id);
}
