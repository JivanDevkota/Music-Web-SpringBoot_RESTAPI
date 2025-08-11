package com.nsu.mymusic.dtos;

import lombok.Data;

@Data
public class CreatePlaylistRequest {
    private String title;
    private String description;
    private boolean isPublic;
}

