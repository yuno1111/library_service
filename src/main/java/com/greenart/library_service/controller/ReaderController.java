package com.greenart.library_service.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.ReaderMapper;

@Controller
public class ReaderController {
    @Autowired ReaderMapper reader_mapper;
    @GetMapping("/mySubscribe")
    public String getMySubscribe(Model model,HttpSession session){
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        model.addAttribute("list", reader_mapper.selectAllUserSubBooks(user.getRd_seq()));
        return "/reader/mySubscribe";
    }
    @GetMapping("/myStorage")
    public String getMyStroage(Model model,HttpSession session){
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        model.addAttribute("list", reader_mapper.selectUserStorageDetail(user.getRd_seq()));
        return "/reader/myStorage";
    }
    @GetMapping("/storage/books")
    public String getMyStroage(Model model,@RequestParam Integer seq){
        model.addAttribute("list", reader_mapper.selectAllUserBooks(seq));
        return "/reader/storageBooks";
    }
    @GetMapping("/qna/request")
    public String getQnaRequest(Model model){
        return "/reader/qnaRequest";
    }
    @GetMapping("/myQna")
    public String getMyQna(Model model,HttpSession session,
    @RequestParam @Nullable Integer page
    ){
        if(page==null)page=1;
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        model.addAttribute("list", reader_mapper.selectUserQna(user.getRd_seq(),(page-1)*10));
        model.addAttribute("pageCnt", reader_mapper.selectUserQnaCnt(user.getRd_seq()));
        return "/reader/myQna";
    }
    @GetMapping("/myQna/qna_detail")
    public String getMyQna(Model model,@RequestParam Integer seq){
        model.addAttribute("list", reader_mapper.selectUserQnaDetail(seq));
        return "/reader/myQna_detail";
    }

    @GetMapping("/payment")
    public String getPaymentList(Model model,HttpSession session){
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        model.addAttribute("dub", reader_mapper.selectUserPayment(user.getRd_seq()));
        model.addAttribute("list", reader_mapper.selectAllPaymentList());
        return "/reader/payment_list";
    }
}
