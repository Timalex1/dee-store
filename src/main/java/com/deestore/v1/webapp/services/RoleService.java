package com.deestore.v1.webapp.services;

import com.deestore.v1.webapp.domains.security.Role;

public interface RoleService extends CRUDService<Role> {
    Role findByRole(String role);
}
