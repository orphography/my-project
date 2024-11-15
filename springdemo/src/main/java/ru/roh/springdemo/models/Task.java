package ru.roh.springdemo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String priority; // LOW, MEDIUM, HIGH
    private String status;   // NEW, IN_PROGRESS, COMPLETED, ON_HOLD
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee; // Связь с пользователем

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project; // Связь с проектом

    @OneToMany(mappedBy = "task")
    private List<Comment> comments = new ArrayList<>(); // Связь с комментариями

    @OneToMany(mappedBy = "task")
    private List<TaskHistory> taskHistories = new ArrayList<>(); // История изменений задачи

    // Конструкторы, геттеры и сеттеры
    public Task() {}

    public Task(String title, String description, String priority, String status, LocalDateTime startDate, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    // геттеры и сеттеры

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(List<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(priority, task.priority) && Objects.equals(status, task.status) && Objects.equals(startDate, task.startDate) && Objects.equals(dueDate, task.dueDate) && Objects.equals(assignee, task.assignee) && Objects.equals(project, task.project) && Objects.equals(comments, task.comments) && Objects.equals(taskHistories, task.taskHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, priority, status, startDate, dueDate, assignee, project, comments, taskHistories);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", assignee=" + assignee +
                ", project=" + project +
                ", comments=" + comments +
                ", taskHistories=" + taskHistories +
                '}';
    }
}
