package ru.roh.springdemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.services.ProjectService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Creates project.
     *
     * @param project project dto to create
     * @return ResponseEntity with created project
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(project));
    }

    /**
     * Get project by id.
     *
     * @param id project id
     * @return project
     */
    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    /**
     * Get all projects.
     *
     * @return list of all projects
     */
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * Finds projects by name.
     *
     * @return list of projects
     */
    @GetMapping("/findProjectsByName")
    public List<Project> searchProjects(@RequestParam String name) {
        return projectService.getProjectsByName(name);
    }

    // Поиск проектов, начинающихся после указанной даты
    @GetMapping("/findProjectsByDate")
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
