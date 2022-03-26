package spring.security.user.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.security.user.api.model.AppUser;
import spring.security.user.api.model.Role;
import spring.security.user.api.service.RoleService;
import spring.security.user.api.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService){
		return args -> {
			roleService.save(new Role(null,"ROLE_USER"));
			roleService.save(new Role(null,"ROLE_MANAGER"));
			roleService.save(new Role(null,"ROLE_ADMIN"));
			roleService.save(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.save(new AppUser(null,"Emma Watson","emma","1234",new ArrayList<>()));
			userService.save(new AppUser(null,"Jim Carrey","jim","1234",new ArrayList<>()));
			userService.save(new AppUser(null,"Steve Taylor","steve","1234",new ArrayList<>()));
			userService.save(new AppUser(null,"Axl Rose","axl","1234",new ArrayList<>()));

			roleService.addRoleToUser("emma","ROLE_USER");
			roleService.addRoleToUser("emma","ROLE_MANAGER");
			roleService.addRoleToUser("jim","ROLE_MANAGER");
			roleService.addRoleToUser("steve","ROLE_ADMIN");
			roleService.addRoleToUser("axl","ROLE_SUPER_ADMIN");
			roleService.addRoleToUser("axl","ROLE_ADMIN");
			roleService.addRoleToUser("axl","ROLE_MANAGER");

		};
	}

}
