package com.nsu.mymusic.dtos;

import com.nsu.mymusic.entity.Artist;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class AlbumDto {
    private Long albumId;
    private String title;

    private Long artistId;
    private String artistName;

    private LocalDate releaseDate;

    private String coverImg;

    private Long genreId;
    private String genreName;
}
