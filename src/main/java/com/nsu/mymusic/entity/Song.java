package com.nsu.mymusic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsu.mymusic.dtos.SongDto;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="songs")
@Data
@ToString
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private Genre genre;

    private int duration;  //in second

    private int playCount;

    @Column(name = "song_path")
    private String filePath;

    @Column(name = "song_img")
    private String songImgPath;

    @Column(name = "upload_date",updatable = false)
    private Timestamp uploadDate;

    @PrePersist
    public void onUpload(){
        uploadDate=new Timestamp(System.currentTimeMillis());
    }

   public SongDto toSongDto(){
        SongDto songDto = new SongDto();
        songDto.setSongId(this.id);
        songDto.setTitle(this.title);
        songDto.setArtistId(artist.getId());
        songDto.setArtistName(artist.getUser().getUsername());
        if (album != null) {
            songDto.setAlbumId(album.getId());
            songDto.setAlbumName(album.getTitle());
        }
       if (genre != null) {
           songDto.setGenreId(genre.getId());
           songDto.setGenreName(genre.getTitle());
       }
        songDto.setDuration(this.duration);
        songDto.setFilePath(this.filePath);
        songDto.setSongImgPath(this.songImgPath);
        songDto.setPlayCount(this.playCount);
        return songDto;
    }

}
