package com.green.nowon.service.impl;

import com.green.nowon.domain.entity.Category;
import com.green.nowon.domain.entity.CategoryRepository;
import com.green.nowon.service.GoodsService;
import com.green.nowon.utils.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceProcess implements GoodsService {


    @Value("${file.location.temp}")
    private String locationTemp;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Map<String,String> fileTempUpload(MultipartFile gimg) {

        return MyFileUtils.fileUpload(gimg, locationTemp);    //업로드위치까지
    }

    //1차 카테고리 읽어오는것
    @Override
    public void categoryList(Model model) {
        //1차 카테고리만 읽어오기         조건을 넣을때 물리디비의 컬럼을 카멜케이스로 접근
        List<Category> result = categoryRepository.findAllByParentNoIsNull();
        model.addAttribute("list",result);
    }

    //sub카테고리(2차,3차) 읽어오는것
    @Override
    public void categoryList(long no, Model model) {
        model.addAttribute("list",categoryRepository.findAllByParentNo(no));
    }
}
