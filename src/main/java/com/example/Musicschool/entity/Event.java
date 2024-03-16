package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false,length = 10000)
    private String title;
    @Column(name = "about",nullable = false,length = 10000)
    private String about;
    @Column(name = "date",nullable = false,length = 10000)
    private Date date;
    @Column(name = "start_time",length = 5)
    private String start_time;
    @Column(name = "end_time",length = 5)
    private String end_time;
    @Column(name = "location",length = 10000)
    private String location;
    @Column(name = "photo_link",nullable = false)
    private String photo_link;
}
