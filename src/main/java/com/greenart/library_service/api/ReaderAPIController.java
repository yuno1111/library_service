package com.greenart.library_service.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_service.data.QnaVO;
import com.greenart.library_service.data.ReaderInfoVO;
import com.greenart.library_service.data.RecurringPaymentItemVO;
import com.greenart.library_service.data.StorageVO;
import com.greenart.library_service.mapper.ReaderMapper;

@RestController
@RequestMapping("/api")
public class ReaderAPIController {
    @Autowired ReaderMapper reader_mapper;
    @GetMapping("/reader/storage")
    public Map<String,Object> getReaderStorage(@RequestParam Integer bi_seq,HttpSession session){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        List<StorageVO> storage = reader_mapper.selectUserStorage(user.getRd_seq());
        if(storage.isEmpty()){
            StorageVO tempStorage = new StorageVO();
            tempStorage.setSto_name("임시 보관함");
            tempStorage.setSto_open(0);
            tempStorage.setSto_rd_seq(user.getRd_seq());
            reader_mapper.insertFirstStorage(tempStorage);
            resultMap.put("status", false);
            resultMap.put("message", "임시 보관함에 추가하였습니다.");
            reader_mapper.insertMybook(bi_seq, tempStorage.getSto_seq(), user.getRd_seq());
            return resultMap;
        }
            resultMap.put("status", true);
            resultMap.put("list", reader_mapper.selectUserStorage(user.getRd_seq()));
            return resultMap;
    }
    @PutMapping("/reader/storage")
    public Map<String,Object> addStorage(@RequestBody StorageVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("message", "보관함을 생성했습니다.");
        reader_mapper.insertUserStorage(data);
        return resultMap;
    }
    @PatchMapping("/reader/storage")
    public Map<String,Object> updateStorageOpen(@RequestParam Integer open,@RequestParam Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.updateStorageOpen(open, seq);
        return resultMap;
    }
    @DeleteMapping("/reader/storage")
    public Map<String,Object> deleteStorage(@RequestParam Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("message", "보관함을 삭제했습니다.");
        reader_mapper.deleteStorage(seq);
        return resultMap;
    }
    @PatchMapping("/reader/storage_name/modify")
    public Map<String,Object> patchReaderStorageName(@RequestParam String name, @RequestParam Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.updateStorageName(name,seq);
        resultMap.put("status", true);
        resultMap.put("message", "보관함 이름을 변경하였습니다.");
        return resultMap;
    }
    @PutMapping("/reader/my_book/add")
    public Map<String,Object> putReaderStorage(HttpSession session,@RequestParam Integer sto_seq,@RequestParam Integer bi_seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        ReaderInfoVO user = (ReaderInfoVO)session.getAttribute("user");
        Integer dubCheck = reader_mapper.isDubplicateMybook(bi_seq, sto_seq, user.getRd_seq());
        if(dubCheck >= 1){
            resultMap.put("status", false);
            resultMap.put("message", "이미 동일 보관함에 책이 있습니다");
            return resultMap;
        }
        resultMap.put("status", true);
        reader_mapper.insertMybook(bi_seq, sto_seq, user.getRd_seq());
        return resultMap;
    }
    @DeleteMapping("/reader/book_delete")
    public Map<String,Object> deleteStorageBook(@RequestParam Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.deleteStorageBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "보관함에서 삭제하였습니다");
        return resultMap;
    }

    @PutMapping("/reader/qna_add")
    public Map<String,Object> putUserQna(@RequestBody QnaVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.insertUserQna(data);
        resultMap.put("status", true);
        resultMap.put("message", "문의를 등록하였습니다");
        return resultMap;
    }
    @PatchMapping("/reader/qna_mod")
    public Map<String,Object> patchUserQna(@RequestBody QnaVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.updateUserQna(data);
        resultMap.put("status", true);
        resultMap.put("message", "문의를 수정하였습니다");
        return resultMap;
    }
    @DeleteMapping("/reader/qna_del")
    public Map<String,Object> deleteUserQna(@RequestParam Integer seq){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.deleteUserQna(seq);
        resultMap.put("status", true);
        resultMap.put("message", "문의내역을 삭제하였습니다");
        return resultMap;
    }

    @PutMapping("/reader/buy_payment")
    @Transactional
    public Map<String,Object> putUserPayment(@RequestBody RecurringPaymentItemVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        reader_mapper.insertUserPayment(data);
        reader_mapper.updateUserPaymentInfo(data.getRpi_seq(),data.getRpi_rd_seq());
        resultMap.put("status", true);
        resultMap.put("message", "구독권을 구매하였습니다.");
        return resultMap;
    }
}
