package com.greenart.library_service.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.BookMapper;

@Controller
public class BookController {
    @Autowired BookMapper book_mapper;
    @GetMapping("/book/summary")
    public String getBookSummary(HttpSession session,Model model,@RequestParam Integer seq){
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null){
            user = new ReaderInfoVO();
            user.setRd_seq(0);;
        }
        model.addAttribute("list", book_mapper.selectSummaryBooksBySeq(seq));
        model.addAttribute("coverList", book_mapper.selectBooksCoverBySeq(seq));
        model.addAttribute("user_comment", book_mapper.selectUserBookReply(seq, user.getRd_seq()));
        return "/book/book_summary";
    }
    @GetMapping("/book/text")
    public String getBookText(Model model,@RequestParam Integer seq){
        model.addAttribute("contentList", book_mapper.selectBookContentBySeq(seq));
        return "/book/book_text";
    }
    @GetMapping("/book/text/temp")
    public String getBookTextTemp(Model model,@RequestParam Integer seq){
        model.addAttribute("seq", seq);
        return "/book/book_text_temp";
    }
}
