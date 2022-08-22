package com.greenart.library_service.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.mapper.AccountMapper;
import com.greenart.library_service.util.AESAlgorithm;

@RestController
@RequestMapping("/api/account")
public class AccountAPIController {
    @Autowired AccountMapper account_mapper;
    @PutMapping("/join")
    public ResponseEntity<Map<String, Object>> putReaderJoin(@RequestBody ReaderInfoVO data) throws Exception{
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        data.setRd_pwd(AESAlgorithm.Encrypt(data.getRd_pwd()));
        try {
            account_mapper.joinReader(data);
            }
        catch(Exception e) {
            if(e.getMessage().indexOf("Duplicate") > 0 ) {
                resultMap.put("message", data.getRd_id()+"은(는) 이미 등록된 ID 입니다.");
                resultMap.put("status", false);
                return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.CONFLICT);
            }
            resultMap.put("message", "내부오류가 발생했습니다.");
            resultMap.put("status", false);
            e.printStackTrace();
            return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resultMap.put("message","회원가입이 완료되엇습니다.");
        resultMap.put("status", true);

        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.CREATED);
    }
    
    @GetMapping("/id_chk")
    public Boolean getReaderIDCheck(@RequestParam String id) {
        return account_mapper.IsDuplicateCheck(id) == 1;
    }
    
    @GetMapping("/phone_chk")
    public Boolean getReaderPhoneCheck(@RequestParam String phone_no) {
        return account_mapper.isDuplicatePhone(phone_no) ==  1;
    }

    @PostMapping("/login")
    public Map<String, Object> postAccountLogin(@RequestBody ReaderInfoVO data, HttpSession session) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        data.setRd_pwd(AESAlgorithm.Encrypt(data.getRd_pwd()));
        ReaderInfoVO user = account_mapper.login(data);
        
        if(user == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 또는 비밀번호 오류입니다.");
            return resultMap;
        }

        session.setAttribute("user", user);
        resultMap.put("status", true);
        resultMap.put("message", "로그인 되었습니다.");
        return resultMap;
    }

    @PutMapping("/book_sub")
    public Map<String,Object> putBookSub(@RequestParam Integer bi_seq, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null){
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요한 서비스 입니다.");
            return resultMap;
        }
        account_mapper.insertUserBookSub(bi_seq, user.getRd_seq());
        resultMap.put("status", true);
        resultMap.put("message", "구독 목록에 추가 되었습니다.");
        return resultMap;
    }

    @DeleteMapping("/book_sub/delete")
    public Map<String,Object> deleteBookSub(@RequestParam Integer bi_seq, HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        if(user == null){
            resultMap.put("status", false);
            resultMap.put("message", "로그인이 필요한 서비스 입니다.");
            return resultMap;
        }
        account_mapper.deleteUserBookSub(bi_seq, user.getRd_seq());
        resultMap.put("status", true);
        resultMap.put("message", "구독 목록에 삭제 되었습니다.");
        return resultMap;
    }
}
