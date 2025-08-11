package com.nsu.mymusic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistDto {
    private Long id;
    private String title;
    private String description;
    private String playlistImgPath;
    private Long creatorId;  //userId
    private String creatorName;
    private boolean isPublic;
    private String createdAt;
    private String updateAt;
    private int songCount;
    private List<PlaylistSongDto> songs;
}
