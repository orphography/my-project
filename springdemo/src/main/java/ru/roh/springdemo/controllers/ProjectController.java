package ru.roh.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.services.ProjectService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Создание нового проекта
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    // Получение проекта по ID
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // Получение всех проектов
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Поиск проектов по названию
    @GetMapping("/search")
    public List<Project> searchProjects(@RequestParam String name) {
        return projectService.getProjectsByName(name);
    }

    // Поиск проектов, начинающихся после указанной даты
    @GetMapping("/startDate")
    public List<Project> searchProjectsByStartDate(@RequestParam LocalDateTime startDate) {
        return projectService.getProjectsByStartDate(startDate);
    }

    // Обновление проекта
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    // Удаление проекта
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
