package ru.roh.springdemo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "Task_history")
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task; // Связь с задачей

    private LocalDateTime changeDate = LocalDateTime.now(); // Дата и время изменения

    private String previousValue; // Старое значение
    private String newValue;      // Новое значение

    @ManyToOne
    @JoinColumn(name = "changed_by")
    private User changedBy; // Связь с пользователем, который сделал изменение

    public TaskHistory(Task task, User user, String changeDetails) {
        this.task = task;
        this.changedBy = user;
        this.newValue = changeDetails;
    }
}

