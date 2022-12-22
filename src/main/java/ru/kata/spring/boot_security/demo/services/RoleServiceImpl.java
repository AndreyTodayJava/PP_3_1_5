package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Override
    public void addRoles(Set<String> rolesName, User user) {
        Set<Role> roles = new HashSet<>();

        roles.add(new Role(2, "ROLE_USER"));
        for (String name : rolesName) {
            if (name.contains("ROLE_ADMIN")) {
                roles.add(new Role(1, "ROLE_ADMIN"));
            }
        }
        user.setRoles(roles);
    }
}
