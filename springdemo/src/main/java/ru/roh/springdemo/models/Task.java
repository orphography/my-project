package ru.roh.springdemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority; // LOW, MEDIUM, HIGH
    @Enumerated(EnumType.STRING)
    private Status status;   // NEW, IN_PROGRESS, COMPLETED, ON_HOLD

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

    public enum Priority{
        LOW, MEDIUM, HIGH
    }
    public enum Status{
        NEW, IN_PROGRESS, COMPLETED, ON_HOLD
    }
}
