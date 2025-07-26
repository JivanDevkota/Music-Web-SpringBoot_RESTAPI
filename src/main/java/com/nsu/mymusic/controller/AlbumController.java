package com.nsu.mymusic.controller;

import com.nsu.mymusic.dtos.AlbumDto;
import com.nsu.mymusic.dtos.SongDto;
import com.nsu.mymusic.service.artistServices.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/artist/create/album")
    public ResponseEntity<AlbumDto> createAlbum(
            @RequestParam("title") String title,
            @RequestParam("genreId") Long genreId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("artistId") Long artistId)
            throws IOException {
        System.out.println("AlbumController.createAlbum");
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle(title);
        albumDto.setGenreId(genreId);
        albumDto.setArtistId(artistId);

        return ResponseEntity.ok(albumService.createAlbum(albumDto, image));

    }
}
