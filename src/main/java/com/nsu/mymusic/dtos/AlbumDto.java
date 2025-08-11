package com.nsu.mymusic.dtos;


import lombok.Data;

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
