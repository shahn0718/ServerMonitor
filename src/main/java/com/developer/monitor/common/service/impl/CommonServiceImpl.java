package com.developer.monitor.common.service.impl;

import com.developer.monitor.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public List<File> getFileFromDir(String filePath) {

        File targetDir = new File(filePath);
        List<File> getFileList = Arrays.asList(targetDir.listFiles());

        return getFileList;
    }


}
