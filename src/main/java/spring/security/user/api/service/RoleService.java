package spring.security.user.api.service;

import spring.security.user.api.model.Role;

public interface RoleService {
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
}
