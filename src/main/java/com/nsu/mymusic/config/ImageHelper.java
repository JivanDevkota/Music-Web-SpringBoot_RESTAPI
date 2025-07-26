package com.nsu.mymusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageHelper {
//    @Value("${imgPath}")
//    private String imgPath;
//
//    public  String saveImg(MultipartFile file)throws IOException {
//        if (file==null ||file.isEmpty()){
//            throw  new RuntimeException("");
//        }
//        Path path= Paths.get(imgPath);
//        if (!Files.exists(path)){
//            Files.createDirectories(path);
//        }
//        String originalFilename = file.getOriginalFilename();
//        String fileExtension=getImageExtension(originalFilename);
//        String random= UUID.randomUUID().toString();
//        String uniqueFileName=random+fileExtension;
//        System.out.println(uniqueFileName);
//
//        Path targetPath=path.resolve(uniqueFileName);
//        file.transferTo(targetPath.toFile());
//        return "img/"+uniqueFileName;
//
//    }
//
//    public static String getImageExtension(String fileName) {
//        if (fileName==null || fileName.isEmpty()) {
//            return "";
//        }
//        return fileName.substring(fileName.lastIndexOf("."));
//    }
}
