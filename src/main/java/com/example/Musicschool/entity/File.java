package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "link",unique = true,nullable = false)
    private String link;
    @Column(name = "type",nullable = false)
    private Integer type;
}
