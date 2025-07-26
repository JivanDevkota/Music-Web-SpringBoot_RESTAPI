package com.nsu.mymusic.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "liked_songs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "song_id"})
})
@Data
public class LikedSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    private Timestamp likedTime;

    public void onLike() {
        this.likedTime = new Timestamp(System.currentTimeMillis());
    }
}
