package com.greenart.library_service.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_service.data.BookReplyVO;
import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.BookMapper;
import com.greenart.library_service.mapper.ReaderMapper;

@RestController
@RequestMapping("/api")
public class BookAPIController {
    private static int totalLength = 0;
    // private static int totalPage = 0;
    // private static int currentPage = 1;
    @Autowired BookMapper book_mapper;
    @Autowired ReaderMapper reader_mapper;
    @GetMapping("/book/text")
    public Map<String,Object> getBookText(HttpSession session){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null){
            user = new ReaderInfoVO();
            user.setRd_seq(0);
        }
        resultMap.put("havePayment", reader_mapper.selectUserPayment(user.getRd_seq()));
        return resultMap;
    }
    @GetMapping("/book/text/temp")
    public Map<String,Object> getBookTextTemp(HttpSession session,@RequestParam Integer seq,@RequestParam @Nullable Integer fontSize) throws Exception{
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(fontSize == null) {fontSize=0;}
        if(user == null){
            user = new ReaderInfoVO();
            user.setRd_seq(0);
        }
        String contentList = book_mapper.selectBookTextFile(seq);
        String filepath = "/library/book_text/";
        String filename = filepath+contentList;

        // FileInputStream fileInputStream = 
        // InputStreamReader inputStreamReader = ;
        
        List<String> content = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        
        int i = 0;
        totalLength = 0;
        while(br.read() != -1) {
            totalLength++;
        }
        br.close();
        Integer char_per_page = 1000;
        char_per_page += fontSize;
        // System.out.println(fontSize);
        br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        int readLen = 0;
        int totalPage = (int)(Math.ceil((double)totalLength/char_per_page));
        for(int a = 0; a<=totalPage; a++) {
            String pageContent = "";
            for(int b=0; b < char_per_page; b++) {
                i = br.read();
                pageContent+= (char)i;
                readLen++;
                if(readLen >= totalLength) break;
            }
            content.add(pageContent);
        }
        // int startWord = (currentPage-1) * CHAR_PER_PAGE;
        
        // while(startWord < currentPage * CHAR_PER_PAGE) {
            // if(startWord > totalLength) break;
            // startWord++;
            // i = br.read();
            // System.out.print((char)i);
        // }
        br.close();


        // for(int i=0; i<br.read(); i++){
            // String s = "";
            // s +=  br.readLine();
            // content.add(s);
        // }
        resultMap.put("havePayment", reader_mapper.selectUserPayment(user.getRd_seq()));
        resultMap.put("content", content);
        return resultMap;
    }
    @GetMapping("/book/recommend/list")
    public Map<String,Object> getBookRecommendList(HttpSession session,@RequestParam String title){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null){
            user = new ReaderInfoVO();
            user.setRd_seq(0);
        }
        resultMap.put("user", user);
        resultMap.put("list", book_mapper.selectAllRecommendBooks(title,user.getRd_seq()));
        return resultMap;
    }
    @GetMapping("/book/comment/list")
    public Map<String,Object> getBookComment(@RequestParam Integer seq, @RequestParam @Nullable Integer page){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        resultMap.put("list", book_mapper.selectAllBookReply(seq));
        resultMap.put("pageCnt", book_mapper.selectAllBookReplyCnt(seq));
        return resultMap;
    }
    @PutMapping("/book/comment_add")
    public Map<String,Object> putBookComment(@RequestBody BookReplyVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.insertBookReply(data);
        resultMap.put("status", true);
        resultMap.put("message", "댓글을 등록하였습니다.");
        return resultMap;
    }
    @PatchMapping("/book/comment_modify")
    public Map<String,Object> patchBookComment(@RequestBody BookReplyVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.updateBookReply(data);
        resultMap.put("status", true);
        resultMap.put("message", "댓글을 수정하였습니다.");
        return resultMap;
    }
    @DeleteMapping("/book/comment/delete")
    public Map<String,Object> deleteBookComment(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.deleteBookReply(seq);
        resultMap.put("status", true);
        resultMap.put("message", "댓글을 삭제하였습니다.");
        return resultMap;
    }
}
