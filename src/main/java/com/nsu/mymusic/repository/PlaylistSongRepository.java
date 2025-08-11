package com.nsu.mymusic.repository;

import com.nsu.mymusic.entity.Playlist;
import com.nsu.mymusic.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    Optional<Playlist>findByPlaylistIdAndSongId(Long playlistId, Long songId);
    boolean existsByPlaylistIdAndSongId(Long playlistId, Long songId);

    List<PlaylistSong>findByPlaylistIdAndPlaylist_Creator_Id(Long playlistId,Long creatorId);
}
