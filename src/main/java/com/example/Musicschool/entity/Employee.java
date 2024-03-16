package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name",nullable = false)
    private String fullName;
    @Column(name = "about",nullable = false,length = 10000)
    private String about;
    @Column(name = "editor",length = 10000)
    private String editor;
    @ManyToOne
    @JoinColumn(name = "position_id",nullable = false)
    private Position position;
    @ManyToOne
    @JoinColumn(name = "direction_id",nullable = false)
    private Direction direction;
    @Column(name = "type",nullable = false)
    private Integer type;
    @Column(name = "level")
    private Integer level;
    @Column(name = "photo_link",nullable = false)
    private String photoLink;
    @ElementCollection
    @CollectionTable(name = "employee_list_file", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "list_file")
    private List<String> listFile;
}
