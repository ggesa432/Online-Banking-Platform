package com.synergisticit.config;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collections;
import java.util.Optional;

/**
 * @author GesangZeren
 * @project OnlineBank - Assessment
 * @date 1/28/2025
 */
@Configuration
public class AdminInitializerConfig implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "admin";

        // Check if the admin user already exists
        Optional<User> adminUser = userService.getUserByUsername(adminUsername);

        if (adminUser.isEmpty()) {
            System.out.println("Admin user not found.");

            Role adminRole = roleService.getRoleByName("ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRoleName("ADMIN");
                roleService.createRole(adminRole);
                System.out.println("Created ROLE: ADMIN");
            }

            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setPassword("admin123");
            admin.setEmail("admin@gmail.com");
            admin.setRoles(Collections.singletonList(adminRole));


            userService.createUser(admin);
            System.out.println("Admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
