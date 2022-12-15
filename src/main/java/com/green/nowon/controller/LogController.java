package com.green.nowon.controller;

import com.green.nowon.domain.dto.member.MemberInsertDTO;
import com.green.nowon.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogController {

    @Autowired
    private LogService service;

    @GetMapping("/member/login")
    public String login(){
        return "log/login";
    }
    //회원가입 페이지이동/member/login
    @GetMapping("/member/signup")
    public String join(){
        return "log/signup";
    }
    //회원가입처리
    @PostMapping("/member/signup")
    public String join(MemberInsertDTO dto){
        service.save(dto);
        return "redirect:/member/login";//회원가입후 로그인페이지로
    }
}
