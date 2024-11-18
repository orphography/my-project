package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.UserRepository;
import ru.roh.springdemo.utils.NotFoundException;
import ru.roh.springdemo.utils.NotCreatedException;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with this ID not found"));
    }
    // Создание нового пользователя
    public User createUser(User user) {
        if(!userRepository.existsByEmail(user.getEmail())) {
            return userRepository.save(user);
        }
        else throw new NotCreatedException("A user with this email already exists.");
    }

    public User updateUser(Long id, User updatedUser) {
        User newUser = getUserById(id);
        newUser.setFirstName(updatedUser.getFirstName());
        newUser.setLastName(updatedUser.getLastName());
        newUser.setEmail(updatedUser.getEmail());
        newUser.setPassword(updatedUser.getPassword());
        newUser.setRole(updatedUser.getRole());
        return userRepository.save(newUser);
    }//TODO проверить сколько выполняется sql-запросов

    // Удаление пользователя
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

