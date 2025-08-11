package com.nsu.mymusic.service.userServices;

import com.nsu.mymusic.dtos.*;
import com.nsu.mymusic.entity.Playlist;
import com.nsu.mymusic.entity.PlaylistSong;
import com.nsu.mymusic.entity.Song;
import com.nsu.mymusic.entity.Users;
import com.nsu.mymusic.repository.PlaylistRepository;
import com.nsu.mymusic.repository.PlaylistSongRepository;
import com.nsu.mymusic.repository.SongRepository;
import com.nsu.mymusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PlaylistServiceImpl implements PlaylistService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PlaylistRepository playlistRepository;

   @Autowired
   private PlaylistSongRepository playlistSongRepository;

   @Autowired
   private SongRepository songRepository;

   @Transactional
    public PlaylistDto createPlaylist(CreatePlaylistRequest request,Long userId) {

        Users user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));

        Playlist playlist=new Playlist();
        playlist.setTitle(request.getTitle());
        playlist.setDescription(request.getDescription());
        playlist.setPublic(request.isPublic());
        playlist.setCreator(user);
        Playlist save = playlistRepository.save(playlist);
        return save.toDto();
    }

    public List<PlaylistResponseDto> getAllPlaylist(Long userId) {
        List<Playlist> playlist = playlistRepository.findByCreatorId(userId);
        return playlist
                .stream()
                .map(Playlist::responseDto)
                .collect(Collectors.toList());
    }


    public PlaylistDto getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        return playlist.toDto();
    }
    public PlaylistDto addSongToPlaylist(AddSongToPlaylistRequest request) {
       Playlist playlist= playlistRepository.findById(request.getPlaylistId())
               .orElseThrow(()->new RuntimeException("Playlist not found"));

       Song song=songRepository.findById(request.getSongId())
               .orElseThrow(()->new RuntimeException("Song not found"));

//       playlistSongRepository
//               .findByPlaylistIdAndSongId(request.getPlaylistId(), request.getSongId())
//               .ifPresent(playlistSong->{
//                   throw new RuntimeException("Song already Present");
//               });
        boolean songAlreadyExists = playlistSongRepository.existsByPlaylistIdAndSongId(playlist.getId(), song.getId());
        if (songAlreadyExists) {
            throw new IllegalStateException("Song already exists");
        }
        PlaylistSong playlistSong=new PlaylistSong();
       playlistSong.setPlaylist(playlist);
       playlistSong.setSong(song);
       //set position -if not provied, append to end
        int position=request.getPosition()!=null?
                request.getPosition():
                playlist.getSongs().size()+1;
        playlistSong.setPosition(position);

        PlaylistSong saved = playlistSongRepository.save(playlistSong);

        //
        playlist.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//        playlistRepository.save(playlist);
//        return getPlaylistById(playlist.getId());
        return playlist.toDto();
   }

    public PlaylistDto getPlaylistWithSongs(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        PlaylistDto dto = new PlaylistDto();
        dto.setId(playlist.getId());
        dto.setTitle(playlist.getTitle());
        dto.setPlaylistImgPath(playlist.getPlaylistImgPath());
        dto.setDescription(playlist.getDescription());
        dto.setPublic(playlist.isPublic());

        List<PlaylistSongDto> songDtos = playlist.getSongs().stream().map(song -> {
            PlaylistSongDto s = new PlaylistSongDto();
            s.setId(song.getId());
            s.setSongId(song.getSong().getId());
            s.setSongTitle(song.getSong().getTitle());
            s.setImgPath(song.getSong().getSongImgPath());
            s.setFilePath(song.getSong().getFilePath());
            s.setDuration(song.getSong().getDuration());
            s.setArtistName(song.getSong().getArtist().getUser().getUsername());
            s.setAddedAt(song.getAddedAt().toString());
            s.setPosition(song.getPosition());
            return s;
        }).toList();

        dto.setSongs(songDtos);
        return dto;
    }

//   public List<PlaylistSongDto>getAllPlaylistSongs(Long  playlistId,Long userId) {
//
//       List<PlaylistSong> byPlaylistIdAndPlaylistCreatorId = playlistSongRepository.findByPlaylistIdAndPlaylist_Creator_Id(playlistId, userId);
//       return byPlaylistIdAndPlaylistCreatorId
//               .stream()
//               .map(PlaylistSong::toDto)
//               .collect(Collectors.toList());
//   }
}
