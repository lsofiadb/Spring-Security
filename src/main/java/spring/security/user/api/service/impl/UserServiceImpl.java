package spring.security.user.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.user.api.model.AppUser;
import spring.security.user.api.repository.UserRepository;
import spring.security.user.api.service.UserService;

import java.util.List;
@Service @AllArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public AppUser save(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getUsername());
        return userRepository.save(appUser);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {} from the database", username);
        return userRepository.findByUsername(username);
    }
}
