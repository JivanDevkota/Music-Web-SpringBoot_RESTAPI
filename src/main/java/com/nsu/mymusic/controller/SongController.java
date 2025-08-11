package com.nsu.mymusic.controller;

import com.nsu.mymusic.dtos.SongDto;
import com.nsu.mymusic.service.artistServices.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/artist/upload/song")
    public ResponseEntity<SongDto> uploadSong(
            @RequestParam("title") String title,
            @RequestParam(value = "artistId") Long artistId,
            @RequestParam(value = "albumId", defaultValue = "2") Long albumId,
            @RequestParam("genreId") Long genreId,
            @RequestParam(value = "duration",defaultValue = "120") int duration,
            @RequestParam("audio") MultipartFile audio,
            @RequestParam("image") MultipartFile image
    ) throws IOException {
        System.out.println("Hey Hey");
        SongDto songDto = new SongDto();
        songDto.setTitle(title);
        songDto.setArtistId(artistId);
        songDto.setAlbumId(albumId);
        songDto.setGenreId(genreId);
        songDto.setDuration(duration);

        return ResponseEntity.ok(songService.createSong(songDto, audio, image));
    }

    @PutMapping("/artist/update/song/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable Long id, @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songService.updateSong(id, songDto));
    }

    @DeleteMapping("/artist/delete/song/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok("Song deleted successfully");
    }

    @GetMapping("/artist/song/{id}")
    public ResponseEntity<SongDto> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/artist/songs")
    public ResponseEntity<List<SongDto>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSong());
    }

    @GetMapping("/all/songs")
    public ResponseEntity<List<SongDto>> getAllSong() {
        return ResponseEntity.ok(songService.getAllSong());
    }

    @GetMapping("/artist/{artistId}/songs")
    public ResponseEntity<List<SongDto>> getAllSongByArtistId(@PathVariable Long artistId) {
        return ResponseEntity.ok(songService.getAllByArtistId(artistId));
    }

}
