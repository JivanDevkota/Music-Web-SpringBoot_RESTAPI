package com.nsu.mymusic.entity;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true)
    private Users user;

    @Column(unique = true, name = "stage_name")
    private String stageName;

    @Column(name = "profile_image")
    private String profileImage;

    @Lob
    private String lob;

}
