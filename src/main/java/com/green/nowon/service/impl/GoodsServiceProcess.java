package com.green.nowon.service.impl;

import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class GoodsServiceProcess implements GoodsService {
	
	@Value("${file.location.temp}")
	private String locationTemp;
	
	
	
	@Override
	public Map<String,String> fileTempUpload(MultipartFile gimg) {
		
		return MyFileUtils.fileUpload(gimg, locationTemp);
	}

	
	
	

}
