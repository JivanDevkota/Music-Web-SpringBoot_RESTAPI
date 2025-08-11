package com.nsu.mymusic.dtos;

import lombok.Data;

@Data
public class PlaylistSongDto {
    private Long id;
    private Long songId;
    private String songTitle;
    private String imgPath;
    private String filePath;
    private String artistName;
    private int duration;
    private String addedAt;
    private int position;
    private String playlistName;
    private String playlistImg;
}
