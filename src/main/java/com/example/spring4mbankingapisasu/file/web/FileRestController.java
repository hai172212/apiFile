package com.example.spring4mbankingapisasu.file.web;

import com.example.spring4mbankingapisasu.base.BaseApi;
import com.example.spring4mbankingapisasu.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileRestController {
    private final FileService fileService;

    @PostMapping("/upload-singles")
    public BaseApi<?> uploadSingle(@RequestPart("file") MultipartFile file) {

        log.info("Request file upload = {}", file);

        FileDto fileDto = fileService.uploadSingle(file);


        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }



    @PostMapping("/upload-multiples")
    public BaseApi<?> uploadMultiple(@RequestPart("files") List<MultipartFile> files){
        log.info("File Request {}" , files);
        List<FileDto> fileDto  = fileService.uploadMultiple(files);
        return BaseApi.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();


    }



    @DeleteMapping("/{fileName}")
    public BaseApi<?> deleteFiles(@PathVariable("fileName") String fileName) {
        FileDto fileDto = fileService.deleteSingleFile(fileName);
        return BaseApi.builder()
                .status(true)
                .message("File Has been Deleted")
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .data(fileDto)
                .build();
    }
    @GetMapping
    public BaseApi<?> AllFiles() {
        List<FileDto> fileDto = fileService.AllFiles();
        if (fileDto.isEmpty()) {
            return BaseApi
                    .builder()
                    .data(null)
                    .message("Data Not have Please Enter Data to get")
                    .build();
        } else {
            return BaseApi.builder().status(true)
                    .code(HttpStatus.OK.value())
                    .message("File have been get")
                    .timestamp(LocalDateTime.now())
                    .data(fileDto)
                    .build();
        }

    }


    @DeleteMapping
    public BaseApi<?> removeAllFile() {
        boolean files = fileService.removeAllFile();
        return BaseApi.builder().status(true)
                .code(HttpStatus.OK.value())
                .message("File have been Removed ")
                .timestamp(LocalDateTime.now())
                .data(files)
                .build();
    }
}
