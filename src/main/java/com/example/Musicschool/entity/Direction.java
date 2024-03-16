package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false,unique = true)
    private String name;
    @Column(name = "history",nullable = false,length = 100000)
    private String history;
    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;
    @Column(name = "level")
    private Integer level;
}
