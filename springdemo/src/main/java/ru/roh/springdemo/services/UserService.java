package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.UserRepository;
import ru.roh.springdemo.utils.NotFoundException;
import ru.roh.springdemo.utils.NotCreatedException;

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
        if(!userRepository.existsByEmail(user.getEmail())) {
            return userRepository.save(user);
        }
        else throw new NotCreatedException("A user with this email already exists.");
    }

    // Получить пользователя по email

    // Проверка на существование пользователя по email

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NotFoundException("User with this ID not found"));
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

