package com.nsu.mymusic.entity;

import com.nsu.mymusic.dtos.PlaylistSongDto;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "playlist_songs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"playlist_id", "song_id"})
})
@Data
public  class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(name = "added_at", updatable = false)
    private Timestamp addedAt;

    private int position; // For ordering songs in playlist

    @PrePersist
    protected void onCreate() {
        addedAt = new Timestamp(System.currentTimeMillis());
    }

    public PlaylistSongDto toDto() {
        PlaylistSongDto dto = new PlaylistSongDto();
        dto.setId(this.id);
        dto.setSongId(this.song.getId());
        dto.setSongTitle(this.song.getTitle());
        dto.setImgPath(this.song.getSongImgPath());
        dto.setFilePath(this.song.getFilePath());
        dto.setAddedAt(addedAt.toString());
        dto.setDuration(this.song.getDuration());
        dto.setPlaylistImg(playlist.getTitle());
        dto.setPlaylistImg(playlist.getPlaylistImgPath());
        dto.setPosition(position);
        dto.setArtistName(playlist.getCreator().getUsername());
        return dto;
    }
}
