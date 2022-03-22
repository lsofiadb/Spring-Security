package spring.security.user.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.user.api.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
