package com.greenart.library_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_service.data.BookContentListVO;
import com.greenart.library_service.data.BookCoverListVO;
import com.greenart.library_service.data.BookReplyVO;
import com.greenart.library_service.data.BookSummaryVO;
import com.greenart.library_service.data.Book_infoVO;

@Mapper
public interface BookMapper {
    public Book_infoVO selectSummaryBooksBySeq(Integer seq);
    public List<BookCoverListVO>  selectBooksCoverBySeq(Integer seq);
    public List<BookContentListVO> selectBookContentBySeq(Integer seq);
    public String selectBookTextFile(Integer seq);
    public List<BookSummaryVO> selectAllRecommendBooks(String title,Integer seq);

    public List<BookReplyVO> selectAllBookReply(Integer seq);
    public Integer selectAllBookReplyCnt(Integer seq);
    public BookReplyVO selectUserBookReply(Integer seq , Integer rd_seq);
    public void insertBookReply(BookReplyVO data);
    public void updateBookReply(BookReplyVO data);
    public void deleteBookReply(Integer seq);
}
