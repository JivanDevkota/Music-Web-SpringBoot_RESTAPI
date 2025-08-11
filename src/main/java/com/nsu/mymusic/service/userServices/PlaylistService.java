package com.nsu.mymusic.service.userServices;

import com.nsu.mymusic.dtos.*;

import java.util.List;

public interface PlaylistService {
    public PlaylistDto createPlaylist(CreatePlaylistRequest request, Long userId);
    public List<PlaylistResponseDto> getAllPlaylist(Long userId);
    public PlaylistDto getPlaylistById(Long id);
    public PlaylistDto addSongToPlaylist(AddSongToPlaylistRequest request);

    public PlaylistDto getPlaylistWithSongs(Long playlistId);
//    public List<PlaylistSongDto>getAllPlaylistSongs(Long  playlistId,Long userId);

}
