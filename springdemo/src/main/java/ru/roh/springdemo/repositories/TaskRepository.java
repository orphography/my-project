package ru.roh.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Найти задачи по статусу
    List<Task> findByStatus(String status);

    // Найти задачи, назначенные пользователю
    List<Task> findByAssignee(User assignee);

    // Найти задачи по проекту
    List<Task> findByProject(Project project);

    // Найти задачи по названию, используя ключевые слова
    List<Task> findByTitleContainingIgnoreCase(String title);

    // Найти задачи по приоритету
    List<Task> findByPriority(String priority);

    // Дополнительные кастомные запросы можно добавлять сюда
}
