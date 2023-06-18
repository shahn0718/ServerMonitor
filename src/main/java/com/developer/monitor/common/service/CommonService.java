package com.developer.monitor.common.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface CommonService {
        /**
         *  getFileFromDIr
         *  폴더 내, 파일목록 리스트화
         */
        List<File> getFileFromDir(String filePath);
        String readFileFromDir(File fileName) throws Exception;
        List<String> makeListFromDir(File fileName) throws Exception;

    }
