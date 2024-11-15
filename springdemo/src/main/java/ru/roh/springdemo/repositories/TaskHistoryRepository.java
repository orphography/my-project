package ru.roh.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.TaskHistory;
import ru.roh.springdemo.models.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

    // Найти историю изменений задачи по задаче
    List<TaskHistory> findByTask(Task task);

    // Найти все изменения задачи, сделанные пользователем
    List<TaskHistory> findByChangedBy(User changedBy);

    // Найти изменения, сделанные после определенной даты
    List<TaskHistory> findByChangeDateAfter(LocalDateTime changeDate);

    // Дополнительные кастомные запросы можно добавлять сюда
}

