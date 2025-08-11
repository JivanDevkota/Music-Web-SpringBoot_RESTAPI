package com.nsu.mymusic.dtos;

import lombok.Data;

@Data
public class PlaylistResponseDto {
    private Long id;
    private String title;
    private String description;
    private String img;
}
