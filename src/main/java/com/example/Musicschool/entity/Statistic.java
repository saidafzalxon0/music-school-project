package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statistic")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title1",nullable = false,length = 100000)
    private String title1;
    @Column(name = "number1",nullable = false,length = 100000)
    private String number1;
    @Column(name = "title2",nullable = false,length = 100000)
    private String title2;
    @Column(name = "number2",nullable = false,length = 100000)
    private String number2;
    @Column(name = "title3",nullable = false,length = 100000)
    private String title3;
    @Column(name = "number3",nullable = false,length = 100000)
    private String number3;
}
