package com.nsu.mymusic.entity;

import com.nsu.mymusic.dtos.AlbumDto;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "cover_image")
    private String coverImg;

    public AlbumDto getAlbumDto() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumId(this.id);
        albumDto.setTitle(this.title);
        albumDto.setReleaseDate(this.releaseDate);
        albumDto.setCoverImg(this.coverImg);
        return albumDto;
    }

}
