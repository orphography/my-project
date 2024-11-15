package ru.roh.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roh.springdemo.models.Project;
import ru.roh.springdemo.repositories.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Создание нового проекта
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Получить проект по ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Получить проекты по названию
    public List<Project> getProjectsByName(String name) {
        return projectRepository.findByNameContainingIgnoreCase(name);
    }

    // Получить проекты, начинающиеся после определенной даты
    public List<Project> getProjectsByStartDate(LocalDateTime startDate) {
        return projectRepository.findByStartDateAfter(startDate);
    }

    // Получить все проекты
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Обновить проект
    public Project updateProject(Long id, Project updatedProject) {
        Project project = getProjectById(id);
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());
        return projectRepository.save(project);
    }

    // Удаление проекта
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
