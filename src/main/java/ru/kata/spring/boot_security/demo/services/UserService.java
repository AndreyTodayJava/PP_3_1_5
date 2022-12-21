package ru.kata.spring.boot_security.demo.services;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
     User findByEmail(String email);
     User findById(int id);
     List<User> findAll();
     void save(User newUser, Set<String> roles);
     void update(int id, User updatedUser, Set<String> listOfRoles);
     void delete(int id);
}
