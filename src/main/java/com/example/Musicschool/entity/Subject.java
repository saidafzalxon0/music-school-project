package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject",
    uniqueConstraints = {@UniqueConstraint(name = "name_directionId_unique", columnNames = {"name", "direction_id"})})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "direction_id",nullable = false)
    private Direction direction;
}
