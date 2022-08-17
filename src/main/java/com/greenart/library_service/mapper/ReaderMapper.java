package com.greenart.library_service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_service.data.BookSummaryVO;
import com.greenart.library_service.data.MaxSubBookVO;
import com.greenart.library_service.data.QnaVO;
import com.greenart.library_service.data.ReaderPaymentVO;
import com.greenart.library_service.data.RecurringPaymentItemVO;
import com.greenart.library_service.data.RecurringPaymentVO;
import com.greenart.library_service.data.StorageVO;

@Mapper
public interface ReaderMapper {
    public List<BookSummaryVO> selectAllUserSubBooks(Integer seq);
    public List<StorageVO> selectUserStorage(Integer seq);
    public List<StorageVO> selectUserStorageDetail(Integer seq);
    public List<MaxSubBookVO> selectAllUserBooks(Integer seq);
    public void insertMybook(Integer bi_seq, Integer sto_seq, Integer rd_seq);
    public Integer isDubplicateMybook(Integer bi_seq, Integer sto_seq, Integer rd_seq);
    public void insertUserStorage(StorageVO data);
    public void insertFirstStorage(StorageVO data);
    public void updateStorageOpen(Integer open, Integer seq);
    public void updateStorageName(String name, Integer seq);
    public void deleteStorage(Integer seq);
    public void deleteStorageBook(Integer seq);

    public List<QnaVO> selectUserQna(Integer seq,Integer offset);
    public Integer selectUserQnaCnt(Integer seq);
    public QnaVO selectUserQnaDetail(Integer seq);
    public void insertUserQna(QnaVO data);
    public void updateUserQna(QnaVO data);
    public void deleteUserQna(Integer seq);

    public List<RecurringPaymentVO> selectAllPaymentList();
    public List<ReaderPaymentVO> selectAllUserPayment();
    public Integer selectUserPayment(Integer seq);
    public Integer selectUserPaymentScheduled(Integer seq);
    public void insertUserPayment(RecurringPaymentItemVO data);
    public void updateUserPaymentInfo(Integer rpi_seq, Integer rd_seq);
    public void updateUserPaymentInfoEnd(Integer seq);
}
