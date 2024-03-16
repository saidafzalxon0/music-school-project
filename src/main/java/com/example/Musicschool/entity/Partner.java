package com.example.Musicschool.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true,nullable = false)
    private String name;
    @Column(name = "photo_link", unique = true,nullable = false)
    private String photoLink;
    @Column(name = "link_website",unique = true,nullable = false)
    private String linkWebsite;
}
