package spring.security.user.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Spring will be in charge of configuration
@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { //this class provides us methods to personalize the security configuration
    //this attribute/bean will be injected in our class with the constructor
    private final UserDetailsService userDetailsService; //we need to implement this class and override one of its methods to configure this bean
    //another bean that will help us to encrypt passwords
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //we have to manage these beans for our project

    /*
    This method allow us to configure the authentication process for the users with their username and password
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //login process
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }


}
