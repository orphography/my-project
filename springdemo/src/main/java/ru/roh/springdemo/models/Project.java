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
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "ProjectName should not be empty")
    private String name;
    @NotEmpty(message = "DescriptionProject should not be empty")
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();  // Связь с задачами

}
