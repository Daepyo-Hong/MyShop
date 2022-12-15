package com.green.nowon.controller;

import com.green.nowon.service.GoodsService;
import com.green.nowon.service.impl.GoodsServiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService service;

    //ajax 요청이지만 @ResponseBody 안쓰고 응답을 다른 페이지로(HTML로) 처리
    @GetMapping("/common/categorys")
    public String categoryList(Model model){
        service.categoryList(model);
        return "goods/category";
    }
    //ajax 요청이지만 @ResponseBody 안쓰고 응답을 다른 페이지로(HTML로) 처리
    //1차카테고리 선택되었을때 2차(sub)카테고리 넘겨줌
    @GetMapping("/common/categorys/{no}")
    public String categoryList(@PathVariable long no, Model model){
        service.categoryList(no,model);
        return "goods/category";
    }
    @GetMapping("/admin/goods")
    public String goods(){
        return "goods/reg";
    }

    @ResponseBody   //응답데이터를 JSON 형식으로 리턴합니다.
    @PostMapping("/admin/temp-upload")
    public Map<String, String> tempUpload(MultipartFile gimg){
        return service.fileTempUpload(gimg);
    }
}
