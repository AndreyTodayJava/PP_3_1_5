package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User newUser, Set<String> roles) {
        newUser.addRoles(roles);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    //    @Transactional
//    public void update(int id, User user, Set<String> listOfRoles) {
//        User updatedUser = userRepository.getById(id);
//        updatedUser.setUsername(user.getUsername());
//        updatedUser.setEmail(user.getEmail());
//        updatedUser.setAge(user.getAge());
//        updatedUser.setRoles(listOfRoles);
//        updatedUser.setLastName(user.getLastName());
//        if (!StringUtils.isEmpty(user.getPassword())) {
//            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//        userRepository.save(updatedUser);
//    }

    @Transactional // упрощенный update
    public void update(int id, User user, Set<String> listOfRoles) {
        User updatedUser = userRepository.getById(id);
        user.setId(id);
        user.addRoles(listOfRoles);
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(updatedUser.getPassword());
        }
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
