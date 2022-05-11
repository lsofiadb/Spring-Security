package spring.security.user.api.service;


import spring.security.user.api.model.AppUser;

import java.util.List;

public interface UserService{
    AppUser save(AppUser appUser);
    List<AppUser> getUsers();
    AppUser getUser(String username);
 }
