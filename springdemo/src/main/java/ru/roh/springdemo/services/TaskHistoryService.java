package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.TaskHistory;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.TaskHistoryRepository;
import ru.roh.springdemo.repositories.TaskRepository;
import ru.roh.springdemo.repositories.UserRepository;

import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Добавить запись в историю изменений задачи
    public TaskHistory addTaskHistory(Long taskId, Long userId, String changeDetails) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TaskHistory history = new TaskHistory(task, user, changeDetails);
        return taskHistoryRepository.save(history);
    }

    // Получить историю изменений задачи
    public List<TaskHistory> getHistoryByTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskHistoryRepository.findByTask(task);
    }

    // Удаление записи из истории
    public void deleteTaskHistory(Long id) {
        taskHistoryRepository.deleteById(id);
    }
}
