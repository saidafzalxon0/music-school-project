package com.example.Musicschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "general")
public class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone",length = 100000)
    private String phone;
    @Column(name = "fax",length = 100000)
    private String fax;
    @Column(name = "email",length = 100000)
    private String email;
    @Column(name = "location",length = 100000)
    private String location;
    @Column(name = "target",length = 100000)
    private String target;
    @Column(name = "directions",length = 100000)
    private String directions;
    @Column(name = "additional",length = 100000)
    private String additional;
}
