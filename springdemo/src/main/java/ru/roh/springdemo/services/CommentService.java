package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.Comment;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.CommentRepository;
import ru.roh.springdemo.repositories.TaskRepository;
import ru.roh.springdemo.repositories.UserRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Создание нового комментария
    public Comment createComment(Long taskId, Long userId, String text) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment = new Comment(task, user, text);
        return commentRepository.save(comment);
    }

    // Получить комментарии по задаче
    public List<Comment> getCommentsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepository.findByTask(task);
    }

    // Получить комментарии по пользователю
    public List<Comment> getCommentsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return commentRepository.findByUser(user);
    }

    // Удаление комментария
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

