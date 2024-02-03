package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;

@Component
public class RolesUtil {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public RolesUtil(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @PostConstruct
    public void createAdmin() {
        var mayBeAdminRole = roleRepository.findByName("ROLE_ADMIN");
        Role adminRole = null;
        if(mayBeAdminRole.isEmpty()) {
            var role = new Role();
            role.setName("ROLE_ADMIN");
            adminRole = roleRepository.save(role);
        } else {
            adminRole = mayBeAdminRole.get();
        }

        if(userService.findByRole(adminRole).isEmpty()) {
            var admin = User.builder()
                    .name("admin")
                    .password("admin")
                    .roles(Set.of(adminRole))
                    .build();
            userService.save(admin);
        }
    }
}
