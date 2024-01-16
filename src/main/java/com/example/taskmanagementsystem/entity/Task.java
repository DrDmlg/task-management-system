package com.example.taskmanagementsystem.entity;

import com.example.taskmanagementsystem.enums.Priority;
import com.example.taskmanagementsystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = true)
    private Employee author;

    @OneToOne()
    @JoinColumn(name = "executor_id", nullable = true)
    private Employee executor;

    @ElementCollection
    @CollectionTable(name = "comment")
    private List<String> comment;
}
