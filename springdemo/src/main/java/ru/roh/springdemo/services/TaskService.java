package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.models.Task;
import ru.roh.springdemo.models.User;
import ru.roh.springdemo.repositories.ProjectRepository;
import ru.roh.springdemo.repositories.TaskRepository;
import ru.roh.springdemo.repositories.UserRepository;
import ru.roh.springdemo.utils.NotFoundException;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    // Создание новой задачи
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Получить задачу по ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with this ID not found"));
    }

    // Получить задачи по статусу
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    // Получить задачи по пользователю
    public List<Task> getTasksByAssignee(User assignee) {
        return taskRepository.findByAssignee(assignee);
    }

    // Получить задачи по проекту
    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    // Обновление статуса задачи
    public Task updateTaskStatus(Long taskId, Task.Status newStatus) {
        Task task = getTaskById(taskId);
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    // Обновление задачи
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        task.setStartDate(updatedTask.getStartDate());
        task.setDueDate(updatedTask.getDueDate());
        task.setAssignee(updatedTask.getAssignee());
        task.setProject(updatedTask.getProject());
        return taskRepository.save(task);
    }

    // Удаление задачи
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
