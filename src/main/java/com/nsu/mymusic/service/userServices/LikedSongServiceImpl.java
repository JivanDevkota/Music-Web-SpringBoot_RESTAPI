package com.nsu.mymusic.service.userServices;

import com.nsu.mymusic.entity.LikedSong;
import com.nsu.mymusic.entity.Song;
import com.nsu.mymusic.entity.Users;
import com.nsu.mymusic.repository.LikedSongRepository;
import com.nsu.mymusic.repository.SongRepository;
import com.nsu.mymusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedSongServiceImpl implements LikedSongService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private LikedSongRepository likedSongRepository;

    public void likeSong(Long userId, Long songId){
       Users user= userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException(""));
        Song song=songRepository.findById(songId)
                .orElseThrow(()->new RuntimeException(""));

        boolean alreadyLiked=likedSongRepository.findByUserAndSong(user,song).isPresent();
        if(!alreadyLiked){
            LikedSong likedSong=new LikedSong();
            likedSong.setUser(user);
            likedSong.setSong(song);
            likedSongRepository.save(likedSong);
        }
    }

    public void unlikeSong(Long userId, Long songId){
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        likedSongRepository.deleteByUserAndSong(user,song);
    }

    public List<Song> getLikedSongs(Long userId){
       Users user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
       return likedSongRepository.findByUser(user)
                .stream()
                .map(LikedSong::getSong)
               .collect(Collectors.toList());
    }

}
