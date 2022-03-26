package spring.security.user.api.dto;

import lombok.Data;

@Data
public class RoleToUserForm {
    private String username;
    private String roleName;
}
