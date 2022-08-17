package com.greenart.library_service.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.MainMapper;

@Controller
public class MainController {
    @Autowired MainMapper main_mapper;
    @GetMapping("/")
    public String getMain(Model model,HttpSession session){
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null) {
            user = new ReaderInfoVO();
            user.setRd_seq(0);
        }
        model.addAttribute("subBookList", main_mapper.selectAllMaxSubBooks(user.getRd_seq()));
        return "/index";
    }
    @GetMapping("/join")
    public String getjoin(){
        return "/account/join";
    }
    @GetMapping("/login")
    public String getLogin(){
        return "/account/login";
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/notice/list")
    public String getBookList(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null) page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", main_mapper.selectAllNoticeInfo(keyword, (page-1)*10));
        model.addAttribute("pageCnt", main_mapper.selectAllNoticePageCnt(keyword));
        return "/notice_list";
    }
    @GetMapping("/notice/detail")
    public String getNoticeAdd(Model model,@RequestParam Integer seq){
        model.addAttribute("list", main_mapper.selectNoticeInfoBySeq(seq));
        return "/notice_detail";
    }
}
