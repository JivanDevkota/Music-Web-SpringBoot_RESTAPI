package com.nsu.mymusic.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@Component
public class FileStorageUtil {

//    private final Path songDir = Paths.get("uploads/songs");
//    private final Path imageDir = Paths.get("uploads/images");
//
//    public FileStorageUtil() throws IOException {
//        Files.createDirectories(songDir);
//        Files.createDirectories(imageDir);
//    }
//
//    public String saveFile(MultipartFile file, String type) throws IOException {
//        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//        Path target = type.equals("song") ?
//                songDir.resolve(fileName) : imageDir.resolve(fileName);
//        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
//        return target.toString();
//    }

    // Update these to point to the "static/uploads/..." folder
    private final Path rootDir = Paths.get("src/main/resources/static/uploads");
    private final Path songDir = rootDir.resolve("songs");
    private final Path imageDir = rootDir.resolve("images");

    public FileStorageUtil() throws IOException {
        Files.createDirectories(songDir);
        Files.createDirectories(imageDir);
    }

    public String saveFile(MultipartFile file, String type) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path target;

        if ("song".equalsIgnoreCase(type)) {
            target = songDir.resolve(fileName);
        } else if ("image".equalsIgnoreCase(type)) {
            target = imageDir.resolve(fileName);
        } else {
            throw new IllegalArgumentException("Invalid file type: " + type);
        }

        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        // Return web-accessible path: /uploads/songs/xxx or /uploads/images/xxx
        return "/uploads/" + type.toLowerCase() + "s/" + fileName;
    }

    public int getAudioDurationInSeconds(File audioFile) {
        try {
            AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(audioFile);
            Map<?, ?> properties = baseFileFormat.properties();
            Long microseconds = (Long) properties.get("duration");
            return (int) (microseconds / 1_000_000);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read duration", e);
        }
    }
}
