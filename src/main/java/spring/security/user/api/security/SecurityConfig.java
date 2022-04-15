package spring.security.user.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.security.user.api.filter.CustomAuthenticationFilter;

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
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        //Cross Site Request Forgery
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //controlling resources access with endpoints!!!
        http.authorizeRequests().antMatchers("/api/login/**").permitAll(); //we don't have to deal with this endpoint, Spring does it for us, although we can configure it
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        // http.authorizeRequests().anyRequest().permitAll(); we don´t want this because it´s necessary control the resource access with user roles
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
