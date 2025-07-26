package com.nsu.mymusic.repository;

import com.nsu.mymusic.entity.LikedSong;
import com.nsu.mymusic.entity.Song;
import com.nsu.mymusic.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedSongRepository extends JpaRepository<LikedSong,Long> {

    List<LikedSong> findByUser(Users user);

    Optional<LikedSong> findByUserAndSong(Users user, Song song);

    void  deleteByUserAndSong(Users user, Song song);
}
