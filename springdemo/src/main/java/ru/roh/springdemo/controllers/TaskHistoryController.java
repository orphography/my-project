package ru.roh.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.TaskHistory;
import ru.roh.springdemo.services.TaskHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/task-history")
public class TaskHistoryController {

    @Autowired
    private TaskHistoryService taskHistoryService;

    // Добавить запись в историю изменений задачи
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskHistory addTaskHistory(@RequestParam Long taskId, @RequestParam Long userId, @RequestBody String changeDetails) {
        return taskHistoryService.addTaskHistory(taskId, userId, changeDetails);
    }

    // Получить историю изменений задачи
    @GetMapping("/task/{taskId}")
    public List<TaskHistory> getHistoryByTask(@PathVariable Long taskId) {
        return taskHistoryService.getHistoryByTask(taskId);
    }

    // Удаление записи из истории
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskHistory(@PathVariable Long id) {
        taskHistoryService.deleteTaskHistory(id);
    }
}
