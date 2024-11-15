package ru.roh.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roh.springdemo.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//  List<User> findAll();
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
