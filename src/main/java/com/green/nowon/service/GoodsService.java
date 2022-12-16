package com.green.nowon.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface GoodsService {

	Map<String,String> fileTempUpload(MultipartFile gimg);

	

}
