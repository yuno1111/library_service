package com.greenart.library_service.data;

import java.util.Date;

import lombok.Data;

@Data
public class RecurringPaymentItemVO {
    private Integer rpi_seq;
    private Integer rpi_rp_seq;
    private Integer rpi_rd_seq;
    private Date rpi_end_dt;

    private Integer period;
}
