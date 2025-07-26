package com.nsu.mymusic.service.userServices;

import com.nsu.mymusic.entity.Song;

import java.util.List;

public interface LikedSongService {
    public void likeSong(Long userId, Long songId);
    public void unlikeSong(Long userId, Long songId);
    public List<Song> getLikedSongs(Long userId);
}
