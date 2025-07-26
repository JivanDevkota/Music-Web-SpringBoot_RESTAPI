package com.nsu.mymusic.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class SongDto {
    private Long songId;
    private String title;
    private Long albumId;
    private String albumName;

    private Long artistId;
    private String artistName;
    private Long genreId;
    private String genreName;
    private int duration;
    private int playCount;
    private String songImgPath;
    private String filePath;
}
