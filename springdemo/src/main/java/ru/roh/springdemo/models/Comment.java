package ru.roh.springdemo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    private String text;
    private LocalDateTime createdAt = LocalDateTime.now(); // Время создания комментария

    // Конструкторы, геттеры и сеттеры
    public Comment() {}

    public Comment(Task task, User user, String text) {
        this.task = task;
        this.user = user;
        this.text = text;
    }

    // геттеры и сеттеры

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(task, comment.task) && Objects.equals(user, comment.user) && Objects.equals(text, comment.text) && Objects.equals(createdAt, comment.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, user, text, createdAt);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", task=" + task +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
