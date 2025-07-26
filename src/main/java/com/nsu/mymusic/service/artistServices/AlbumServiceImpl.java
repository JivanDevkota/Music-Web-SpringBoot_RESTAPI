package com.nsu.mymusic.service.artistServices;

import com.nsu.mymusic.dtos.AlbumDto;
import com.nsu.mymusic.entity.Album;
import com.nsu.mymusic.entity.Artist;
import com.nsu.mymusic.repository.AlbumRepository;
import com.nsu.mymusic.repository.ArtistRepository;
import com.nsu.mymusic.utils.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Autowired
    private ArtistRepository artistRepository;

    public AlbumDto createAlbum(AlbumDto albumDto, MultipartFile file)throws IOException {

        Artist artist=artistRepository.findById(albumDto.getArtistId())
                .orElseThrow(()->new RuntimeException("Artist not found"));

        String coverImage = fileStorageUtil.saveFile(file, "image");
        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        album.setReleaseDate(LocalDate.now());
        album.setCoverImg(coverImage);
        album.setArtist(artist);
      return albumRepository.save(album).getAlbumDto();
    }


}
