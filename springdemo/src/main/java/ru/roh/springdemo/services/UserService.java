package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // Создание нового пользователя
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Получить пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Проверка на существование пользователя по email
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Получить пользователя по ID
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Обновление информации о пользователе
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }

    // Удаление пользователя
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

