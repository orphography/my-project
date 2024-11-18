package ru.roh.springdemo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.services.ProjectService;
import ru.roh.springdemo.utils.ErrorResponse;
import ru.roh.springdemo.utils.NotCreatedException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Создание нового проекта
    @PostMapping
    public ResponseEntity<HttpStatus> createProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            StringBuilder errMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";\n");
            }
            throw new NotCreatedException(errMsg.toString());
        }
        projectService.createProject(project);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
//    @PutMapping("/{id}")
//    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
//        return projectService.updateProject(id, project);
//    }

    // Удаление проекта
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
