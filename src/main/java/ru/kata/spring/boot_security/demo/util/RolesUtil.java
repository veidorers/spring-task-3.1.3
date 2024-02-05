package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Gender;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class RolesUtil {
    private final UserService userService;

    @Autowired
    public RolesUtil(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostConstruct
    public void createAdmin() {
        var mayBeAdmin = userService.findByRole(Role.ROLE_ADMIN);
        if (mayBeAdmin.isEmpty()) {
            var admin = User.builder()
                    .name("admin")
                    .password("admin")
                    .age(30)
                    .gender(Gender.MALE)
                    .roles(Set.of(Role.ROLE_ADMIN))
                    .build();
            userService.save(admin);
        }
    }
}
