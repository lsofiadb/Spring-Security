package spring.security.user.api.service;

import spring.security.user.api.model.Role;

public interface RoleService {
    Role save(Role role);
    void addRoleToUser(String username, String roleName);
}
