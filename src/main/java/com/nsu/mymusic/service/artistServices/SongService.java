package com.nsu.mymusic.service.artistServices;

import com.nsu.mymusic.dtos.SongDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SongService {
    SongDto createSong(SongDto songDto, MultipartFile songFile,MultipartFile imageFile)throws IOException;
    SongDto updateSong(Long id,SongDto songDto);
    void deleteSong(Long id);
    SongDto getSongById(Long id);
    List<SongDto>getAllSong();
    public List<SongDto>getAllByArtistId(Long artistId);
}
