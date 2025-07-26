package com.nsu.mymusic.controller;

import com.nsu.mymusic.entity.Song;
import com.nsu.mymusic.repository.LikedSongRepository;
import com.nsu.mymusic.service.userServices.LikedSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikedSongController {

    @Autowired
    private LikedSongService likedSongService;

    @PostMapping("/song/liked")
    public String likeSong(@RequestParam Long userId,@RequestParam Long songId){
        likedSongService.likeSong(userId, songId);
        return "Song liked";
    }

    @DeleteMapping("/song/unlike")
    public String unlikeSong(Long userId, Long songId){
        likedSongService.unlikeSong(userId, songId);
        return "Song unliked";
    }

    @GetMapping
    public List<Song>getLikedSongs(Long userId){
        return likedSongService.getLikedSongs(userId);
    }
}
