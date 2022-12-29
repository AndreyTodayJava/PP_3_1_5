package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("select user from User user join fetch user.roles where user.email = :email")
 User findByEmail(String email);

}