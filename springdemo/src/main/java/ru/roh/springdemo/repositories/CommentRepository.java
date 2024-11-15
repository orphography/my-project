package ru.roh.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roh.springdemo.models.Comment;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Найти комментарии по задаче
    List<Comment> findByTask(Task task);

    // Найти комментарии по пользователю
    List<Comment> findByUser(User user);

    // Найти комментарии по времени создания
    List<Comment> findByCreatedAtAfter(LocalDateTime createdAt);

    // Дополнительные кастомные запросы можно добавлять сюда
}
