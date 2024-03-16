package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false,length = 100000)
    private String title;
    @Column(name = "about",nullable = false,length = 100000)
    private String about;
    @Column(name = "shortly",nullable = false,length = 100000)
    private String shortly;
    @Column(name = "who_from",length = 100000)
    private String who_from;
    @Column(name = "date_time",nullable = false)
    private Date date;
    @Column(name = "file_link")
    private String file;
}
