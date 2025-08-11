package com.nsu.mymusic.dtos;

import lombok.Data;

@Data
public class AddSongToPlaylistRequest {
    private Long playlistId;
    private Long songId;
    private Integer position;
}
