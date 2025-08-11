package com.nsu.mymusic.service.artistServices;

import com.nsu.mymusic.dtos.SongDto;
import com.nsu.mymusic.entity.Album;
import com.nsu.mymusic.entity.Artist;
import com.nsu.mymusic.entity.Genre;
import com.nsu.mymusic.entity.Song;
import com.nsu.mymusic.repository.AlbumRepository;
import com.nsu.mymusic.repository.ArtistRepository;
import com.nsu.mymusic.repository.GenreRepository;
import com.nsu.mymusic.repository.SongRepository;
import com.nsu.mymusic.utils.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
            @CacheEvict(value = "songs",allEntries = true)
    public SongDto createSong(SongDto songDto, MultipartFile songFile,MultipartFile imageFile)throws IOException {

        Long defaultArtistId=1L;
        Artist artist = artistRepository.findById(defaultArtistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        Album album = albumRepository.findById(songDto.getAlbumId())
                .orElseThrow(() -> new RuntimeException("Album not found"));

        Genre genre = genreRepository.findById(songDto.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        String songPath = fileStorageUtil.saveFile(songFile, "song");
        String imgPath=fileStorageUtil.saveFile(imageFile,"image");
        Song song = new Song();
        song.setTitle(songDto.getTitle());
        song.setDuration(songDto.getDuration());
        song.setGenre(genre);
        song.setArtist(artist);
        song.setAlbum(album);
        song.setFilePath(songPath);
        song.setSongImgPath(imgPath);
        song.setPlayCount(0);

        return songRepository.save(song).toSongDto();
    }

    @Override
    public SongDto updateSong(Long id, SongDto songDto) {
        return null;
    }

    @Override
    public void deleteSong(Long id) {

    }

    @Override
    public SongDto getSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found"));
        return song.toSongDto();
    }

    @Override
    public List<SongDto> getAllSong() {
        List<Song> songs = songRepository.findAll();
        return songs
                .stream()
                .map(Song::toSongDto)
                .collect(Collectors.toList());
    }

    public List<SongDto>getAllByArtistId(Long artistId) {
        List<Song> songList = songRepository.findByArtistId(artistId);
        return songList
                .stream()
                .map(Song::toSongDto)
                .collect(Collectors.toList());
    }

}
