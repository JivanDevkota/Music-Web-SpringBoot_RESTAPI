package com.nsu.mymusic.entity;

import com.nsu.mymusic.dtos.PlaylistDto;
import com.nsu.mymusic.dtos.PlaylistResponseDto;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;

    private String description;

    private String playlistImgPath;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users creator;

    @Column(name = "is_public")
    private boolean isPublic=false;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "playlist",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PlaylistSong> songs=new ArrayList<>();

    @PrePersist
    public void onCreate(){
        createdAt=new Timestamp(System.currentTimeMillis());
    }


    public PlaylistResponseDto responseDto() {
        PlaylistResponseDto dto = new PlaylistResponseDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setDescription(this.description);
        dto.setImg(this.playlistImgPath);
        return dto;
    }

    public PlaylistDto  toDto(){
        PlaylistDto dto = new PlaylistDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setDescription(this.description);
        dto.setPublic(this.isPublic);
        dto.setPlaylistImgPath(this.playlistImgPath);
        return dto;
    }


}
