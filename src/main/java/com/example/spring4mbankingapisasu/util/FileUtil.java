package com.example.spring4mbankingapisasu.util;

import com.example.spring4mbankingapisasu.file.web.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.server-path}")
    public String fileServerPath;
    @Value("${file.base-url}")
    public String fileBaseUrl;


    public FileDto uploadFile(MultipartFile multipartFile){
        int lastDotIndex = multipartFile.getOriginalFilename().lastIndexOf(".");

        String extension = multipartFile.getOriginalFilename().substring(lastDotIndex+1);


        long size = multipartFile.getSize();


        String fileName = String.format("%s.%s", UUID.randomUUID(),extension);


        String url = String.format("%s%s",fileBaseUrl,fileName);


        Path path = Paths.get(fileServerPath + fileName);
        try {
            Files.copy(multipartFile.getInputStream(), path);
            return FileDto.builder()
                    .name(fileName)
                    .url(url)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Uploading failed...!");
        }
    }
    Path foundFile;
    public Path FileAsResource(String fileName) throws IOException {
        Path Path = Paths.get(fileServerPath);


        Files.list(Path).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileName)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {
            Path path = Paths.get(fileServerPath);
            return path;
        }

        return null;
    }


}