package com.greenart.library_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_service.data.BookSummaryVO;
import com.greenart.library_service.data.MaxSubBookVO;
import com.greenart.library_service.data.NoticeInfoVO;

@Mapper
public interface MainMapper {
    public List<MaxSubBookVO> selectAllMaxSubBooks(Integer seq);
    public List<NoticeInfoVO> selectAllNoticeInfo(String keyword, Integer offset);
    public List<BookSummaryVO> selectNewbooks(Integer seq);
    public Integer selectAllNoticePageCnt(String keyword);
    public NoticeInfoVO selectNoticeInfoBySeq(Integer seq);
}
