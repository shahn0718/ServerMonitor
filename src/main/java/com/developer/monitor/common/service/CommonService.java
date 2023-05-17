package com.developer.monitor.common.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface CommonService {
    /**
     *  getFileFromDIr
     *  폴더 내, 파일리스트 생성하기
     */
    public List<File> getFileFromDir(String filePath);








}
