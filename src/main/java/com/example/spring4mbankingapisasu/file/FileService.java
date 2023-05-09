package com.example.spring4mbankingapisasu.file;

import com.example.spring4mbankingapisasu.file.web.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     *  use to upload a single file
     * @param file request form data from client
     * @return fileDto
     */
    FileDto uploadSingle(MultipartFile file);
    List<FileDto> uploadMultiple(List<MultipartFile> file);

    Boolean removeAllFile();

    FileDto deleteSingleFile(String fileName);
    List<FileDto>AllFiles();



}
