package com.arzymatova;

import com.arzymatova.domain.Role;
import com.arzymatova.domain.User;
import com.arzymatova.serive.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecJwtAmigoseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecJwtAmigoseApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            userService.saveRole(new Role(1l,"USER"));
            userService.saveRole(new Role(2l,"MANAGER"));
            userService.saveRole(new Role(3l,"ADMIN"));
            userService.saveRole(new Role(4l,"SUPER_ADMIN"));

            userService.save(new User(1l,"John Travolta","john","1234",new ArrayList<>()));
            userService.save(new User(2l,"Will Smith","will","1234",new ArrayList<>()));
            userService.save(new User(3l,"Jim Carry","jim","1234",new ArrayList<>()));
            userService.save(new User(4l,"Arnold Schwarzenegger","arnold","1234",new ArrayList<>()));

            userService.addRoleToUser("john","USER");
            userService.addRoleToUser("john","MANAGER");
            userService.addRoleToUser("will","MANAGER");
            userService.addRoleToUser("jim","ADMIN");
            userService.addRoleToUser("arnold","USER");
            userService.addRoleToUser("arnold","ADMIN");
            userService.addRoleToUser("arnold","SUPER_ADMIN");
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
