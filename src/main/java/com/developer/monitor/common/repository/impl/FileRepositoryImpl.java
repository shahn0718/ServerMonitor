package com.developer.monitor.common.repository.impl;

import com.developer.monitor.common.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Repository
public class FileRepositoryImpl implements FileRepository {
    @Override
    public List<File> getFileFromDir(String filePath) {
        File targetDir = new File(filePath);
        List<File> getFileList = Arrays.asList(targetDir.listFiles());

        return getFileList;
    }

    @Override
    public String readFileFromDir(File fileName) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String readData = "";
        String readTmpData="";

        while((readTmpData = bufferedReader.readLine()) != null){
            readData += readTmpData + "\n";
        }
        return readData;
    }

    @Override
    public List<String> makeListFromDir(File fileName) throws Exception {
        /**
         *  ArrayList<String> 형식으로 만들기
         *  [gw-node.load_num=5, gw-node.cpu_usage=10, gw-node.mem_usage=35]
         *
         * */
        FileInputStream fileInputStream = new FileInputStream(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        ArrayList<String> fileData = new ArrayList<>();
        String readTmpData ="";

        while((readTmpData = bufferedReader.readLine()) != null){
            fileData.add(readTmpData);
        }

        return fileData;
    }
}
