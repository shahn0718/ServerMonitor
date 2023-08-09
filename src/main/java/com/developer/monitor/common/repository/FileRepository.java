package com.developer.monitor.common.repository;

import java.io.File;
import java.util.List;

public interface FileRepository {

    /**
     *  getFileFromDIr
     *  폴더 내, 파일목록 리스트화
     */
    List<File> getFileFromDir(String filePath);
    String readFileFromDir(File fileName) throws Exception;
    List<String> makeListFromDir(File fileName) throws Exception;
}
