package com.greenart.library_service.component;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.greenart.library_service.data.ReaderPaymentVO;
import com.greenart.library_service.mapper.ReaderMapper;


@Component
public class ServiceScheduleComponent {
    @Autowired ReaderMapper reader_mapper;
    @Scheduled(cron = "* */1 * * * *")
    public void deleteAfterPaymentTemp2(){
        List<ReaderPaymentVO> pay =  reader_mapper.selectAllUserPayment();
        for(ReaderPaymentVO vo : pay){
            Integer isEnd = reader_mapper.selectUserPaymentScheduled(vo.getRd_rpi_seq());
            if(isEnd == null){
                reader_mapper.updateUserPaymentInfoEnd(vo.getRd_seq());
            }
        }
    }
}
