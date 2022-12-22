package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUserList(){
        List<User> list = userService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public User getUser(Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
         return user;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<User> addUser(@ModelAttribute("user") User user, @RequestParam("role") Set<String> listOfRoles){
        userService.save(user, listOfRoles);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        if (userService.findById(id) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK); }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id,
                                           @RequestParam(value = "role", defaultValue = "ROLE_USER") Set<String> listOfRoles) {
        if (userService.findById(id) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.update(id, user, listOfRoles);
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
