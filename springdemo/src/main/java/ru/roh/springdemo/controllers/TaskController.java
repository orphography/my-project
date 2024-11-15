package ru.roh.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.ProjectRepository;
import ru.roh.springdemo.repositories.UserRepository;
import ru.roh.springdemo.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Создание новой задачи
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Получение задачи по ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Получение задач по статусу
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.getTasksByStatus(status);
    }

    // Получение задач по пользователю
    @GetMapping("/assignee/{userId}")
    public List<Task> getTasksByAssignee(@PathVariable Long userId) {
        User assignee = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskService.getTasksByAssignee(assignee);
    }

    // Получение задач по проекту
    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return taskService.getTasksByProject(project);
    }

    // Обновление статуса задачи
    @PutMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestBody String newStatus) {
        return taskService.updateTaskStatus(taskId, newStatus);
    }

    // Обновление задачи
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    // Удаление задачи
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
