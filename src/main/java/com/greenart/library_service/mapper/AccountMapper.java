package com.greenart.library_service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_service.data.ReaderInfoVO;

@Mapper
public interface AccountMapper {
    public void joinReader(ReaderInfoVO data);    
    public Integer IsDuplicateCheck(String id);
    public Integer isDuplicatePhone(String phone);

    public ReaderInfoVO login(ReaderInfoVO data);

    public void insertUserBookSub(Integer bi_seq, Integer rd_seq);
    public void deleteUserBookSub(Integer bsub_seq, Integer rd_seq);

}
