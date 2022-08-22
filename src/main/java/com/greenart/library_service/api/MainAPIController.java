package com.greenart.library_service.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.MainMapper;

@RestController
@RequestMapping("/api")
public class MainAPIController {
    @Autowired MainMapper main_mapper;
    @GetMapping("/")
    public Map<String,Object> getMain(HttpSession session){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null) {
            user = new ReaderInfoVO();
            user.setRd_seq(0);
        }
        resultMap.put("user", user);
        resultMap.put("subBookList", main_mapper.selectAllMaxSubBooks(user.getRd_seq()));
        resultMap.put("newBookList", main_mapper.selectNewbooks(user.getRd_seq()));
        return resultMap;
    }
}
