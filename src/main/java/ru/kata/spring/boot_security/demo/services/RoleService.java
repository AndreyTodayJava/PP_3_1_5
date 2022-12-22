package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.HashSet;
import java.util.Set;

public interface RoleService {

    public void addRoles(Set<String> rolesName, User user);


}
