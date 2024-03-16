package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "string1",nullable = false,length = 100000)
    private String string1;
    @Column(name = "string2",nullable = false,length = 100000)
    private String string2;
    @Column(name = "string3",nullable = false,length = 100000)
    private String string3;
    @Column(name = "string4",nullable = false,length = 100000)
    private String string4;
}
