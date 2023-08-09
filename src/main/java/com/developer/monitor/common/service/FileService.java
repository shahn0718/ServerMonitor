package com.developer.monitor.common.service;

import com.developer.monitor.common.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    public List<File> getFileFromDir(String filePath){
        return fileRepository.getFileFromDir(filePath);
    }
    public String readFileFromDir(File fileName) throws Exception{
        return fileRepository.readFileFromDir(fileName);
    }
    public List<String> makeListFromDir(File fileName) throws Exception{
        return fileRepository.makeListFromDir(fileName);
    }

}
