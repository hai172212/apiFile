package com.example.spring4mbankingapisasu.file;

import com.example.spring4mbankingapisasu.file.web.FileDto;
import com.example.spring4mbankingapisasu.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${file.server-path}")
    private String fileServerPath;

    @Value("${file.base-url}")
    private String fileBaseUrl;

    public FileUtil fileUtil;
@Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
      return fileUtil.uploadFile(file);
    }

    /**
     *
     * @param
     * @return
     */

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> fileDto= new ArrayList<>();
        for (MultipartFile file: files) {
        fileDto.add(fileUtil.uploadFile(file));
    }
        return fileDto;
    }

    @Override
    public Boolean removeAllFile() {
        if (this.AllFiles().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File is Empty !");
        }

        for (FileDto file : this.AllFiles()) {
            this.deleteSingleFile(file.name());
        }

        return true;
    }

    @Override
    public FileDto deleteSingleFile(String fileName) {
        FileDto fileDto = this.AllFiles().stream().filter(
                file -> file.name().equalsIgnoreCase(fileName)).findFirst().orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Files Not Found"));

        File file = new File(fileUtil.fileServerPath, fileName);
        file.delete();
        return fileDto;
    }

    @Override
    public List<FileDto>
    AllFiles() {
        List<FileDto> listFile = new ArrayList<>();
        File file = new File(fileUtil.fileServerPath);
        File[] listOfFiles = file.listFiles();

        for (File files : listOfFiles) {
            if (files.isFile()) {
                String name = files.getName();
                String url = String.format("%s%s", fileUtil.fileBaseUrl, name);
                long size = file.length();
                int lastDotIndex = name.lastIndexOf(".");
                String extension = name.substring(lastDotIndex + 1);

                listFile.add(new FileDto(name, url,size,extension));
            }
        }
        return listFile;
    }
}
