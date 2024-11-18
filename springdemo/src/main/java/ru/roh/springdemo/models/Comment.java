package ru.roh.springdemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task; // Связь с задачей

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Связь с пользователем, который оставил комментарий
    @NotEmpty(message = "Comment should not be empty")
    private String text;
    private LocalDateTime createdAt = LocalDateTime.now(); // Время создания комментария

    public Comment(Task task, User user, String text) {
        this.task = task;
        this.user = user;
        this.text = text;
    }
}
