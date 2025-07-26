package com.nsu.mymusic.controller;

import com.nsu.mymusic.entity.Genre;
import com.nsu.mymusic.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/get/all/genre")
    public ResponseEntity<?>getAllGenre(){
        List<Genre> genres = genreRepository.findAll();

        return ResponseEntity.ok(genres);
    }

}
