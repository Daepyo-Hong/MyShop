package com.green.nowon.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyFileUtils {

    public static Map<String,String> fileUpload(MultipartFile gimg, String location) {
        ClassPathResource cpr = new ClassPathResource("static"+location);
        File folder=null;
        String fileName=null;
        try {
            folder = cpr.getFile();
            String orgName = gimg.getOriginalFilename();
            System.out.println(">>>>>>>>>orgName>>>>>>>>>"+orgName);
            int idx = orgName.lastIndexOf(".");
            String extension = orgName.substring(idx);

            fileName=orgName.substring(0, idx) //.확장자 를 제외한 파일이름
                    +"_"+(System.nanoTime()/1000000)
                    +extension;  //.확장자
            /*
            fileName= UUID.randomUUID()
                    +extension;  //.확장자
            */
            //원본이름_랜덤숫자.jpg
            gimg.transferTo(new File(folder, fileName));  //물리경로
            System.out.println(">>>> 임시폴더에 파일업로드: "+location+fileName );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String,String> tempFile=new HashMap<>();
        //tempFile.put("location", location);
        tempFile.put("fileName", fileName);
        tempFile.put("url", location+fileName);
        return tempFile;

    }

    private MyFileUtils() {}
}
