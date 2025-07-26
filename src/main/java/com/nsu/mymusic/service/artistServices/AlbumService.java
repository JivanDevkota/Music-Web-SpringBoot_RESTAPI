package com.nsu.mymusic.service.artistServices;

import com.nsu.mymusic.dtos.AlbumDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {
    public AlbumDto createAlbum(AlbumDto albumDto, MultipartFile file)throws IOException;
}
