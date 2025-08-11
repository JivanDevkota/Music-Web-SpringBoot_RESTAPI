    package com.nsu.mymusic.controller;
    
    import com.nsu.mymusic.dtos.*;
    import com.nsu.mymusic.repository.PlaylistRepository;
    import com.nsu.mymusic.service.userServices.PlaylistService;
    import org.apache.coyote.BadRequestException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;
    
    import java.util.List;
    
    @RestController
    @RequestMapping("/api")
    public class PlaylistController {
    
        @Autowired
        private PlaylistService playlistService;

        @PostMapping("/user/{userId}/create/playlist")
        public ResponseEntity<PlaylistDto> createPlaylist(
                @RequestBody CreatePlaylistRequest request,
                @PathVariable Long userId
        ){
            PlaylistDto playlist = playlistService.createPlaylist(request, userId);
            return ResponseEntity.ok(playlist);
        }
    
    
        @GetMapping("/user/{userId}/playlist")
        public ResponseEntity<?>getAllPlaylists(
                @PathVariable Long userId
        ){
            List<PlaylistResponseDto> allPlaylist =
                    playlistService.getAllPlaylist(userId);
            return ResponseEntity.ok(allPlaylist);
        }
    
    
        @PostMapping("/user/add/songs/to/playlist/{playlistId}")
        public ResponseEntity<PlaylistDto> addSongToPlaylist(
                @PathVariable Long playlistId,
                @RequestBody AddSongToPlaylistRequest request) {
    
            if (!playlistId.equals(request.getPlaylistId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Playlist ID in URL does not match request body");
            }
    
            PlaylistDto playlist = playlistService.addSongToPlaylist(request);
            return ResponseEntity.ok(playlist);
        }
    

//        @GetMapping("/user/{userId}/playlist/{playlistId}/songs")
//        public ResponseEntity<List<PlaylistSongDto>>getAllSongByPlaylistId(@PathVariable Long playlistId,
//                                                                     @PathVariable Long userId) {
//            List<PlaylistSongDto> allPlaylistSongs = playlistService.getAllPlaylistSongs(playlistId, userId);
//            return new ResponseEntity(allPlaylistSongs,HttpStatus.OK);
//        }

        @GetMapping("/user/playlists/{id}")
        public PlaylistDto getPlaylistDetails(@PathVariable Long id) {
            return playlistService.getPlaylistWithSongs(id);
        }

    }
