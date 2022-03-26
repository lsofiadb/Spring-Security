package spring.security.user.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.user.api.model.AppUser;
import spring.security.user.api.model.Role;
import spring.security.user.api.repository.RoleRepository;
import spring.security.user.api.repository.UserRepository;
import spring.security.user.api.service.RoleService;
@Service @AllArgsConstructor @Transactional @Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding new role {}  to user {}", roleName, username);
        AppUser appUser = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        //Transactional - without repository is saved in DB
        appUser.getRoles().add(role);
    }


}
