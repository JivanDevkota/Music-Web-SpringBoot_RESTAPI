package com.nsu.mymusic.repository;

import com.nsu.mymusic.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song>findByArtistStageName(String artistStageName);
    List<Song>findByGenre(String genre);
    List<Song>findByArtistId(Long artistId);
}
