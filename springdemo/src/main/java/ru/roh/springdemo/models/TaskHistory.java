package ru.roh.springdemo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    // Конструкторы, геттеры и сеттеры
    public TaskHistory() {}

    public TaskHistory(Task task, String previousValue, String newValue, User changedBy) {
        this.task = task;
        this.previousValue = previousValue;
        this.newValue = newValue;
        this.changedBy = changedBy;
    }

    public TaskHistory(Task task, User user, String changeDetails) {

    }


    // геттеры и сеттеры

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskHistory that = (TaskHistory) o;
        return Objects.equals(task, that.task) && Objects.equals(changeDate, that.changeDate) && Objects.equals(previousValue, that.previousValue) && Objects.equals(newValue, that.newValue) && Objects.equals(changedBy, that.changedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, changeDate, previousValue, newValue, changedBy);
    }

    @Override
    public String toString() {
        return "TaskHistory{" +
                "id=" + id +
                ", task=" + task +
                ", changeDate=" + changeDate +
                ", previousValue='" + previousValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", changedBy=" + changedBy +
                '}';
    }
}

