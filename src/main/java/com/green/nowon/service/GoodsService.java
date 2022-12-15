package com.green.nowon.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface GoodsService {
    Map<String,String> fileTempUpload(MultipartFile gimg);

    void categoryList(Model model);

    void categoryList(long no, Model model);
}
