package spring.security.user.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.user.api.model.AppUser;
import spring.security.user.api.repository.UserRepository;
import spring.security.user.api.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @AllArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if(appUser == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //for each role user we will add authorities/permissions to arraylist with the role name
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        //this authorities will send to spring
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser save(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getUsername());
        //encode password user before save it in the database
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
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
